package com.tvnsoftware.newyorktimes.model;

import com.google.gson.annotations.SerializedName;
import com.tvnsoftware.newyorktimes.Utils.Contant;

import java.io.Serializable;

/**
 * Created by TamHH on 6/26/2017.
 */

public class Multimedia implements Serializable{
    @SerializedName("width")
    public int width;
    @SerializedName("url")
    public String url;
    @SerializedName("rank")
    public int rank;
    @SerializedName("height")
    public int height;
    @SerializedName("subtype")
    public String subtype;
    @SerializedName("legacy")
    public Legacy legacy;
    @SerializedName("type")
    public String type;

    public Multimedia() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return Contant.BASE_URL_IMAGES + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
