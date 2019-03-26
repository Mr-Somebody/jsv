package com.chewords.jsv.action;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 13:06:08
 * @Desc
 *
 */

import com.chewords.jsv.JsvRecord;
import com.chewords.jsv.field.ListField;
import com.chewords.jsv.util.Utility;

import java.lang.reflect.Array;
import java.util.List;

public class ListAction implements Action {

    private ListField field;

    public ListAction(ListField field) {
        this.field = field;
    }

    @Override
    public Object load(JsvRecord record) throws Exception {
        List values = (List) this.field.getType().newInstance();
        Object result = Utility.split(record.get(this.field.getName()), this.field.getSep(), this.field.getvType());
        for (int i = 0; i < Array.getLength(result); ++i) {
            values.add(Array.get(result, i));
        }
        return values;
    }

    @Override
    public String dump(Object obj) throws Exception {
        List value = (List)this.field.getField().get(obj);
        if (value == null) return "";
        return Utility.join(this.field.getSep(), value.toArray());
    }
}
