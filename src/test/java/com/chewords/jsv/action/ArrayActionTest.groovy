package com.chewords.jsv.action

import com.chewords.jsv.JsvRecord
import com.chewords.jsv.Person
import com.chewords.jsv.field.ArrayField
import spock.lang.Specification;
/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:47:30
 * @Desc
 *
 */

class ArrayActionTest extends Specification {

    def "load and dump test"() {
        JsvRecord record = new JsvRecord(["sibs"] as String[], ["Kate,Mars,Mike"] as String[])
        ArrayField arrayField = new ArrayField()
        arrayField.setName("sibs")
        arrayField.setField(Person.getDeclaredField("siblings"))
        arrayField.getField().setAccessible(true)
        arrayField.setSep(",")
        arrayField.setvType(String.class)
        ArrayAction action = new ArrayAction(arrayField);
        when:
        String[] sibs = (String[]) action.load(record)
        then:
        sibs.length == 3
        sibs[0] == "Kate"
        sibs[1] == "Mars"
        sibs[2] == "Mike"
        when:
        sibs[0] = "A"
        Person person = new Person()
        person.siblings = sibs
        then:
        String value = action.dump(person)
        then:
        value == "A,Mars,Mike"
    }
}
