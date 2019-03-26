package com.chewords.jsv.util;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:47:05
 * @Desc
 *
 */

import com.chewords.jsv.exception.UnsupportedCastTypeException;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.regex.Pattern;

public class Utility {
    /*
    * Cast string value to basic java types safely.
    * Supported types: String, Double/double, Float/float, Long/long, Integer/integer, Short/short, Boolean/boolean, Character/char
    * An UnsupportedCastTypeException will be thrown if trying to cast to other types
    * */
    public static <T> T safeCast(String value, Class<T> cls) throws UnsupportedCastTypeException {
        if (cls.isAssignableFrom(String.class)) return (T) value;
        else if (cls.isAssignableFrom(Double.class) || cls.isAssignableFrom(double.class))
            return (T) (Object) Double.parseDouble(value);
        else if (cls.isAssignableFrom(Float.class) || cls.isAssignableFrom(float.class))
            return (T) (Object) Float.parseFloat(value);
        else if (cls.isAssignableFrom(Long.class) || cls.isAssignableFrom(long.class))
            return (T) (Object) Long.parseLong(value);
        else if (cls.isAssignableFrom(Integer.class) || cls.isAssignableFrom(int.class))
            return (T) (Object) Integer.parseInt(value);
        else if (cls.isAssignableFrom(Short.class) || cls.isAssignableFrom(short.class))
            return (T) (Object) Short.parseShort(value);
        else if (cls.isAssignableFrom(Boolean.class) || cls.isAssignableFrom(boolean.class))
            return (T) (Object) Boolean.parseBoolean(value);
        else if (cls.isAssignableFrom(Character.class) || cls.isAssignableFrom(char.class))
            return (T) (Object) value.charAt(0);
        else {
            throw new UnsupportedCastTypeException("Unsupported type: " + cls.getCanonicalName());
        }
    }

    /*
    * Split a string into an array
    * */
    public static <T> Object split(String string, String sep, Class<T> cls) throws UnsupportedCastTypeException {
        if (string == null || string.length() == 0) return Array.newInstance(cls, 0);
        String[] fields = string.split(Pattern.quote(sep));
        Object result = Array.newInstance(cls, fields.length);
        for (int i = 0; i < fields.length; ++i) {
            Array.set(result, i, safeCast(fields[i], cls));
        }
        return result;
    }

    /*
    * Split a string to a map
    * */
    public static <K, V> Map<K, V> split(String string, String sep, String kvSep, Class<?> cls, Class<K> kCls, Class<V> vCls) throws IllegalAccessException, InstantiationException, UnsupportedCastTypeException {
        Map<K, V> result = (Map<K, V>) cls.newInstance();
        if (string == null || string.length() == 0) return result;
        for (String kvString : string.split(Pattern.quote(sep))) {
            String[] fields = kvString.split(Pattern.quote(kvSep));
            K key = safeCast(fields[0], kCls);
            V val = safeCast(fields[1], vCls);
            result.put(key, val);
        }
        return result;
    }

    /*
    * Join a object array into a string
    * */
    public static String join(String sep, Object[] objects) {
        if (objects == null) return "";
        String[] strings = new String[objects.length];
        for (int i = 0; i < objects.length; ++i) {
            strings[i] = String.valueOf(objects[i]);
        }
        return String.join(sep, strings);
    }

    /*
    * Join a map into a string
    * */
    public static String join(String sep, String kvSep, Map map) {
        if (map == null) return "";
        StringBuilder sb = new StringBuilder();
        for (Object key : map.keySet()) {
            if (sb.length() != 0) {
                sb.append(sep);
            }
            sb.append(key);
            sb.append(kvSep);
            sb.append(map.get(key));
        }
        return sb.toString();
    }
}
