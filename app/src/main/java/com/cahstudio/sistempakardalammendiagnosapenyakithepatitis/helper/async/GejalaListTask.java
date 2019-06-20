package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.DetailRiwayatContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.GejalaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.PengetahuanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahGejalaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahPengetahuanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.DiagnosaContract;

import java.util.List;

import retrofit2.Response;

public class GejalaListTask extends AsyncTask<Void,Integer, List<Gejala>> {
    private DiagnosaContract.View mDiagnosaView;
    private GejalaContract.View mGejalaView;
    private TambahPengetahuanContract.View mTambahPengetahuanView;
    private DetailRiwayatContract.View mDetailView;
    private ApiInterface mApi;

    public GejalaListTask(DiagnosaContract.View mDiagnosaView, ApiInterface mApi) {
        this.mDiagnosaView = mDiagnosaView;
        this.mApi = mApi;
    }

    public GejalaListTask(GejalaContract.View mGejalaView, ApiInterface mApi) {
        this.mGejalaView = mGejalaView;
        this.mApi = mApi;
    }

    public GejalaListTask(TambahPengetahuanContract.View mTambahPengetahuanView, ApiInterface mApi) {
        this.mTambahPengetahuanView = mTambahPengetahuanView;
        this.mApi = mApi;
    }

    public GejalaListTask(DetailRiwayatContract.View mDetailView, ApiInterface mApi) {
        this.mDetailView = mDetailView;
        this.mApi = mApi;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mGejalaView != null){
            mGejalaView.showLoading();
        }else if (mTambahPengetahuanView != null){
            mTambahPengetahuanView.showLoading();
        }
    }

    @Override
    protected List<Gejala> doInBackground(Void... voids) {
        try {
            Response<List<Gejala>> response = mApi.getGejalaList().execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Gejala> gejalas) {
        super.onPostExecute(gejalas);
        if (mDiagnosaView != null){
            mDiagnosaView.hideLoading();
        }else if (mGejalaView != null){
            mGejalaView.hideLoading();
        }else if (mTambahPengetahuanView != null){
            mTambahPengetahuanView.hideLoading();
        }

        if (gejalas != null){
            if (mDiagnosaView != null){
                mDiagnosaView.retrieveGejalaList(gejalas);
            }else if (mGejalaView != null){
                mGejalaView.getGejalaList(gejalas);
            }else if (mTambahPengetahuanView != null){
                mTambahPengetahuanView.getGejalaList(gejalas);
            }else if (mDetailView != null){
                mDetailView.getGejalaList(gejalas);
            }
        }
    }
}
