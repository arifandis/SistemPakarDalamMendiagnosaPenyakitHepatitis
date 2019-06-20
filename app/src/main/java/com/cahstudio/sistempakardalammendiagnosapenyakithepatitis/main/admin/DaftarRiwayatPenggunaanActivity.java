package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.adapter.DaftarRiwayatPenggunaanRecyclerAdapter;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.DaftarRiwayatPenggunaanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.DaftarRiwayatPenggunaanPresenter;

import java.util.ArrayList;
import java.util.List;

public class DaftarRiwayatPenggunaanActivity extends AppCompatActivity implements DaftarRiwayatPenggunaanContract.View {
    private RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    private List<Pengguna> mPenggunaList = new ArrayList<>();
    private DaftarRiwayatPenggunaanPresenter mPresenter;
    private ApiInterface mApi;
    private DaftarRiwayatPenggunaanRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Pengguna");
        setContentView(R.layout.activity_daftar_riwayat_penggunaan);

        recyclerView = findViewById(R.id.riwayat_rv);

        progressDialog = Global.setupProgressDialog(this);
        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new DaftarRiwayatPenggunaanPresenter(this,mApi);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new DaftarRiwayatPenggunaanRecyclerAdapter(this,mPenggunaList,mPresenter);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        mPresenter.doGetDaftarRiwayat();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void getResponse(Responses responses) {
        Toast.makeText(this, responses.getMessage(), Toast.LENGTH_SHORT).show();

        if  (responses.getStatus() == 1){
            mPenggunaList.remove(responses.getId());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDaftarRiwayat(List<Pengguna> penggunaList) {
        mPenggunaList.addAll(penggunaList);
        mAdapter.notifyDataSetChanged();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
