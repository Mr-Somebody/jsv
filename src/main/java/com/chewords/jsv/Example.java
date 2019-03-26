package com.chewords.jsv;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 10:26:57
 * @Desc
 *
 */

import com.chewords.jsv.action.Action;
import com.chewords.jsv.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Example {

    @JsvExpose
    private int age;

    @JsvExpose
    @JsvName(name = "sib")
    @JsvArray(sep = "|")
    private String[] siblings = new String[]{"Mike", "Ben"};

    @JsvExpose
    @JsvName(name = "cats")
    @JsvList(sep = "|", type = LinkedList.class)
    private List cats = new LinkedList();

    @JsvExpose
    @JsvMap(sep = "|")
    private Map dogs = new HashMap();

    @JsvExpose
    @JsvName(name = "rname")
    @JsvAction(cls = MinusOneAction.class)
    private int rname;


    public Example() {
        this.cats.add("kitty_1");
        this.cats.add("kitty_2");
        this.dogs.put("doggy_1", 4);
        this.dogs.put("doggy_2", 5);
    }

    public static void main(String[] args) throws Exception {
        Jsv jsv = new Jsv();
        JsvRecord record = jsv.dump(new Example());
        System.out.println(String.join(",", record.getHeaders()));
        System.out.println(String.join(",", record.getValues()));
    }
    ///

    public int getAge() {
        return age;
    }
}

class MinusOneAction implements Action {

    @Override
    public Object load(JsvRecord record) throws Exception {
        return null;
    }

    @Override
    public String dump(Object value) throws Exception {
        Example example = (Example) value;
        return String.valueOf(example.getAge() - 1);
    }

    ///

}
