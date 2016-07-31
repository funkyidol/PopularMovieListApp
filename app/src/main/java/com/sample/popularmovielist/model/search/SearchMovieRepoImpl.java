package com.sample.popularmovielist.model.search;

import com.sample.popularmovielist.model.search.pojo.SearchMoviePojo;
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
public class SearchMovieRepoImpl implements SearchMovieRepo {
    Call<ArrayList<SearchMoviePojo>> searchMovieCall;
    private SearchMoviesCallback searchMoviesCallback;
    Callback<ArrayList<SearchMoviePojo>> callback = new Callback<ArrayList<SearchMoviePojo>>() {
        @Override
        public void onResponse(Call<ArrayList<SearchMoviePojo>> call,
                               Response<ArrayList<SearchMoviePojo>> response) {
            if (response.body() != null && response.body().size() > 0) {
                searchMoviesCallback.onSuccess(response.body());
            }
        }

        @Override
        public void onFailure(Call<ArrayList<SearchMoviePojo>> call, Throwable t) {
            searchMoviesCallback.onFailure(t);
        }
    };

    @Override
    public void getPopularMovies(int page,
                                 final SearchMoviesCallback searchMoviesCallback) {
        Retrofit retrofit = RetrofitHelper.getInstance(null).getRetrofit();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        movieApiService.popularMovies(Constants.ApiConsts.API_VERSION,
                Constants.ApiConsts.API_KEY,
                Constants.ApiConsts.LIMIT,
                page).enqueue(new Callback<ArrayList<SearchMoviePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchMoviePojo>> call,
                                   Response<ArrayList<SearchMoviePojo>> response) {
                if (response.body() != null && response.body().size() > 0) {
                    searchMoviesCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchMoviePojo>> call, Throwable t) {
                searchMoviesCallback.onFailure(t);
            }
        });
    }

    @Override
    public void searchMovies(String search,
                             int page,
                             final SearchMoviesCallback searchMoviesCallback) {
        this.searchMoviesCallback = searchMoviesCallback;
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
