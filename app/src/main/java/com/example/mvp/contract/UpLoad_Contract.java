package com.example.mvp.contract;

import com.example.UploadBean;

import java.io.File;

public interface UpLoad_Contract {
    interface Model {
        void setData(File file,GetDataState callBack);
    }

    interface GetDataState {
        void onSuccessful(UploadBean uploadBean);
        void onError(String error);
    }
    interface View extends GetDataState {
        @Override
        void onSuccessful(UploadBean uploadBean);

        @Override
        void onError(String error);

        File setFile();
    }

    interface Presenter {
        void setData();
        void onDestory();
    }
}
