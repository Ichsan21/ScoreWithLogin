package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
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
import com.example.login.httpclient.ApiClient;
import com.example.login.httpclient.ApiInterface;
import com.example.login.model.ResponseRegister;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etUsername, etPaswword,etCPassword;
    Button bRegister,bCancel;
    ApiInterface apiInterface;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context=this;

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPaswword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCpassword);

        bRegister = findViewById(R.id.btnRegister);
        bCancel = findViewById(R.id.btnCancel);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegister();            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public  void setRegister(){

        Call<ResponseRegister> api= apiInterface.setRegister(
                etName.getText().toString(),
                etEmail.getText().toString(),
                etUsername.getText().toString(),
                etPaswword.getText().toString(),
                etCPassword.getText().toString()
        );


        api.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if(response.isSuccessful()){

                    MaterialDialog dialog = new MaterialDialog(context);
                    dialog.title(null, "Success");
                    dialog.message(null, "Register Berhasil", false, 1f);
                    dialog.cancelOnTouchOutside(false);
                    dialog.positiveButton(null, "OK", new Function1<MaterialDialog, Unit>() {
                        @Override
                        public Unit invoke(MaterialDialog materialDialog) {
                            Intent intent = new Intent(context,LoginActivity.class);
                            startActivity(intent);
                            return null;
                        }
                    });
                    dialog.show();
                } else {
                    MaterialDialog dialog = new MaterialDialog(context);
                    dialog.title(null, "Gagal");
                    dialog.message(null, "Register Gagal", false, 1f);
                    dialog.show();
                }

            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                MaterialDialog dialog = new MaterialDialog(context);
                dialog.title(null, "Gagal");
                dialog.message(null, "Register Gagal", false, 1f);
                dialog.show();
            }
        });

    }
}
