package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahGejalaContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.TambahGejalaPresenter;

public class TambahGejalaActivity extends AppCompatActivity implements TambahGejalaContract.View {
    private EditText etKode,etGejala,etBobot;
    private Button btnSimpan;

    private TambahGejalaPresenter mPresenter;
    private ApiInterface mApi;

    private ProgressDialog progressDialog;

    private String mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gejala);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etKode = findViewById(R.id.tambahGejala_etKode);
        etGejala = findViewById(R.id.tambahGejala_etGejala);
        etBobot = findViewById(R.id.tambahGejala_etBobot);
        btnSimpan = findViewById(R.id.tambahGejala_btnSimpan);

        mStatus = getIntent().getStringExtra("status");

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new TambahGejalaPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);

        getSupportActionBar().setTitle("Tambah Gejala");
        if (mStatus.equals("edit")){
            String kodeGejala = getIntent().getStringExtra("kode_gejala");
            mPresenter.doGetGejala(kodeGejala);
            getSupportActionBar().setTitle("Edit Gejala");
        }

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
        progressDialog.hide();
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
    public void getGejala(Gejala gejala) {
        etKode.setText(gejala.getKodeGejala());
        etKode.setEnabled(false);
        etKode.setBackgroundColor(getResources().getColor(R.color.gray));
        etKode.setTextColor(getResources().getColor(R.color.dark_gray));
        etGejala.setText(gejala.getGejala());
        etBobot.setText(gejala.getBobot());
    }

    protected void checkForm(){
        String kode = etKode.getText().toString();
        String namaGejala = etGejala.getText().toString();
        String bobot = etBobot.getText().toString();

        if (kode.isEmpty() || namaGejala.isEmpty() || bobot.isEmpty()){
            Toast.makeText(this, "Lengkapi kolom inputan yang kosong", Toast.LENGTH_SHORT).show();
        }else{
            Gejala gejala = new Gejala();
            gejala.setKodeGejala(kode);
            gejala.setGejala(namaGejala);
            gejala.setBobot(bobot);
            if (mStatus.equals("edit")){
                mPresenter.doUpdateGejala(gejala);
            }else if (mStatus.equals("new")){
                mPresenter.doAddGejala(gejala);
            }
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
