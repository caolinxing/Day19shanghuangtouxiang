package com.example.mvp.model;

import com.example.UploadBean;
import com.example.apiserver.ApiServer;
import com.example.mvp.contract.UpLoad_Contract;
import com.example.utils.RetrofitUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpLoad_Model implements UpLoad_Contract.Model {
    @Override
    public void setData(File file, final UpLoad_Contract.GetDataState callBack) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RetrofitUtils.newInstance("https://www.zhaoapi.cn/")
                .create(ApiServer.class)
                .rxUpload("71", body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<UploadBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {
                        callBack.onSuccessful(uploadBean);
                    }
                });
    }
}
