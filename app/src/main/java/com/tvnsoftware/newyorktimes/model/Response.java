package com.tvnsoftware.newyorktimes.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TamHH on 6/23/2017.
 */

public class Response implements Serializable {
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("docs")
    private List<Doc> docs;

    public Response() {
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }
}
