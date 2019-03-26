package com.chewords.jsv.field;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:05:09
 * @Desc
 *
 */

public class MapField extends JsvField {
    // Separation of kv pairs
    private String sep;
    // Separation of key and value
    private String kvSep;
    // Class of the Map. Must be instantiable
    private Class type;
    // Key type
    private Class kType;
    // Value type
    private Class vType;

    // Getters and Setters

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public String getKvSep() {
        return kvSep;
    }

    public void setKvSep(String kvSep) {
        this.kvSep = kvSep;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Class getkType() {
        return kType;
    }

    public void setkType(Class kType) {
        this.kType = kType;
    }

    public Class getvType() {
        return vType;
    }

    public void setvType(Class vType) {
        this.vType = vType;
    }
}
