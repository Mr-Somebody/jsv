package com.chewords.jsv;
/*
 *
 * @Author Joey
 * @Date 26/03/2019 11:52:29
 * @Desc
 *
 */

import com.chewords.jsv.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Person {
    @JsvExpose
    private String name;

    @JsvExpose
    private int age;

    @JsvExpose
    @JsvName(name = "sibs")
    @JsvArray
    private String[] siblings;

    @JsvExpose
    @JsvName(name = "nbs")
    @JsvList(type = LinkedList.class)
    private List neighbours;

    @JsvExpose
    @JsvMap(vType = Integer.class)
    private Map<String, Double> scores;
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSiblings() {
        return siblings;
    }

    public void setSiblings(String[] siblings) {
        this.siblings = siblings;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List neighbours) {
        this.neighbours = neighbours;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }
}
