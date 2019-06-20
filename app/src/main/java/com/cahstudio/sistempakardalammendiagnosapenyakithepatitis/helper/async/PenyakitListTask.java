package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async;

import android.os.AsyncTask;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.DiagnosaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.DetailRiwayatContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.PenyakitContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahPengetahuanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;

import java.util.List;

import retrofit2.Response;

public class PenyakitListTask extends AsyncTask<Void, Integer, List<Penyakit>> {
    private DiagnosaContract.View mDiagnosaView;
    private PenyakitContract.View mPenyakitView;
    private TambahPengetahuanContract.View mTambahPengetahuanView;
    private DetailRiwayatContract.View mDetailRiwayatView;
    private ApiInterface mApi;

    public PenyakitListTask(DiagnosaContract.View mDiagnosaView, ApiInterface mApi) {
        this.mDiagnosaView = mDiagnosaView;
        this.mApi = mApi;
    }

    public PenyakitListTask(PenyakitContract.View mPenyakitView, ApiInterface mApi) {
        this.mPenyakitView = mPenyakitView;
        this.mApi = mApi;
    }

    public PenyakitListTask(TambahPengetahuanContract.View mTambahPengetahuanView, ApiInterface mApi) {
        this.mTambahPengetahuanView = mTambahPengetahuanView;
        this.mApi = mApi;
    }

    public PenyakitListTask(DetailRiwayatContract.View mDetailRiwayatView, ApiInterface mApi) {
        this.mDetailRiwayatView = mDetailRiwayatView;
        this.mApi = mApi;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mPenyakitView != null){
            mPenyakitView.showLoading();
        }
    }

    @Override
    protected List<Penyakit> doInBackground(Void... voids) {
        try {
            Response<List<Penyakit>> response = mApi.getPenyakitList().execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Penyakit> penyakitList) {
        super.onPostExecute(penyakitList);
        if (penyakitList != null) {
            if (mDiagnosaView != null){
                mDiagnosaView.retrievePenyakitList(penyakitList);
            }else if (mPenyakitView != null){
                mPenyakitView.hideLoading();
                mPenyakitView.getPenyakitList(penyakitList);
            }else if (mTambahPengetahuanView != null){
                mTambahPengetahuanView.getPenyakitList(penyakitList);
            }else if (mDetailRiwayatView != null){
                mDetailRiwayatView.getPenyakitList(penyakitList);
            }
        }
    }
}
