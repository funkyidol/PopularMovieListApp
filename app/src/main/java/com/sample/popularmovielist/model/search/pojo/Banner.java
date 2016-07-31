
package com.sample.popularmovielist.model.search.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("full")
    @Expose
    private Object full;

    /**
     * 
     * @return
     *     The full
     */
    public Object getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    public void setFull(Object full) {
        this.full = full;
    }

}
