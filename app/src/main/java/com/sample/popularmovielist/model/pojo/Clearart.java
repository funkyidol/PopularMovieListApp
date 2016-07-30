
package com.sample.popularmovielist.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clearart {

    @SerializedName("full")
    @Expose
    private String full;

    /**
     * 
     * @return
     *     The full
     */
    public String getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    public void setFull(String full) {
        this.full = full;
    }

}
