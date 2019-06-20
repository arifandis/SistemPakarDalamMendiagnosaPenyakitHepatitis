package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.AdminPreference;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.MainActivity;

public class AdminMainActivity extends AppCompatActivity {
    private Button btnRiwayat,btnPenyakit,btnGejala,btnPengetahuan,btnLogout,btnExit;

    private AdminPreference mAdminPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        getSupportActionBar().setTitle("Halaman Utama Admin");

        btnRiwayat = findViewById(R.id.admin_btnDaftarPengguna);
        btnPenyakit = findViewById(R.id.admin_btnPenyakit);
        btnGejala = findViewById(R.id.admin_btnGejala);
        btnPengetahuan = findViewById(R.id.admin_btnPengetahuan);
        btnLogout = findViewById(R.id.admin_btnLogout);
        btnExit = findViewById(R.id.admin_btnExit);

        mAdminPref = new AdminPreference(this);

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DaftarRiwayatPenggunaanActivity.class));
            }
        });

        btnPenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PenyakitActivity.class));
            }
        });

        btnGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GejalaActivity.class));
            }
        });

        btnPengetahuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PengetahuanActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                mAdminPref.clearAdminLogged();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
