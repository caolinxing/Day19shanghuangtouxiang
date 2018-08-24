package com.example.apiserver;

import com.example.UploadBean;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiServer {
    @Multipart
    @POST("file/upload")
    Observable<UploadBean> rxUpload (@Query("uid")String uid, @Part MultipartBody.Part file);
}
