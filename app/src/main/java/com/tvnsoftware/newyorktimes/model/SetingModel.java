package com.tvnsoftware.newyorktimes.model;

/**
 * Created by Thieusike on 6/27/2017.
 */

public class SetingModel {
    private String beginDate;
    private String sort;
    private boolean arts;
    private boolean fashion;
    private boolean sports;

    public SetingModel() {
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isArts() {
        return arts;
    }

    public void setArts(boolean arts) {
        this.arts = arts;
    }

    public boolean isFashion() {
        return fashion;
    }

    public void setFashion(boolean fashion) {
        this.fashion = fashion;
    }

    public boolean isSports() {
        return sports;
    }

    public void setSports(boolean sports) {
        this.sports = sports;
    }
}
