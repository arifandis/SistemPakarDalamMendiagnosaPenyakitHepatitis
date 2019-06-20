package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;

public class FormDataActivity extends AppCompatActivity {
    private Button btnNext;
    private EditText etNama,etUsia;
    private RadioGroup rgJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Form Data Pengguna");

        btnNext = findViewById(R.id.data_btnNext);
        etNama = findViewById(R.id.data_etNama);
        etUsia = findViewById(R.id.data_etUsia);
        rgJenisKelamin = findViewById(R.id.data_rgJenilsKelamin);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveData();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        AlertDialog.Builder homeDialog = Global.setupPopupDialog(this,"Apakah Anda yakin akan kembali ke halaman home?");
        homeDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        homeDialog.show();
        return true;
    }

    protected void moveData(){
        String nama = etNama.getText().toString();
        String usia = etUsia.getText().toString();
        int selectedJenisKelamin = rgJenisKelamin.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selectedJenisKelamin);

        if (nama.isEmpty() || usia.isEmpty() || rb.getText() == null){
            Toast.makeText(this, "Isikan data dengan lengkap", Toast.LENGTH_SHORT).show();
        }else{
            String jenisKelamin = rb.getText().toString();
            Intent intent = new Intent(this,DiagnosaActivity.class);
            intent.putExtra("nama",nama);
            intent.putExtra("usia",usia);
            intent.putExtra("jenisKelamin",jenisKelamin);
            intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
            finish();
        }
    }
}
