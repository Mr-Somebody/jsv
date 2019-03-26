package com.chewords.jsv.action

import com.chewords.jsv.JsvRecord
import com.chewords.jsv.Person
import com.chewords.jsv.field.JsvField
import spock.lang.Specification

/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:47:42
 * @Desc
 *
 */

class DefaultActionTest extends Specification {

    def "load and dump test"() {
        JsvRecord record = new JsvRecord(["Name", "age"] as String[], ["Kate", "56"] as String[])
        JsvField jsvField = new JsvField()
        DefaultAction action = new DefaultAction(jsvField)
        when:
        jsvField.setName("Name")
        jsvField.setField(Person.getDeclaredField("name"))
        jsvField.getField().setAccessible(true)
        String name = action.load(record)
        then:
        name == "Kate"
        when:
        jsvField.setName("age")
        jsvField.setField(Person.getDeclaredField("age"))
        jsvField.getField().setAccessible(true)
        int age = action.load(record)
        then:
        age == 56
    }
}
