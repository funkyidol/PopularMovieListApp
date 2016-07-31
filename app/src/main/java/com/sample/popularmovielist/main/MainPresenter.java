package com.sample.popularmovielist.main;

import com.sample.popularmovielist.model.movie.MovieRepoImpl;
import com.sample.popularmovielist.model.movie.MoviesRepo;
import com.sample.popularmovielist.model.movie.pojo.MoviePojo;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by kshitij on 28/7/16.
 */
public class MainPresenter implements MainContract.UserActionListener {

    MainContract.View view;
    MoviesRepo moviesRepo = new MovieRepoImpl();

    public MainPresenter(MainContract.View view){
        this.view = view;
    }

    @Override
    public void loadData(int page) {
        moviesRepo.getPopularMovies(page, new MoviesRepo.GetPopularMoviesCallback() {
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
