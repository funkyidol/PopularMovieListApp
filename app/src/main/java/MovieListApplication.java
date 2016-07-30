import android.app.Application;

import com.example.trivagoapp.utils.RetrofitHelper;

import timber.log.Timber;

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
