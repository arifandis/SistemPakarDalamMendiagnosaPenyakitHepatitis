package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.adapter.PengetahuanRecyclerAdapter;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.PengetahuanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.async.GejalaListTask;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.PengetahuanPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PengetahuanActivity extends AppCompatActivity implements PengetahuanContract.View {
    private RecyclerView recyclerView;
    private Button btnTambah;

    private ProgressDialog progressDialog;
    private PengetahuanPresenter mPresenter;
    private ApiInterface mApi;

    private PengetahuanRecyclerAdapter mAdapter;
    private List<Pengetahuan> mPengetahuanList = new ArrayList<>();
    private List<Penyakit> mPenyakitList = new ArrayList<>();
    private List<Gejala> mGejalaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengetahuan);
        getSupportActionBar().setTitle("Daftar Pengetahuan");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.pengetahuan_rv);
        btnTambah = findViewById(R.id.pengetahuan_btnTambah);

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new PengetahuanPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new PengetahuanRecyclerAdapter(this,mPengetahuanList,mPresenter);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        mPresenter.doGetPengetahuanList();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TambahPengetahuanActivity.class);
                intent.putExtra("status","new");
                startActivityForResult(intent,1);
            }
        });
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

        if (responses.getStatus() == 1){
            mPengetahuanList.remove(responses.getId());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getPengetahuanList(List<Pengetahuan> pengetahuanList) {
        mPengetahuanList.addAll(pengetahuanList);
        Collections.sort(mPengetahuanList, new Comparator<Pengetahuan>() {
            @Override
            public int compare(Pengetahuan o1, Pengetahuan o2) {
                return o1.getIdPenyakit().compareTo(o2.getIdPenyakit());
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                mPengetahuanList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.doGetPengetahuanList();
            }
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
