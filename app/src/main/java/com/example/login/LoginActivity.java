package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.login.Manager.PrefManager;
import com.example.login.httpclient.ApiClient;
import com.example.login.httpclient.ApiInterfance;
import com.example.login.model.ResponseLogin;
import com.example.login.model.ResponseRegister;

public class LoginActivity extends AppCompatActivity {

    EditText etUser, etPass;
    Button bLogin,btnCancel;
    ApiInterfance apiInterfance;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;

        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);

        bLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        apiInterfance = ApiClient.getApiClient().create(ApiInterfance.class);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();            }
        });

//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,WelcomeActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public  void setLogin(){

        Call<ResponseLogin> api=apiInterfance.setLogin(
                etUser.getText().toString(),
                etPass.getText().toString()
        );

        api.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()){

                    PrefManager prefManager = new PrefManager(context);
                    prefManager.setString("TOKEN",response.body().getSuccess().getToken());

                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    MaterialDialog dialog = new MaterialDialog(context);
                    dialog.title(null, "Gagal");
                    dialog.message(null, "Login Gagal", false, 1f);
                    dialog.show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                MaterialDialog dialog = new MaterialDialog(context);
                dialog.title(null, "Gagal");
                dialog.message(null, "Login Gagal", false, 1f);
                dialog.show();
            }
        });

    }
}
