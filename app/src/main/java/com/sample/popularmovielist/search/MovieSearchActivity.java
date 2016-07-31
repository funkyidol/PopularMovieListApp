package com.sample.popularmovielist.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sample.popularmovielist.R;
import com.sample.popularmovielist.model.search.pojo.SearchMoviePojo;
import com.sample.popularmovielist.utils.SearchMovieRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MovieSearchActivity extends AppCompatActivity implements SearchContract.View {

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    protected SearchMovieRecyclerAdapter movieRecyclerAdapter;
    private RecyclerView.OnScrollListener onScrollListener;
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int previousTotal;
    private int firstVisibleItem;
    protected boolean isLoading = true;
    private int visibleThreshold = 5;
    private int currentPage = 1;

    private ArrayList<SearchMoviePojo> moviesList;

    private SearchView searchView;
    private SearchPresenter userActionListener;
    private String searchTerm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rvMovies = (RecyclerView) findViewById(R.id.rv_movies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        userActionListener = new SearchPresenter(this);

        userActionListener.loadData(searchTerm, currentPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(myActionMenuItem);
        SearchView.SearchAutoComplete tvSearch =
                (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        tvSearch.setTextColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Handle search query
                if (moviesList != null) moviesList.clear();
                if (movieRecyclerAdapter != null) movieRecyclerAdapter.notifyDataSetChanged();
                currentPage = 1;
                searchTerm = query;
                search(query);

                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //                if (query.length() > 3) {
                if (moviesList != null) moviesList.clear();
                if (movieRecyclerAdapter != null) movieRecyclerAdapter.notifyDataSetChanged();
                //                movieRecyclerAdapter = null;
                currentPage = 1;
                searchTerm = query;
                search(query);
                return true;
                //                }
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                //                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void search(String query) {
        //        currentPage = 1;
        isLoading = true;
        userActionListener.loadData(query, currentPage);
    }

    private void loadData() {
        isLoading = true;
        userActionListener.loadData(searchTerm, currentPage);
    }

    @Override
    public void populateMovies(ArrayList<SearchMoviePojo> moviePojoArrayList) {
        isLoading = false;
        if (moviePojoArrayList != null && moviePojoArrayList.size() > 0) {
            if (moviesList == null) {
                moviesList = new ArrayList<>(moviePojoArrayList);
                movieRecyclerAdapter =
                        new SearchMovieRecyclerAdapter(moviesList, MovieSearchActivity.this);
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
}
