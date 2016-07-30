package com.sample.popularmovielist.model;

import com.example.trivagoapp.model.pojo.MoviePojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by kshitij on 28/7/16.
 */
public interface MoviesRepo {

    void getPopularMovies(int page, GetPopularMoviesCallback getPopularMoviesCallback);

    interface GetPopularMoviesCallback{
        void onSuccess(ArrayList<MoviePojo> moviePojosArray);

        void onFailure(Throwable throwable);
    }

    interface MovieApiService {
        @Headers("Content-Type: application/json")
        @GET("movies/popular?extended=full,images")
        Call<ArrayList<MoviePojo>> popularMovies(@Header("trakt-api-version") int apiVersion,
                                                 @Header("trakt-api-key") String apiKey,
                                                 @Query("limit") int limit,
                                                 @Query("page") int page);
    }
}
