package com.example.proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogout;
    TextView txtNameF, txtEmailF, txtTglLahirF, txtAlamatF;
    SharedPreferences preferences;
    String nameF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        txtNameF = (TextView) findViewById(R.id.txtNameF);
        txtEmailF = (TextView) findViewById(R.id.txtEmailF);
        txtTglLahirF = (TextView) findViewById(R.id.txtTglLahirF);
        txtAlamatF = (TextView) findViewById(R.id.txtAlamatF);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        String name = prefs.getString("name", "");
        String email = prefs.getString("email", "");
        String tglLahir = prefs.getString("tglLahir", "");
        String alamat = prefs.getString("alamat", "");

        txtNameF.setText(name);
        txtEmailF.setText(email);
        txtTglLahirF.setText(tglLahir);
        txtAlamatF.setText(alamat);
    }

    @Override
    public void onClick(View v) {

        Log.d("Sukses1", "sukses1");
//        preferences.edit().clear().commit();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoged", false);
        editor.apply();
        finish();
        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentLogin);
        Log.d("Sukses2", "sukses2");
    }
}