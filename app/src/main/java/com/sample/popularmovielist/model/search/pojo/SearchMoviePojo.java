
package com.sample.popularmovielist.model.search.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMoviePojo {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("score")
    @Expose
    private float score;
    @SerializedName("movie")
    @Expose
    private Movie movie;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The score
     */
    public float getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * 
     * @return
     *     The movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * 
     * @param movie
     *     The movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
