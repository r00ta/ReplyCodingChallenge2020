package com.redhat.developer.types;

public class WorkPotential {
    public Integer unionScore;
    public Integer distinctScore;

    public Integer bonusScore;

    public Integer workPotential;

    public WorkPotential(Integer unionScore, Integer distinctScore, Integer bonusScore){
        this.unionScore = unionScore;
        this.distinctScore = distinctScore;
        this.bonusScore = bonusScore;
        this.workPotential = distinctScore * (unionScore - distinctScore) + bonusScore;
    }
}
