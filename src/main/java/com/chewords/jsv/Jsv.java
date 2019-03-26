package com.chewords.jsv;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 10:43:33
 * @Desc
 *
 */

import com.chewords.jsv.action.*;
import com.chewords.jsv.annotation.*;
import com.chewords.jsv.field.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Jsv {
    public Jsv() {
    }

    /*
     * Dump an annotated object into a Record object
     * */
    public JsvRecord dump(Object obj) throws Exception {
        List<JsvField> jsvFields = this.getFields(obj.getClass());
        JsvRecord record = new JsvRecord(this.parseHeaders(jsvFields), this.parseValues(jsvFields, obj));
        return record;
    }

    /*
     * Dump an list of object into a list of Record objects
     * */
    public List<JsvRecord> dump(List objs) throws Exception {
        ArrayList<JsvRecord> result = new ArrayList<>(objs.size());
        if (objs.size() == 0) return result;
        List<JsvField> jsvFields = this.getFields(objs.get(0).getClass());
        String[] headers = this.parseHeaders(jsvFields);
        for (Object obj : objs) result.add(new JsvRecord(headers, this.parseValues(jsvFields, obj)));
        return result;
    }

    /*
    * Parse an object from a record
    * */
    public <T> T load(JsvRecord record, Class<T> cls) throws Exception {
        List<JsvField> jsvFields = this.getFields(cls);
        T result = cls.newInstance();
        for (JsvField jsvField : jsvFields) {
            Action action;
            if (jsvField.getClass() == ArrayField.class) {
                action = new ArrayAction((ArrayField) jsvField);
            } else if (jsvField.getClass() == ListField.class) {
                action = new ListAction((ListField) jsvField);
            } else if (jsvField.getClass() == MapField.class) {
                action = new MapAction((MapField) jsvField);
            } else if (jsvField.getClass() == ActionField.class) {
                action = (Action) ((ActionField) jsvField).getCls().newInstance();
            } else action = new DefaultAction(jsvField);
            jsvField.getField().set(result, action.load(record));
        }
        return result;
    }

    /*
     * Parse headers
     * */
    protected String[] parseHeaders(List<JsvField> jsvFields) {
        String[] headers = new String[jsvFields.size()];
        for (int i = 0; i < jsvFields.size(); ++i) headers[i] = jsvFields.get(i).getName();
        return headers;
    }

    /*
     * Parse values
     * */
    protected String[] parseValues(List<JsvField> jsvFields, Object obj) throws Exception {
        String[] result = new String[jsvFields.size()];
        for (int i = 0; i < jsvFields.size(); ++i) {
            JsvField jsvField = jsvFields.get(i);
            Action action;
            if (jsvField.getClass() == ArrayField.class) {
                action = new ArrayAction((ArrayField) jsvField);
            } else if (jsvField.getClass() == ListField.class) {
                action = new ListAction((ListField) jsvField);
            } else if (jsvField.getClass() == MapField.class) {
                action = new MapAction((MapField) jsvField);
            } else if (jsvField.getClass() == ActionField.class) {
                action = (Action) ((ActionField) jsvField).getCls().newInstance();
            } else action = new DefaultAction(jsvField);
            result[i] = action.dump(obj);
        }
        return result;
    }

    /*
     * Parse JsvField info of a class
     * */
    protected List<JsvField> getFields(Class cls) {
        ArrayList<JsvField> fields = new ArrayList<>();
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Annotation annotation;
            if (field.getDeclaredAnnotation(JsvExpose.class) != null) {
                JsvField jsvField;
                if ((annotation = field.getDeclaredAnnotation(JsvArray.class)) != null) {
                    JsvArray jsvArray = (JsvArray) annotation;
                    ArrayField arrayField = new ArrayField();
                    arrayField.setSep(jsvArray.sep());
                    arrayField.setvType(jsvArray.vType());
                    jsvField = arrayField;
                } else if ((annotation = field.getDeclaredAnnotation(JsvList.class)) != null) {
                    JsvList jsvList = (JsvList) annotation;
                    ListField listField = new ListField();
                    listField.setSep(jsvList.sep());
                    listField.setType(jsvList.type());
                    listField.setvType(jsvList.vType());
                    jsvField = listField;
                } else if ((annotation = field.getDeclaredAnnotation(JsvMap.class)) != null) {
                    JsvMap jsvMap = (JsvMap) annotation;
                    MapField mapField = new MapField();
                    mapField.setSep(jsvMap.sep());
                    mapField.setKvSep(jsvMap.kvSep());
                    mapField.setType(jsvMap.type());
                    mapField.setkType(jsvMap.kType());
                    mapField.setvType(jsvMap.vType());
                    jsvField = mapField;
                } else if ((annotation = field.getDeclaredAnnotation(JsvAction.class)) != null) {
                    JsvAction jsvAction = (JsvAction) annotation;
                    ActionField actionField = new ActionField();
                    actionField.setCls(jsvAction.cls());
                    jsvField = actionField;
                } else {
                    jsvField = new JsvField();
                }
                jsvField.setField(field);
                String name;
                if ((annotation = field.getDeclaredAnnotation(JsvName.class)) != null) {
                    name = ((JsvName) annotation).name();
                } else name = field.getName();
                jsvField.setName(name);
                fields.add(jsvField);
            }
        }
        return fields;
    }
}
