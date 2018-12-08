package com.ibatis.mysql;


import java.sql.Date;

/**
 * Created by Administrator on 2018/12/8 0008.
 */
public class Student {
    private int id;
    private String name;
    private Date birth;
    private float score;

    public Student(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    @Override
    public String toString(){
        return "id="+id+"\t name"+name+"\t birth="+birth+"\t score="+score+"\n";
    }
}
