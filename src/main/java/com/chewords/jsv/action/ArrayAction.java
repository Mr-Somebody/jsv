package com.chewords.jsv.action;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:44:31
 * @Desc
 *
 */

import com.chewords.jsv.JsvRecord;
import com.chewords.jsv.exception.UnsupportedCastTypeException;
import com.chewords.jsv.field.ArrayField;
import com.chewords.jsv.util.Utility;

public class ArrayAction implements Action {

    private ArrayField field;

    public ArrayAction(ArrayField field) {
        this.field = field;
    }

    @Override
    public Object load(JsvRecord record) throws UnsupportedCastTypeException {
        return Utility.split(record.get(this.field.getName()), this.field.getSep(), this.field.getvType());
    }

    @Override
    public String dump(Object target) throws IllegalAccessException {
        return Utility.join(this.field.getSep(), (Object[]) this.field.getField().get(target));
    }
}
