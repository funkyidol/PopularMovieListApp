package com.sample.popularmovielist.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.popularmovielist.R;
import com.sample.popularmovielist.model.search.pojo.SearchMoviePojo;

import java.util.ArrayList;

/**
 * Created by kshitij on 31/7/16.
 */
public class SearchMovieRecyclerAdapter extends RecyclerView.Adapter<SearchMovieRecyclerAdapter
        .ViewHolder> {

    private final ArrayList<SearchMoviePojo> moviesList;
    private Context context;

    public SearchMovieRecyclerAdapter(ArrayList<SearchMoviePojo> moviePojos, Context context) {
        this.moviesList = moviePojos;
        this.context = context;
    }

    @Override
    public SearchMovieRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchMovieRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(moviesList.get(position).getMovie().getTitle());
        holder.tvYear.setText(moviesList.get(position).getMovie().getYear() + "");
        holder.tvOverview.setText(moviesList.get(position).getMovie().getOverview());
        if (moviesList.get(position).getMovie().getImages().getThumb() != null &&
                !TextUtils.isEmpty(moviesList.get(position)
                        .getMovie()
                        .getImages()
                        .getThumb()
                        .getFull())) {
            ImageLoader.loadImage(moviesList.get(position)
                    .getMovie()
                    .getImages()
                    .getThumb()
                    .getFull(), holder.ivThumbnail, context);
        }
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