package com.example.monitoringsiswapkl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoringsiswapkl.R;
import com.example.monitoringsiswapkl.api.ApiInterface;
import com.example.monitoringsiswapkl.api.ApiServer;
import com.example.monitoringsiswapkl.model.ResponseLogin;
import com.example.monitoringsiswapkl.utils.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    String textUser, textPass;
    EditText user, pass;
    AppCompatButton btnLogin, btnRegister;
    SharedPreferences sharedPreferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = new SharedPreferences(getApplicationContext());
        if (sharedPreferences.isLogedIn()){
            String role = sharedPreferences.getUserDetail().get(SharedPreferences.ROLE);
            if (role.equals("admin")){
                moveToMainAdmin();
            }else {
                Toast.makeText(getApplicationContext(), "Maaf Anda Bukan Admin", Toast.LENGTH_SHORT).show();
            }
        }

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textUser = user.getText().toString();
                textPass = pass.getText().toString();

                if (textUser.equals("")){
                    user.setError("Mohon diisi!");
                }else if (textPass.equals("")){
                    pass.setError("Mohon diisi");
                }else {
                    moveToLogin(textUser, textPass);
                }

            }
        });

    }

    private void moveToMainAdmin() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private void moveToLogin(String textUser, String textPass) {
        ApiInterface apiInterface = ApiServer.getClient().create(ApiInterface.class);
        Call<ResponseLogin> responseLoginCall = apiInterface.login(textUser, textPass);
        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }
}