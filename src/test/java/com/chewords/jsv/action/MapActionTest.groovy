package com.chewords.jsv.action

import com.chewords.jsv.JsvRecord
import com.chewords.jsv.Person
import com.chewords.jsv.field.MapField
import spock.lang.Specification;

/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:48:02
 * @Desc
 *
 */

class MapActionTest extends Specification {
    def "load and dump test"() {
        JsvRecord record = new JsvRecord(["scores"] as String[], ["math:90,physics:89.5,literature:78"] as String[])
        MapField mapField = new MapField()
        mapField.setName("scores")
        mapField.setField(Person.getDeclaredField("scores"))
        mapField.getField().setAccessible(true)
        mapField.setSep(",")
        mapField.setKvSep(":")
        mapField.setType(HashMap.class)
        mapField.setkType(String.class)
        mapField.setvType(Double.class)
        MapAction action = new MapAction(mapField)
        when:
        Map scores = (Map) action.load(record)
        then:
        scores.getClass() == HashMap.class
        scores.size() == 3
        scores.get("math") == 90
        scores.get("physics") == 89.5
        scores.get("literature") == 78
        when:
        scores["math"] = 60.0
        Person person = new Person()
        person.scores = scores
        then:
        String value = action.dump(person)
        then:
        value == "literature:78.0,physics:89.5,math:60.0"
    }
}
