package com.lishi.baijiaxing.manager;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lishi.baijiaxing.classify.network.ClassifySerVice;
import com.lishi.baijiaxing.home.network.HomeService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/29.
 */
public class RetrofitManager {
    Retrofit mRetrofit;
    OkHttpClient mOkHttpClient;
    String BaseUrl = "http://api.k780.com:88";

    private HomeService mHomeService;

    public static RetrofitManager builder() {
        return new RetrofitManager();
    }

    public RetrofitManager() {
        init();
    }

    private void init() {
        synchronized (RetrofitManager.class) {
            if (mOkHttpClient == null) {
                mOkHttpClient = new OkHttpClient.Builder()
                        .addNetworkInterceptor(new StethoInterceptor())
                        .retryOnConnectionFailure(true)
                        .connectTimeout(15, TimeUnit.SECONDS).build();
            }
        }

        synchronized (RetrofitManager.class) {
            if (mRetrofit == null) {
                mRetrofit = new Retrofit.Builder()
                        .client(mOkHttpClient)
                        .baseUrl(BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
    }

    public <T> Object getHomeService(Class<T> name) {
        return mRetrofit.create(name);
    }
}
