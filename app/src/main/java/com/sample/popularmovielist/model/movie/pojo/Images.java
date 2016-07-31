
package com.sample.popularmovielist.model.movie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("fanart")
    @Expose
    private Fanart fanart;
    @SerializedName("poster")
    @Expose
    private Poster poster;
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @SerializedName("clearart")
    @Expose
    private Clearart clearart;
    @SerializedName("banner")
    @Expose
    private Banner banner;
    @SerializedName("thumb")
    @Expose
    private Thumb thumb;

    /**
     * 
     * @return
     *     The fanart
     */
    public Fanart getFanart() {
        return fanart;
    }

    /**
     * 
     * @param fanart
     *     The fanart
     */
    public void setFanart(Fanart fanart) {
        this.fanart = fanart;
    }

    /**
     * 
     * @return
     *     The poster
     */
    public Poster getPoster() {
        return poster;
    }

    /**
     * 
     * @param poster
     *     The poster
     */
    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    /**
     * 
     * @return
     *     The logo
     */
    public Logo getLogo() {
        return logo;
    }

    /**
     * 
     * @param logo
     *     The logo
     */
    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    /**
     * 
     * @return
     *     The clearart
     */
    public Clearart getClearart() {
        return clearart;
    }

    /**
     * 
     * @param clearart
     *     The clearart
     */
    public void setClearart(Clearart clearart) {
        this.clearart = clearart;
    }

    /**
     * 
     * @return
     *     The banner
     */
    public Banner getBanner() {
        return banner;
    }

    /**
     * 
     * @param banner
     *     The banner
     */
    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    /**
     * 
     * @return
     *     The thumb
     */
    public Thumb getThumb() {
        return thumb;
    }

    /**
     * 
     * @param thumb
     *     The thumb
     */
    public void setThumb(Thumb thumb) {
        this.thumb = thumb;
    }

}
