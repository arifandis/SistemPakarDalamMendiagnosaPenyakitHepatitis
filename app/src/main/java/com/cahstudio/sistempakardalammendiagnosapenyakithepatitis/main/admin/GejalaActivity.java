package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.adapter.GejalaRecyclerAdapter;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.GejalaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.GejalaPresenter;

import java.util.ArrayList;
import java.util.List;

public class GejalaActivity extends AppCompatActivity implements GejalaContract.View {
    private RecyclerView recyclerView;
    private Button btnTambah;

    private GejalaPresenter mPresenter;
    private ApiInterface mApi;
    private ProgressDialog progressDialog;

    private GejalaRecyclerAdapter mAdapter;
    private List<Gejala> mGejalaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Gejala");

        recyclerView = findViewById(R.id.gejala_rv);
        btnTambah = findViewById(R.id.gejala_btnTambah);

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new GejalaPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new GejalaRecyclerAdapter(this,mGejalaList,mPresenter);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        mPresenter.doGetGejalaList();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TambahGejalaActivity.class);
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
            for (Gejala gejala: mGejalaList){
                if (gejala.getKodeGejala().equals(responses.getKode())){
                    mGejalaList.remove(gejala);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void getGejalaList(List<Gejala> gejalaList) {
        mGejalaList.addAll(gejalaList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                mGejalaList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.doGetGejalaList();
            }
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
