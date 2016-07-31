package com.sample.popularmovielist.model.search;

import com.sample.popularmovielist.model.search.pojo.SearchMoviePojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by kshitij on 28/7/16.
 */
public interface SearchMovieRepo {

    void getPopularMovies(int page, SearchMoviesCallback searchMoviesCallback);

    void searchMovies(String search, int page, SearchMoviesCallback searchMoviesCallback);

    interface SearchMoviesCallback {
        void onSuccess(ArrayList<SearchMoviePojo> searchMoviePojosArray);

        void onFailure(Throwable throwable);
    }

    interface MovieApiService {
        @Headers("Content-Type: application/json")
        @GET("movies/popular?extended=full,images")
        Call<ArrayList<SearchMoviePojo>> popularMovies(@Header("trakt-api-version") int apiVersion,
                                                       @Header("trakt-api-key") String apiKey,
                                                       @Query("limit") int limit,
                                                       @Query("page") int page);

        @Headers("Content-Type: application/json")
        @GET("search/movie?extended=full,images")
        Call<ArrayList<SearchMoviePojo>> searchMovies(@Header("trakt-api-version") int apiVersion,
                                                      @Header("trakt-api-key") String apiKey,
                                                      @Query("query") String query,
                                                      @Query("limit") int limit,
                                                      @Query("page") int page);
    }
}
