package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.GejalaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.GejalaListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GejalaPresenter implements GejalaContract.Presenter {
    private GejalaContract.View mView;
    private ApiInterface mApi;
    private GejalaListTask mGejalaListTask;

    public GejalaPresenter(GejalaContract.View mView, ApiInterface mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    @Override
    public void doGetGejalaList() {
        if (mGejalaListTask == null || mGejalaListTask.getStatus() != AsyncTask.Status.RUNNING) {
            mGejalaListTask = new GejalaListTask(mView, mApi);
            mGejalaListTask.execute();
        }
    }

    @Override
    public void deleteGejala(String kode) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.deleteGejala(kode);
        responsesCall.enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(Call<Responses> call, Response<Responses> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        mView.getResponse(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Responses> call, Throwable t) {
                mView.hideLoading();
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
    }
}
