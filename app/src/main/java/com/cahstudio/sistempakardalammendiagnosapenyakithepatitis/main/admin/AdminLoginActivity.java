package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.AdminLoginContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.AdminPreference;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.MainActivity;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Admin;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.AdminLoginPresenter;

public class AdminLoginActivity extends AppCompatActivity implements AdminLoginContract.View {
    private AdminLoginPresenter mPresenter;
    private ApiInterface mApi;

    private EditText etUsername,etPassword;
    private Button btnLogin;

    private ProgressDialog progressDialog;
    private AdminPreference mAdminPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Halaman Login Admin");

        etUsername = findViewById(R.id.login_etUsername);
        etPassword = findViewById(R.id.login_etPassword);
        btnLogin = findViewById(R.id.login_btnLogin);

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new AdminLoginPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);
        mAdminPref = new AdminPreference(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
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
            mAdminPref.setAdminLogin(new Admin(etUsername.getText().toString(),etPassword.getText().toString()));
            startActivity(new Intent(this,AdminMainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void checkLogin(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Isi username dan password", Toast.LENGTH_SHORT).show();
        }else{
            mPresenter.doLogin(new Admin(username,password));
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
