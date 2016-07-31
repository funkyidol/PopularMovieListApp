package com.sample.popularmovielist.search;

import com.sample.popularmovielist.model.search.pojo.SearchMoviePojo;

import java.util.ArrayList;

/**
 * Created by kshitij on 28/7/16.
 */
public interface SearchContract {
    interface View {
        void populateMovies(ArrayList<SearchMoviePojo> moviePojoArrayList);
    }

    interface UserActionListener {
        void loadData(String search, int page);
    }
}
