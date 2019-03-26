package com.chewords.jsv.field;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 13:15:45
 * @Desc
 *
 */

public class ActionField extends JsvField {

    // Subclass of interface Action
    private Class cls;

    // Getters and Setters

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }
}
