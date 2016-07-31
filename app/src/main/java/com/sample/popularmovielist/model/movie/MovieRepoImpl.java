package com.sample.popularmovielist.model.movie;

import com.sample.popularmovielist.model.movie.pojo.MoviePojo;
import com.sample.popularmovielist.utils.Constants;
import com.sample.popularmovielist.utils.RetrofitHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by kshitij on 28/7/16.
 */
public class MovieRepoImpl implements MoviesRepo {
    Call<ArrayList<MoviePojo>> searchMovieCall;
    private GetPopularMoviesCallback getPopularMoviesCallback;
    Callback<ArrayList<MoviePojo>> callback = new Callback<ArrayList<MoviePojo>>() {
        @Override
        public void onResponse(Call<ArrayList<MoviePojo>> call,
                               Response<ArrayList<MoviePojo>> response) {
            if (response.body() != null && response.body().size() > 0) {
                getPopularMoviesCallback.onSuccess(response.body());
            }
        }

        @Override
        public void onFailure(Call<ArrayList<MoviePojo>> call, Throwable t) {
            getPopularMoviesCallback.onFailure(t);
        }
    };

    @Override
    public void getPopularMovies(int page,
                                 final GetPopularMoviesCallback getPopularMoviesCallback) {
        Retrofit retrofit = RetrofitHelper.getInstance(null).getRetrofit();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        movieApiService.popularMovies(Constants.ApiConsts.API_VERSION,
                Constants.ApiConsts.API_KEY,
                Constants.ApiConsts.LIMIT,
                page).enqueue(new Callback<ArrayList<MoviePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<MoviePojo>> call,
                                   Response<ArrayList<MoviePojo>> response) {
                if (response.body() != null && response.body().size() > 0) {
                    getPopularMoviesCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MoviePojo>> call, Throwable t) {
                getPopularMoviesCallback.onFailure(t);
            }
        });
    }

    @Override
    public void searchMovies(String search,
                             int page,
                             final GetPopularMoviesCallback getPopularMoviesCallback) {
        this.getPopularMoviesCallback = getPopularMoviesCallback;
        Retrofit retrofit = RetrofitHelper.getInstance(null).getRetrofit();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        //If call is already under execution, then cancel it
        if (searchMovieCall != null && searchMovieCall.isExecuted()) searchMovieCall.cancel();

        if (searchMovieCall == null || searchMovieCall.isCanceled()) {
            searchMovieCall = movieApiService.searchMovies(Constants.ApiConsts.API_VERSION,
                    Constants.ApiConsts.API_KEY,
                    search,
                    Constants.ApiConsts.LIMIT,
                    page);
        }
        searchMovieCall.enqueue(callback);
    }
}
