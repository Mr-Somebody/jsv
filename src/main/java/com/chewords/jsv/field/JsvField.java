package com.chewords.jsv.field;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:04:53
 * @Desc
 *
 */

import java.lang.reflect.Field;

public class JsvField {
    // Class field related to this JsvFile
    private Field field;
    // Serialized name for this field
    private String name;

    // Getters and setters

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
