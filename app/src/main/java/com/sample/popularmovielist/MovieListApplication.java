package com.sample.popularmovielist;

import android.app.Application;

import timber.log.Timber;
import com.sample.popularmovielist.utils.RetrofitHelper;

/**
 * Created by kshitij on 8/3/16.
 */
public class MovieListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        initRetrofit();

    }

    private void initRetrofit() {
        RetrofitHelper.getInstance(getApplicationContext());
    }

}
