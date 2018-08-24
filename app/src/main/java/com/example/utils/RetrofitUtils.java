package com.example.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final int READ_TIME = 60;//读取超时时间,单位  秒
    private static final int CONNECT_TIME = 12;//连接超时时间,单位  秒

	private RetrofitUtils() {
    }
	
    private static Retrofit mRetrofit;

    public static Retrofit  newInstance(String url){
		mRetrofit=null;
		 if (mRetrofit == null) {
			 synchronized (RetrofitUtils.class) {
                if (mRetrofit == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME,TimeUnit.SECONDS)
                            .build();
                    mRetrofit = new Retrofit.Builder()
                            .client(client)//添加一个client,不然retrofit会自己默认添加一个
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
		 }
        return mRetrofit;
    }
}
