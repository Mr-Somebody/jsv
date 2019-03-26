package com.chewords.jsv.field;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:05:01
 * @Desc
 *
 */

public class ListField extends JsvField {
    // Separation String
    private String sep;
    // Type of this list. Must be instantiable
    private Class type;
    // Value class of the list
    private Class vType;

    // Getters and Setters

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Class getvType() {
        return vType;
    }

    public void setvType(Class vType) {
        this.vType = vType;
    }
}
