package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.AdminPreference;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin.AdminLoginActivity;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin.AdminMainActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnKonsultasi,btnAdmin;
    private AdminPreference mAdminPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");

        btnKonsultasi = findViewById(R.id.main_btnKonsultasi);
        btnAdmin = findViewById(R.id.main_btnLoginAdmin);

        mAdminPref = new AdminPreference(this);

        if (mAdminPref.getAdminLogin() != null){
            startActivity(new Intent(this, AdminMainActivity.class));
            finish();
        }

        btnKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(),FormDataActivity.class),1);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminLoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                finish();
            }
        }
    }
}
