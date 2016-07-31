
package com.sample.popularmovielist.model.search.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fanart {

    @SerializedName("full")
    @Expose
    private Object full;
    @SerializedName("medium")
    @Expose
    private Object medium;
    @SerializedName("thumb")
    @Expose
    private Object thumb;

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

    /**
     * 
     * @return
     *     The medium
     */
    public Object getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Object medium) {
        this.medium = medium;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public Object getThumb() {
        return thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(Object thumb) {
        this.thumb = thumb;
    }

}
