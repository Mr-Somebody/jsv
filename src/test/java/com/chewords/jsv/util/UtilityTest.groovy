package com.chewords.jsv.util
/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:46:41
 * @Desc
 *
 */

import spock.lang.Specification

class UtilityTest extends Specification {

    def "safeCast test"() {
        when:
        String string = Utility.safeCast("13", String.class)
        then:
        string == "13"
        when:
        double a = Utility.safeCast("1.45", double.class)
        Double b = Utility.safeCast("1.45", Double.class)
        then:
        a == 1.45
        b == 1.45
        when:
        float c = Utility.safeCast("1.66", float.class)
        Float d = Utility.safeCast("1.66", Float.class)
        then:
        c == 1.66f
        d == 1.66f
        when:
        long e = Utility.safeCast("56", long.class)
        Long f = Utility.safeCast("56", Long.class)
        then:
        e == 56L
        f == 56L
        when:
        int g = Utility.safeCast("6", int.class)
        Integer h = Utility.safeCast("6", Integer.class)
        then:
        g == 6
        h == 6
        when:
        short i = Utility.safeCast("-1", short.class)
        Integer j = Utility.safeCast("19", Short.class)
        then:
        i == -1
        j == 19
        when:
        boolean k = Utility.safeCast("true", boolean.class)
        Boolean l = Utility.safeCast("false", Boolean.class)
        then:
        k
        !l
        when:
        char m = Utility.safeCast("true", char.class)
        Character n = Utility.safeCast("false", Character.class)
        then:
        m == 't'
        n == 'f'
    }

    def "split array test"() {
        int[] result
        when:
        result = (int[])Utility.split(null, ",", int.class)
        then:
        result.length == 0
        when:
        result = (int[])Utility.split("", ",", int.class)
        then:
        result.length == 0
        when:
        result = (int[])Utility.split("4,5,6", ",", int.class)
        then:
        result.length == 3
        result[0] == 4
        result[1] == 5
        result[2] == 6
        when:
        Double[] result_1 = (Double[])Utility.split("4.5,5", ",", Double.class)
        then:
        result_1.length == 2
        result_1[0] == 4.5
        result_1[1] == 5
    }

    def "split map test"() {
        Map<String, Integer> result
        when:
        result = Utility.split(null, ",", ":", HashMap.class, String.class, Integer.class)
        then:
        result.size() == 0
        when:
        result = Utility.split("", ",", ":", HashMap.class, String.class, Integer.class)
        then:
        result.size() == 0
        when:
        result = Utility.split("Bob:1,Kate:2", ",", ":", HashMap.class, String.class, Integer.class)
        then:
        result.size() == 2
        result.get("Bob") == 1
        result.get("Kate") == 2
    }

    def "join array test"() {
        String result
        when:
        result = Utility.join(",", null)
        then:
        result == ""
        when:
        result = Utility.join(",", [1,2,3] as int[])
        then:
        result == "1,2,3"
    }

    def "join map test"() {
        String result
        when:
        result = Utility.join(",", ":", null)
        then:
        result == ""
        when:
        result = Utility.join(",", ":", [bob: 1, kate:2])
        then:
        result == "bob:1,kate:2"
    }
}
