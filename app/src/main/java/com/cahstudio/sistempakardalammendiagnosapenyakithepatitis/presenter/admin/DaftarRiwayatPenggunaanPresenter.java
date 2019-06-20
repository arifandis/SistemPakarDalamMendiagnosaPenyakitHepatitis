package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.DaftarRiwayatPenggunaanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.PenggunaListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarRiwayatPenggunaanPresenter implements DaftarRiwayatPenggunaanContract.Presenter {
    private DaftarRiwayatPenggunaanContract.View mView;
    private ApiInterface mApi;
    private PenggunaListTask mPenggunaListTask;

    public DaftarRiwayatPenggunaanPresenter(DaftarRiwayatPenggunaanContract.View mView, ApiInterface mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    @Override
    public void doGetDaftarRiwayat() {
        if (mPenggunaListTask == null || mPenggunaListTask.getStatus() != AsyncTask.Status.RUNNING) {
            mPenggunaListTask = new PenggunaListTask(mView, mApi);
            mPenggunaListTask.execute();
        }
    }

    @Override
    public void doDeleteDaftarRiwayat(int id) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.deletePengguna(id);
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
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
    }
}
