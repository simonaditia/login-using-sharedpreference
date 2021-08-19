package com.example.proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "user.txt";
    Button btnLogin, btnToRegister;
    EditText txtEmail, txtPassword;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        btnToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if(bacaFIleLogin())  {
                    Random random = new Random();
                    int n = 100 - 1 + 1;
                    int hasil = random.nextInt() % n;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("session", "" + hasil);
                    editor.putBoolean("isLoged", true);
                    editor.apply();
                    Intent intentMain = new Intent(this, MainActivity.class);
                    startActivity(intentMain);
                }
                break;
            case R.id.btnToRegister:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    boolean bacaFIleLogin() {
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();

                while (line!=null) {
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();

                String[] dataUser = text.toString().split(";");

                if(txtEmail.getText().toString().equalsIgnoreCase(dataUser[1]) && txtPassword.getText().toString().equalsIgnoreCase(dataUser[4])) {
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (IOException ex) {
                Toast.makeText(this, "Error : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}