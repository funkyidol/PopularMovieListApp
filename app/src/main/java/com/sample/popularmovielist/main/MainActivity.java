package com.sample.popularmovielist.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.popularmovielist.R;
import com.sample.popularmovielist.model.pojo.MoviePojo;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        userActionListener.loadData(currentPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void populateMovies(ArrayList<MoviePojo> moviePojoArrayList) {
        isLoading = false;
        if (moviesList == null) {
            moviesList = new ArrayList<>(moviePojoArrayList);
            movieRecyclerAdapter = new MovieRecyclerAdapter();
            setupRecyclerView();
        } else {
            int oldSize = moviesList.size();
            moviesList.addAll(moviePojoArrayList);
            movieRecyclerAdapter.notifyItemInserted(oldSize);
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

    private class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter
            .ViewHolder> {

        @Override
        public MovieRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.item_movie, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MovieRecyclerAdapter.ViewHolder holder, int position) {
            holder.tvTitle.setText(moviesList.get(position).getTitle());
//            holder.tvYear.setText(moviesList.get(position).getYear());
            holder.tvOverview.setText(moviesList.get(position).getOverview());
        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivThumbnail;
            TextView tvTitle;
            TextView tvYear;
            TextView tvOverview;

            public ViewHolder(View view) {
                super(view);

                ivThumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                tvYear = (TextView) view.findViewById(R.id.tv_year_released);
                tvOverview = (TextView) view.findViewById(R.id.tv_overview);
            }
        }

    }
}
