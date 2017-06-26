package com.tvnsoftware.newyorktimes.model;

/**
 * Created by TamHH on 6/23/2017.
 */

public class Article {
    public Response response;
    public String status;
    public String copyright;

    public Article() {
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
