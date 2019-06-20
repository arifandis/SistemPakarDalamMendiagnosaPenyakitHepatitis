package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.DaftarRiwayatPenggunaanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;

import java.util.List;

import retrofit2.Response;

public class PenggunaListTask extends AsyncTask<Void, Integer, List<Pengguna>> {
    private DaftarRiwayatPenggunaanContract.View mView;
    private ApiInterface mApi;

    public PenggunaListTask(DaftarRiwayatPenggunaanContract.View mView, ApiInterface mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mView.showLoading();
    }

    @Override
    protected List<Pengguna> doInBackground(Void... voids) {
        try {
            Response<List<Pengguna>> response = mApi.getPenggunaList().execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Pengguna> penggunas) {
        super.onPostExecute(penggunas);
        mView.hideLoading();
        if (penggunas != null){
            mView.getDaftarRiwayat(penggunas);
        }
    }
}
