package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.DiagnosaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.GejalaListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.PenyakitListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosaPresenter implements DiagnosaContract.Presenter {
    private DiagnosaContract.View mView;
    private ApiInterface mApi;
    private PenyakitListTask mPenyakitListTask;

    public DiagnosaPresenter(DiagnosaContract.View mView, ApiInterface mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    @Override
    public void doRetrieveGejalaList() {
        mView.showLoading();
        Call<List<Gejala>> gejalaCall = mApi.getGejalaList();
        gejalaCall.enqueue(new Callback<List<Gejala>>() {
            @Override
            public void onResponse(Call<List<Gejala>> call, Response<List<Gejala>> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        mView.retrieveGejalaList(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Gejala>> call, Throwable t) {
                mView.hideLoading();
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
//        if (mGejalaListTask == null || mGejalaListTask.getStatus() != AsyncTask.Status.RUNNING) {
//            mGejalaListTask = new GejalaListTask(mView, mApi);
//            mGejalaListTask.execute();
//        }
    }

    @Override
    public void doRetrievePengetahuanList(String gejalas) {
        mView.showLoading();
        Call<List<Pengetahuan>> pengetahuanCall = mApi.getPengetahuanByGejala(gejalas);
        pengetahuanCall.enqueue(new Callback<List<Pengetahuan>>() {
            @Override
            public void onResponse(Call<List<Pengetahuan>> call, Response<List<Pengetahuan>> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        mView.retrievePengetahuanList(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pengetahuan>> call, Throwable t) {
                mView.hideLoading();
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
    }

    @Override
    public void doRetrievePenyakitList() {
        if (mPenyakitListTask == null || mPenyakitListTask.getStatus() != AsyncTask.Status.RUNNING) {
            mPenyakitListTask = new PenyakitListTask(mView, mApi);
            mPenyakitListTask.execute();
        }
    }

    @Override
    public void doSendDataPengguna(Pengguna pengguna) {
        Call<Responses> responsesCall = mApi.sendHasilDiagnosa(pengguna);
        responsesCall.enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(Call<Responses> call, Response<Responses> response) {
                if (!response.isSuccessful()){
                    Responses responses = new Responses();
                    responses.setStatus(0);
                    responses.setMessage("Internal server error");
                    mView.getResponse(responses);
                }
            }

            @Override
            public void onFailure(Call<Responses> call, Throwable t) {
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
    }
}
