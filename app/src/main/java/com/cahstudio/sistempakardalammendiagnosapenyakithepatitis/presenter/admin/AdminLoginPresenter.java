package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.AdminLoginContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Admin;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginPresenter implements AdminLoginContract.Presenter {
    private AdminLoginContract.View mView;
    private ApiInterface mApi;

    public AdminLoginPresenter(AdminLoginContract.View mView, ApiInterface mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    @Override
    public void doLogin(Admin admin) {
        mView.showLoading();
        Call<Responses> responsesCall = mApi.login(admin);
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
