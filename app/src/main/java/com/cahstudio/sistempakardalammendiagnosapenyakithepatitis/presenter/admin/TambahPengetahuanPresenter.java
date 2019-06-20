package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahPengetahuanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.GejalaListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.PenyakitListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahPengetahuanPresenter implements TambahPengetahuanContract.Presenter {
    private TambahPengetahuanContract.View mView;
    private ApiInterface mApi;
    private GejalaListTask mGejalaListTask;
    private PenyakitListTask mPenyakitListTask;

    public TambahPengetahuanPresenter(TambahPengetahuanContract.View mView, ApiInterface mApi) {
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
    public void doGetPenyakitList() {
        if (mPenyakitListTask == null || mPenyakitListTask.getStatus() != AsyncTask.Status.RUNNING) {
            mPenyakitListTask = new PenyakitListTask(mView, mApi);
            mPenyakitListTask.execute();
        }
    }

    @Override
    public void doAddPengetahuan(Pengetahuan pengetahuan) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.tambahPengetahuan(pengetahuan);
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

    @Override
    public void doUpdatePengetahuan(Pengetahuan pengetahuan) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.updatePengetahuan(pengetahuan);
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

    @Override
    public void doGetPengetahuanById(int id) {
        mView.showLoading();
        Call<Pengetahuan> pengetahuanCall = mApi.getPengetahuanById(id);
        pengetahuanCall.enqueue(new Callback<Pengetahuan>() {
            @Override
            public void onResponse(Call<Pengetahuan> call, Response<Pengetahuan> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        mView.getPengetahuan(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Pengetahuan> call, Throwable t) {
                mView.hideLoading();
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
    }
}
