package com.tvnsoftware.newyorktimes.model;

/**
 * Created by TamHH on 6/23/2017.
 */

class Meta {
    private int hits;
    private int time;
    private int offset;

    public Meta() {
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
