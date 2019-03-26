package com.chewords.jsv.action

import com.chewords.jsv.JsvRecord
import com.chewords.jsv.Person
import com.chewords.jsv.field.ListField
import spock.lang.Specification
/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:47:54
 * @Desc
 *
 */

class ListActionTest extends Specification {
    def "load and dump test"() {
        JsvRecord record = new JsvRecord(["nbs"] as String[], ["Kate,Mars,Mike"] as String[])
        ListField listField = new ListField()
        listField.setName("nbs")
        listField.setField(Person.getDeclaredField("neighbours"))
        listField.getField().setAccessible(true)
        listField.setSep(",")
        listField.setvType(String.class)
        listField.setType(LinkedList.class)
        ListAction action = new ListAction(listField)
        when:
        List nbs = (List) action.load(record)
        then:
        nbs.getClass() == LinkedList.class
        nbs.size() == 3
        nbs.get(0) == "Kate"
        nbs.get(1) == "Mars"
        nbs.get(2) == "Mike"
        when:
        nbs.add("Liz")
        Person person = new Person()
        person.neighbours = nbs
        then:
        String value = action.dump(person)
        then:
        value == "Kate,Mars,Mike,Liz"
    }
}
