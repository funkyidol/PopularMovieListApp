package com.sample.popularmovielist.main;

import com.sample.popularmovielist.model.pojo.MoviePojo;

import java.util.ArrayList;

/**
 * Created by kshitij on 28/7/16.
 */
public interface MainContract {
    interface View {
        void populateMovies(ArrayList<MoviePojo> moviePojoArrayList);
    }

    interface UserActionListener {
        void loadData(int page);
    }
}
