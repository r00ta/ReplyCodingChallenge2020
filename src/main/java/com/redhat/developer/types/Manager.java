package com.redhat.developer.types;

public class Manager {

    public Integer id;

    public String company;

    public Integer bonus;

    public Integer row;

    public Integer col;

    public boolean placed;

    public Manager(Integer id, String company, Integer bonus){
        this.company = company;
        this.id = id;
        this.bonus = bonus;
    }
}
