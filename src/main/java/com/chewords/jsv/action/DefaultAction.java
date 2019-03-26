package com.chewords.jsv.action;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 13:07:18
 * @Desc
 *
 */

import com.chewords.jsv.JsvRecord;
import com.chewords.jsv.field.JsvField;
import com.chewords.jsv.util.Utility;

public class DefaultAction implements Action {

    private JsvField field;

    public DefaultAction(JsvField field) {
        this.field = field;
    }

    @Override
    public Object load(JsvRecord record) throws Exception {
        return Utility.safeCast(record.get(this.field.getName()), this.field.getField().getType());
    }

    @Override
    public String dump(Object obj) throws Exception {
        return String.valueOf(this.field.getField().get(obj));
    }
}
