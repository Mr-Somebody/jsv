package com.chewords.jsv.action;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 13:06:23
 * @Desc
 *
 */

import com.chewords.jsv.JsvRecord;
import com.chewords.jsv.field.MapField;
import com.chewords.jsv.util.Utility;

import java.util.Map;

public class MapAction implements Action {

    private MapField field;

    public MapAction(MapField field) {
        this.field = field;
    }

    @Override
    public Object load(JsvRecord record) throws Exception {
        return Utility.split(record.get(this.field.getName()), this.field.getSep(), this.field.getKvSep(),
                this.field.getType(), this.field.getkType(), this.field.getvType());
    }

    @Override
    public String dump(Object obj) throws Exception {
        Map value = (Map)this.field.getField().get(obj);
        if (value == null) return "";
        return Utility.join(this.field.getSep(), this.field.getKvSep(), value);
    }
}
