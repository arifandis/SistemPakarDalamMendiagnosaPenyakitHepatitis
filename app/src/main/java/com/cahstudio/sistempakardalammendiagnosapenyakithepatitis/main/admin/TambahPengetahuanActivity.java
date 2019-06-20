package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahPengetahuanContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.TambahPengetahuanPresenter;

import java.util.ArrayList;
import java.util.List;

public class TambahPengetahuanActivity extends AppCompatActivity implements TambahPengetahuanContract.View {
    private Spinner sPenyakit,sGejala;
    private TextView tvBobot,tvGejala;
    private Button btnSimpan;

    private ProgressDialog progressDialog;
    private TambahPengetahuanPresenter mPresenter;
    private ApiInterface mApi;

    private List<Gejala> mGejalaList = new ArrayList<>();
    private List<Penyakit> mPenyakitList = new ArrayList<>();
    private List<String> mStrGejala = new ArrayList<>();
    private List<String> mStrPenyakit = new ArrayList<>();
    private ArrayAdapter<String> mGejalaAdapter;
    private ArrayAdapter<String> mPenyakitAdapter;

    private String mKodeGejala,mIdPenyakit,mStatus;
    private Pengetahuan mPengetahuan;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tambah_pengetahuan);

        sPenyakit = findViewById(R.id.tambahPengetahuan_sPenyakit);
        sGejala = findViewById(R.id.tambahPengetahuan_sGejala);
        tvBobot = findViewById(R.id.tambahPengetahuan_tvBobot);
        btnSimpan = findViewById(R.id.tambahPengetahuan_btnSimpan);

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new TambahPengetahuanPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);

        mStrGejala.add("Pilih gejala");
        mGejalaAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,mStrGejala);
        sGejala.setAdapter(mGejalaAdapter);

        mStrPenyakit.add("Pilih penyakit");
        mPenyakitAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,mStrPenyakit);
        sPenyakit.setAdapter(mPenyakitAdapter);

        mStatus = getIntent().getStringExtra("status");

        getSupportActionBar().setTitle("Tambah Pengetahuan");
        if (mStatus.equals("edit")){
            mId = getIntent().getIntExtra("id_pengetahuan",0);
            mPresenter.doGetPengetahuanById(mId);
            getSupportActionBar().setTitle("Edit Pengetahuan");
        }

        mPresenter.doGetGejalaList();
        mPresenter.doGetPenyakitList();

        sGejala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String namaGejala = sGejala.getSelectedItem().toString();
                for (Gejala gejala: mGejalaList){
                    if (gejala.getGejala().equals(namaGejala)){
                        tvBobot.setText(gejala.getBobot());
                        break;
                    }else{
                        tvBobot.setText("0");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForm();
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
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void getGejalaList(List<Gejala> gejalaList) {
        for (Gejala gejala: gejalaList){
            mStrGejala.add(gejala.getGejala());
            mGejalaAdapter.notifyDataSetChanged();
        }

        if (mStatus.equals("edit")){
            sGejala.setSelection(mGejalaAdapter.getPosition(mPengetahuan.getKodeGejala()));
        }
        mGejalaList.addAll(gejalaList);
    }

    @Override
    public void getPenyakitList(List<Penyakit> penyakitList) {
        for (Penyakit penyakit: penyakitList){
            mStrPenyakit.add(penyakit.getNamaPenyakit());
            mPenyakitAdapter.notifyDataSetChanged();
        }

        if (mStatus.equals("edit")){
            sPenyakit.setSelection(mPenyakitAdapter.getPosition(mPengetahuan.getIdPenyakit()));
        }
        mPenyakitList.addAll(penyakitList);
    }

    @Override
    public void getPengetahuan(Pengetahuan pengetahuan) {
        mPengetahuan = pengetahuan;
    }

    protected void checkForm(){
        String namaGejala = sGejala.getSelectedItem().toString();
        String namaPenyakit = sPenyakit.getSelectedItem().toString();
        String bobot = tvBobot.getText().toString();
        if (namaGejala.contains("Pilih") || namaPenyakit.contains("Pilih") || bobot.equals("0")){
            Toast.makeText(TambahPengetahuanActivity.this, "Lengkapi form dengan benar", Toast.LENGTH_SHORT).show();
        }else{
            for (Gejala gejala: mGejalaList){
                if (gejala.getGejala().equals(namaGejala)){
                    mKodeGejala = gejala.getKodeGejala();
                    break;
                }
            }

            for (Penyakit penyakit: mPenyakitList){
                if (penyakit.getNamaPenyakit().equals(namaPenyakit)){
                    mIdPenyakit = penyakit.getIdPenyakit();
                    break;
                }
            }

            Pengetahuan pengetahuan = new Pengetahuan();
            pengetahuan.setKodeGejala(mKodeGejala);
            pengetahuan.setIdPenyakit(mIdPenyakit);
            pengetahuan.setBobot(bobot);
            if (mStatus.equals("edit")){
                pengetahuan.setIdPengetahuan(mId);
                mPresenter.doUpdatePengetahuan(pengetahuan);
            }else if (mStatus.equals("new")){
                mPresenter.doAddPengetahuan(pengetahuan);
            }

        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
