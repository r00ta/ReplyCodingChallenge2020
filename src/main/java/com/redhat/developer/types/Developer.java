package com.redhat.developer.types;

import java.util.HashSet;
import java.util.Set;

public class Developer {

    public Integer id;

    public String company;

    public Integer bonus;

    public Integer numberOfSkills;

    public Set<String> skills;

    public Integer row;

    public Integer col;

    public boolean placed;

    public Developer(Integer id, String company, Integer bonus, Set<String> skills){
        this.company = company;
        this.id = id;
        this.bonus = bonus;
        this.skills = skills;
        this.numberOfSkills = skills.size();
    }

    public static WorkPotential calculateWorkPotential(Developer d1, Developer d2){
        Set<String> allSkills = new HashSet<>(d1.skills);
        allSkills.addAll(d2.skills);

        Set<String> uniqueSkills = new HashSet<>(d1.skills);
        uniqueSkills.retainAll(d2.skills);

        Integer bonus = 0;
        if (d1.company.equals(d2.company)){
            bonus = d1.bonus * d2.bonus;
        }

        return new WorkPotential(allSkills.size(), uniqueSkills.size(), bonus);
    }

}
