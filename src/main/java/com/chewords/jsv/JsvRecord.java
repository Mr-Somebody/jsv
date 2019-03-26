package com.chewords.jsv;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 10:45:41
 * @Desc Define a record
 *
 */

import java.util.HashMap;
import java.util.Map;

public class JsvRecord {
    // Header array of this record
    private String[] headers;
    // Value array of this record
    private String[] values;
    // Value maps
    private Map<String, String> map;
    // Getters and setters

    public JsvRecord(String[] headers, String[] values) {
        this.headers = headers;
        this.values = values;
        map = new HashMap<>();
        for (int i = 0; i < values.length; ++i) {
            map.put(headers[i], values[i]);
        }
    }

    public boolean contains(String name) {return this.map.containsKey(name);}

    public String get(String name) {
        return this.map.getOrDefault(name, null);
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[] getValues() {
        return values;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
