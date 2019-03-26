package com.chewords.jsv

import spock.lang.Specification

/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:46:23
 * @Desc
 *
 */

class JsvRecordTest extends Specification {
    def "test create"() {
        when:
        JsvRecord record = new JsvRecord(["A", "B"] as String[], ["56", "34"] as String[])
        then:
        record.headers.length == 2
        record.headers[0] == "A"
        record.headers[1] == "B"
        record.values.length == 2
        record.values[0] == "56"
        record.values[1] == "34"
        record.map.size() == 2
        record.get("A") == "56"
        record.get("B") == "34"
        record.map.get("A") == "56"
        record.map.get("B") == "34"
    }
}
