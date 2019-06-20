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
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.TambahPenyakitContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.TambahPenyakitPresenter;

public class TambahPenyakitActivity extends AppCompatActivity implements TambahPenyakitContract.View {
    private EditText etId, etPenyakit;
    private Button btnSimpan;

    private ProgressDialog progressDialog;
    private ApiInterface mApi;
    private TambahPenyakitPresenter mPresenter;

    private String mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tambah_penyakit);

        etId = findViewById(R.id.tambahPenyakit_etKode);
        etPenyakit = findViewById(R.id.tambahPenyakit_etPenyakit);
        btnSimpan = findViewById(R.id.tambahPenyakit_btnSimpan);

        mStatus = getIntent().getStringExtra("status");

        progressDialog = Global.setupProgressDialog(this);
        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new TambahPenyakitPresenter(this,mApi);

        getSupportActionBar().setTitle("Tambah Penyakit");
        if (mStatus.equals("edit")){
            String idPenyakit = getIntent().getStringExtra("id_penyakit");
            mPresenter.doGetPenyakit(idPenyakit);
            getSupportActionBar().setTitle("Edit Penyakit");
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
    public void getPenyakit(Penyakit penyakit) {
        etId.setText(penyakit.getIdPenyakit());
        etId.setEnabled(false);
        etId.setBackgroundColor(getResources().getColor(R.color.gray));
        etId.setTextColor(getResources().getColor(R.color.dark_gray));
        etPenyakit.setText(penyakit.getNamaPenyakit());
    }

    protected void checkForm(){
        String idPenyakit = etId.getText().toString();
        String namaPenyakit = etPenyakit.getText().toString();

        if (idPenyakit.isEmpty() || namaPenyakit.isEmpty()){
            Toast.makeText(this, "Lengkapi kolom inputan yang kosong", Toast.LENGTH_SHORT).show();
        }else {
            Penyakit penyakit = new Penyakit();
            penyakit.setIdPenyakit(idPenyakit);
            penyakit.setNamaPenyakit(namaPenyakit);
            if (mStatus.equals("edit")){
                mPresenter.doUpdatePenyakit(penyakit);
            }else if (mStatus.equals("new")){
                mPresenter.doAddPenyakit(penyakit);
            }
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
