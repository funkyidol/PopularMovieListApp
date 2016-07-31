package com.sample.popularmovielist.search;

import com.sample.popularmovielist.model.search.SearchMovieRepo;
import com.sample.popularmovielist.model.search.SearchMovieRepoImpl;
import com.sample.popularmovielist.model.search.pojo.SearchMoviePojo;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by kshitij on 28/7/16.
 */
public class SearchPresenter implements SearchContract.UserActionListener {

    SearchContract.View view;
    SearchMovieRepo searchMovieRepo = new SearchMovieRepoImpl();

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(String search, int page) {
        searchMovieRepo.searchMovies(search, page, new SearchMovieRepo.SearchMoviesCallback() {
            @Override
            public void onSuccess(ArrayList<SearchMoviePojo> moviePojosArray) {
                view.populateMovies(moviePojosArray);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Timber.e(throwable, throwable.getMessage());
            }
        });
    }

}
