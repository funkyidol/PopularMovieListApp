package com.sample.popularmovielist.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sample.popularmovielist.R;
import com.sample.popularmovielist.model.movie.pojo.MoviePojo;
import com.sample.popularmovielist.search.MovieSearchActivity;
import com.sample.popularmovielist.utils.MovieRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.UserActionListener userActionListener;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    protected MovieRecyclerAdapter movieRecyclerAdapter;
    private RecyclerView.OnScrollListener onScrollListener;
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int previousTotal;
    private int firstVisibleItem;
    protected boolean isLoading = true;
    private int visibleThreshold = 5;
    private int currentPage = 1;

    private ArrayList<MoviePojo> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rvMovies = (RecyclerView) findViewById(R.id.rv_movies);
        userActionListener = new MainPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();*/
                startActivity(new Intent(MainActivity.this, MovieSearchActivity.class));
            }
        });

        userActionListener.loadData(currentPage);
    }

    @Override
    public void populateMovies(ArrayList<MoviePojo> moviePojoArrayList) {
        isLoading = false;
        if (moviePojoArrayList != null && moviePojoArrayList.size() > 0) {
            if (moviesList == null) {
                moviesList = new ArrayList<>(moviePojoArrayList);
                movieRecyclerAdapter = new MovieRecyclerAdapter(moviesList, MainActivity.this);
                setupRecyclerView();
            } else {
                int oldSize = moviesList.size();
                moviesList.addAll(moviePojoArrayList);
                movieRecyclerAdapter.notifyItemInserted(oldSize);
            }
        }
    }

    protected void setupRecyclerView() {
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //                Timber.i("OnScrolled");

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                //                Timber.i("TotalItemCount=" + totalItemCount);
                //                Timber.i("visibleItemCount=" + visibleItemCount);
                //                Timber.i("firstVisibleItem=" + firstVisibleItem);
                //                Timber.i("visibleThreshold=" + visibleThreshold);

                if (isLoading) {
                    if (totalItemCount > previousTotal) {
                        //                        isLoading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!isLoading && (totalItemCount - visibleItemCount) <=
                        (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
                    Timber.i("Reaching end. LoadMoreCalled");
                    currentPage++;
                    loadData();

                    //                    isLoading = true;
                }
            }

        };
        rvMovies.addOnScrollListener(onScrollListener);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(linearLayoutManager);
        rvMovies.setAdapter(movieRecyclerAdapter);
    }

    private void loadData() {
        userActionListener.loadData(currentPage);
    }
}
