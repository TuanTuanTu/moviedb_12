package com.training.vungoctuan.moviedb.screen;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.local.MovieLocalDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.NetworkReceiver;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vungoctuan on 2/28/18.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private NetworkReceiver mNetworkReceiver;
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
            mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.ApiRequestUrl.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        }
        return mRetrofit;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRetrofitInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mNetworkReceiver == null) return;
        registerReceiver(mNetworkReceiver,
            new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void initNetworkBroadcastReceiver(NetworkReceiver.NetworkStateCallback callback) {
        mNetworkReceiver = new NetworkReceiver(callback);
    }

    public MovieRepository getMovieRepository() {
        return MovieRepository.getInstance(
            MovieRemoteDataSource.getInstance(),
            MovieLocalDataSource.getInstance()
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mNetworkReceiver == null) return;
        unregisterReceiver(mNetworkReceiver);
    }
}
