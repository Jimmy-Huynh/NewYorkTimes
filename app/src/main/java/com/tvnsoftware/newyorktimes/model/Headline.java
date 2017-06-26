package com.tvnsoftware.newyorktimes.model;

import java.io.Serializable;

/**
 * Created by TamHH on 6/23/2017.
 */

public class Headline implements Serializable {
    private String main;
    private String print_headline;

    public Headline() {
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPrint_headline() {
        return print_headline;
    }

    public void setPrint_headline(String print_headline) {
        this.print_headline = print_headline;
    }
}
