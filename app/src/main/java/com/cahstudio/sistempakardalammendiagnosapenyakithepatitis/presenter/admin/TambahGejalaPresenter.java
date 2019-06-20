package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahGejalaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahGejalaPresenter implements TambahGejalaContract.Presenter {
    private TambahGejalaContract.View mView;
    private ApiInterface mApi;

    public TambahGejalaPresenter(TambahGejalaContract.View mView, ApiInterface mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    @Override
    public void doAddGejala(Gejala gejala) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.tambahGejala(gejala);
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
    public void doUpdateGejala(Gejala gejala) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.updateGejala(gejala);
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
    public void doGetGejala(String kodeGejala) {
        mView.showLoading();
        Call<Gejala> gejalaCall = mApi.getGejalaByKode(kodeGejala);
        gejalaCall.enqueue(new Callback<Gejala>() {
            @Override
            public void onResponse(Call<Gejala> call, Response<Gejala> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        mView.getGejala(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Gejala> call, Throwable t) {
                mView.hideLoading();
                Responses responses = new Responses();
                responses.setStatus(0);
                responses.setMessage(t.getMessage());
                mView.getResponse(responses);
            }
        });
    }
}
