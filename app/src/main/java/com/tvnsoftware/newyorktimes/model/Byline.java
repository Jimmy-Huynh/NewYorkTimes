package com.tvnsoftware.newyorktimes.model;

/**
 * Created by TamHH on 6/23/2017.
 */

class Byline {
    //    private List<object> person;
    private String original;
    private String organization;

    public Byline() {
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
