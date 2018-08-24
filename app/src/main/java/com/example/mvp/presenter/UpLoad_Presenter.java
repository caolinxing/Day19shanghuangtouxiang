package com.example.mvp.presenter;

import com.example.UploadBean;
import com.example.mvp.contract.UpLoad_Contract;
import com.example.mvp.model.UpLoad_Model;

public class UpLoad_Presenter implements UpLoad_Contract.Presenter, UpLoad_Contract.GetDataState {
    UpLoad_Contract.View view;
    UpLoad_Contract.Model model;

    public UpLoad_Presenter(UpLoad_Contract.View view) {
        this.view = view;
        model = new UpLoad_Model();
    }

    @Override
    public void setData() {
        model.setData(view.setFile(),this);
    }

    @Override
    public void onDestory() {
        view=null;
    }

    @Override
    public void onSuccessful(UploadBean uploadBean) {
        view.onSuccessful(uploadBean);
    }

    @Override
    public void onError(String error) {
        view.onError(error);
    }
}
