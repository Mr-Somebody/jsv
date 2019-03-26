package com.chewords.jsv;
/*
 *
 * @Author Joey
 * @Date 26/03/2019 10:03:57
 * @Desc
 *
 */

import java.lang.reflect.Array;

public class Test {

    public static <T> T[] test(Class<T> cls) {
        return (T[]) Array.newInstance(cls, 0);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Array.newInstance(int.class, 0).getClass().getName());
        System.out.println(Class.forName("[I"));
        System.out.println(Class.forName("[Lcom.chewords.jsv.Jsv;"));
    }
}
