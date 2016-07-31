package com.sample.popularmovielist.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.popularmovielist.R;
import com.sample.popularmovielist.model.movie.pojo.MoviePojo;

import java.util.ArrayList;

/**
 * Created by kshitij on 31/7/16.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private final ArrayList<MoviePojo> moviesList;
    private Context context;

    public MovieRecyclerAdapter(ArrayList<MoviePojo> moviePojos, Context context) {
        this.moviesList = moviePojos;
        this.context = context;
    }

    @Override
    public MovieRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(moviesList.get(position).getTitle());
        holder.tvYear.setText(moviesList.get(position).getYear() + "");
        holder.tvOverview.setText(moviesList.get(position).getOverview());
        /*if (!TextUtils.isEmpty(moviesList.get(position).getImages().getThumb().getFull())) {
            ImageLoader.loadImage(moviesList.get(position).getImages().getThumb().getFull(),
                    holder.ivThumbnail,
                    context);
        }*/
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