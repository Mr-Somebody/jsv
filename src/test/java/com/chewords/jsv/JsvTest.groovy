package com.chewords.jsv

import spock.lang.Specification;

/*
 *
 * @Author Joey
 * @Date 26/03/2019 09:46:02
 * @Desc
 *
 */

class JsvTest extends Specification {

    def "load test"() {
        JsvRecord record = new JsvRecord(["name", "age", "sibs", "nbs", "scores"] as String[],
                ["Mike", "16", "Monica,Jay", "Rose,Joey", "Math:90,Art:20"] as String[])
        when:
        Person person = new Jsv().load(record, Person.class)
        then:
        person.name == "Mike"
        person.age == 16
        person.siblings == ["Monica", "Jay"] as String[]
        person.neighbours.size() == 2
        person.neighbours.get(0) == "Rose"
        person.neighbours.get(1) == "Joey"
        person.scores.size() == 2
        person.scores['Math'] == 90
        person.scores['Art'] == 20
    }

    def "dump one test"() {
        Person person = new Person()
        person.name = "Mike"
        person.age = 16
        person.siblings = ["Monica", "Jay"] as String[]
        person.neighbours = new LinkedList()
        person.neighbours.addAll(Arrays.asList(["Rose", "Joey"]))
        person.scores = new HashMap<>()
        person.scores['Math'] = 90
        person.scores['Art'] = 20
        when:
        JsvRecord record = new Jsv().dump(person)
        def headers = ["name", "age", "sibs", "nbs", "scores"] as String[]
        def values = ["Mike", "16", "Monica,Jay", "Rose,Joey", "Math:90,Art:20"] as String[]
        then:
        record.headers.length == 5
        record.values.length == 5
        record.map.size() == 5
        for (int i = 0; i < 5; ++i) {
            record.headers[i] == headers[i]
            record.values[i] == values[i]
            record.get(headers[i]) == values[i]
            record.map.get(headers[i]) == values[i]
        }
    }

    def "dump list test"() {
        Person person = new Person()
        person.name = "Mike"
        person.age = 16
        person.siblings = ["Monica", "Jay"] as String[]
        person.neighbours = new LinkedList()
        person.neighbours.addAll(Arrays.asList(["Rose", "Joey"]))
        person.scores = new HashMap<>()
        person.scores['Math'] = 90
        person.scores['Art'] = 20
        ArrayList<Person> people = new ArrayList<>(1)
        people.add(person)
        when:
        List<JsvRecord> records = new Jsv().dump(people)
        JsvRecord record = records.get(0)
        def headers = ["name", "age", "sibs", "nbs", "scores"] as String[]
        def values = ["Mike", "16", "Monica,Jay", "Rose,Joey", "Math:90,Art:20"]
        then:
        record.headers.length == 5
        record.values.length == 5
        record.map.size() == 5
        for (int i = 0; i < 5; ++i) {
            record.headers[i] == headers[i]
            record.values[i] == values[i]
            record.get(headers[i]) == values[i]
            record.map.get(headers[i]) == values[i]
        }
    }
}
