package com.zk.rxjava.rxjava_retrofit;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zk.rxjava.GsonUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: ZK.
 * date:   On 2018/5/11.
 */
public class HttpCreator {


    //http://120.76.205.241:8000/tools/phone_number_ascription?phoneNumber=13610100000&apikey
    // =JQ4iONjs1LBg60Ghgj842cKvjgVE7dDRXfBpxsvWTrgP16hY5RtOaVgqy1Wky7MT


    public final static String PHONENUMBER_URL = "http://120.76.205.241:8000";


    private static Retrofit createRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        return new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(PHONENUMBER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static <T> T createApi(Class<T> tClass) {
        return createRetrofit().create(tClass);
    }


}
