
package com.sample.popularmovielist.model.search.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("ids")
    @Expose
    private Ids ids;
    @SerializedName("tagline")
    @Expose
    private Object tagline;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("released")
    @Expose
    private String released;
    @SerializedName("runtime")
    @Expose
    private Object runtime;
    @SerializedName("trailer")
    @Expose
    private Object trailer;
    @SerializedName("homepage")
    @Expose
    private Object homepage;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("votes")
    @Expose
    private int votes;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("available_translations")
    @Expose
    private List<String> availableTranslations = new ArrayList<String>();
    @SerializedName("genres")
    @Expose
    private List<String> genres = new ArrayList<String>();
    @SerializedName("certification")
    @Expose
    private Object certification;
    @SerializedName("images")
    @Expose
    private Images images;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The year
     */
    public int getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The ids
     */
    public Ids getIds() {
        return ids;
    }

    /**
     * 
     * @param ids
     *     The ids
     */
    public void setIds(Ids ids) {
        this.ids = ids;
    }

    /**
     * 
     * @return
     *     The tagline
     */
    public Object getTagline() {
        return tagline;
    }

    /**
     * 
     * @param tagline
     *     The tagline
     */
    public void setTagline(Object tagline) {
        this.tagline = tagline;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The released
     */
    public String getReleased() {
        return released;
    }

    /**
     * 
     * @param released
     *     The released
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Object getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(Object runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The trailer
     */
    public Object getTrailer() {
        return trailer;
    }

    /**
     * 
     * @param trailer
     *     The trailer
     */
    public void setTrailer(Object trailer) {
        this.trailer = trailer;
    }

    /**
     * 
     * @return
     *     The homepage
     */
    public Object getHomepage() {
        return homepage;
    }

    /**
     * 
     * @param homepage
     *     The homepage
     */
    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The availableTranslations
     */
    public List<String> getAvailableTranslations() {
        return availableTranslations;
    }

    /**
     * 
     * @param availableTranslations
     *     The available_translations
     */
    public void setAvailableTranslations(List<String> availableTranslations) {
        this.availableTranslations = availableTranslations;
    }

    /**
     * 
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * 
     * @return
     *     The certification
     */
    public Object getCertification() {
        return certification;
    }

    /**
     * 
     * @param certification
     *     The certification
     */
    public void setCertification(Object certification) {
        this.certification = certification;
    }

    /**
     * 
     * @return
     *     The images
     */
    public Images getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

}
