
package com.sample.popularmovielist.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fanart {

    @SerializedName("full")
    @Expose
    private String full;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("thumb")
    @Expose
    private String thumb;

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

    /**
     * 
     * @return
     *     The medium
     */
    public String getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

}
