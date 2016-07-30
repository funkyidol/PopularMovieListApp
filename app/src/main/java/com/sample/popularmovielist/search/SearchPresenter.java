package com.sample.popularmovielist.search;

import com.sample.popularmovielist.model.MovieRepoImpl;
import com.sample.popularmovielist.model.MoviesRepo;
import com.sample.popularmovielist.model.pojo.MoviePojo;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by kshitij on 28/7/16.
 */
public class SearchPresenter implements SearchContract.UserActionListener {

    SearchContract.View view;
    MoviesRepo moviesRepo = new MovieRepoImpl();

    public SearchPresenter(SearchContract.View view){
        this.view = view;
    }

    @Override
    public void loadData(String search, int page) {
        moviesRepo.searchMovies(search, page, new MoviesRepo.GetPopularMoviesCallback() {
            @Override
            public void onSuccess(ArrayList<MoviePojo> moviePojosArray) {
                view.populateMovies(moviePojosArray);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Timber.e(throwable, throwable.getMessage());
            }
        });
    }

}
