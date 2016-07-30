package com.sample.popularmovielist.search;

import com.sample.popularmovielist.model.pojo.MoviePojo;

import java.util.ArrayList;

/**
 * Created by kshitij on 28/7/16.
 */
public interface SearchContract {
    interface View {
        void populateMovies(ArrayList<MoviePojo> moviePojoArrayList);
    }

    interface UserActionListener {
        void loadData(String search, int page);
    }
}
