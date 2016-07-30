package com.sample.popularmovielist.model;

import com.example.trivagoapp.model.pojo.MoviePojo;
import com.example.trivagoapp.utils.Constants;
import com.example.trivagoapp.utils.RetrofitHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by kshitij on 28/7/16.
 */
public class MovieRepoImpl implements MoviesRepo {
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
}
