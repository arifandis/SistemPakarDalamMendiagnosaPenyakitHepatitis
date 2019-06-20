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
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.adapter.PenyakitRecyclerAdapter;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.PenyakitContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.PenyakitPresenter;

import java.util.ArrayList;
import java.util.List;

public class PenyakitActivity extends AppCompatActivity implements PenyakitContract.View {
    private RecyclerView recyclerView;
    private Button btnTambah;

    private ApiInterface mApi;
    private PenyakitPresenter mPresenter;
    private ProgressDialog progressDialog;

    private PenyakitRecyclerAdapter mAdapter;
    private List<Penyakit> mPenyakitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit);
        getSupportActionBar().setTitle("Daftar Penyakit");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.penyakit_rv);
        btnTambah = findViewById(R.id.penyakit_btnTambah);

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new PenyakitPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new PenyakitRecyclerAdapter(this,mPenyakitList,mPresenter);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        mPresenter.doGetPenyakitList();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TambahPenyakitActivity.class);
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
    public void getPenyakitList(List<Penyakit> penyakitList) {
        mPenyakitList.addAll(penyakitList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getResponse(Responses responses) {
        Toast.makeText(this, responses.getMessage(), Toast.LENGTH_SHORT).show();

        if (responses.getStatus() == 1){
            for (Penyakit penyakit: mPenyakitList){
                if (penyakit.getIdPenyakit().equals(responses.getKode())){
                    mPenyakitList.remove(penyakit);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1){
            if (resultCode == RESULT_OK){
                mPenyakitList.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.doGetPenyakitList();
            }
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
