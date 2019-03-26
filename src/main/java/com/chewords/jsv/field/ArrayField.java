package com.chewords.jsv.field;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:04:24
 * @Desc
 *
 */

public class ArrayField extends JsvField{
    // Separation string for values
    private String sep;
    // Value class
    private Class vType;

    // Getters and Setters

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public Class getvType() {
        return vType;
    }

    public void setvType(Class vType) {
        this.vType = vType;
    }
}
