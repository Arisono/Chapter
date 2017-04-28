package com.ivangong.commonbase.net;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gongshenghu on 16/3/9.
 */
public class RetrofitWrapper {

  private Retrofit retrofitClient;
  private static RetrofitWrapper mInstance;
  private Retrofit.Builder mRetrofitBuilder;
  private String mBaseUrl;

  private RetrofitWrapper() {
    if (null != mInstance) {
      throw new RuntimeException();
    }
  }

  public static synchronized RetrofitWrapper getInstance() {
    if (null == mInstance) {
      mInstance = new RetrofitWrapper();
    }
    return mInstance;
  }

  public void init(@NonNull String baseUrl) {
    this.mBaseUrl = baseUrl;
  }

  public Retrofit getRetrofit() {
    if (TextUtils.isEmpty(mBaseUrl)) {
      throw new RuntimeException(
          "Please init the Retrofit Wrapper And MUST set the BaseUrl first!");
    }
    if (null == retrofitClient || TextUtils.equals(retrofitClient.baseUrl().url().toString(),
        mBaseUrl)) {
      retrofitClient = getRetrofitBuilder().baseUrl(mBaseUrl).build();
    }
    return retrofitClient;
  }

  public Retrofit getRetrofitByBaseUrl(@NonNull String baseUrl) {
    if (null == retrofitClient || TextUtils.equals(retrofitClient.baseUrl().url().toString(),
        baseUrl)) {
      retrofitClient = getRetrofitBuilder().baseUrl(baseUrl).build();
    }
    return retrofitClient;
  }

  public Retrofit.Builder getRetrofitBuilder() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build();

    if (null == mRetrofitBuilder) {
      mRetrofitBuilder = new Retrofit.Builder().client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }
    return mRetrofitBuilder;
  }
}
