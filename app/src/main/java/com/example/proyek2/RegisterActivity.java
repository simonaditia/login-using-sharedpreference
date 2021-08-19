package com.example.proyek2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "user.txt";
    EditText txtNamaLengkap, txtEmail, txtTglLahir, txtAlamat, txtPassword;
    Button btnSimpan;
    String name, email, tglLahir, alamat;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//        final Context context = builder.getContext();
//        final LayoutInflater inflater = LayoutInflater.from(context);
//        final View view = inflater.inflate(R.layout.activity_register, null, false);
        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        txtNamaLengkap = (EditText) findViewById(R.id.txtNamaLengkap);
        txtEmail = (EditText) findViewById(R.id.txtEmailRegister);
        txtTglLahir = (EditText) findViewById(R.id.txtTglLahir);
        txtAlamat = (EditText) findViewById(R.id.txtAlamat);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        name = txtNamaLengkap.getText().toString();
        email = txtEmail.getText().toString();
        tglLahir = txtTglLahir.getText().toString();
        alamat = txtAlamat.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("tglLahir", tglLahir);
        editor.putString("alamat", alamat);
        editor.apply();

        if(txtNamaLengkap.getText().toString().length() == 0 || txtEmail.getText().toString().length() == 0 || txtTglLahir.getText().toString().length() == 0  || txtAlamat.getText().toString().length() == 0 || txtPassword.getText().toString().length() == 0) {
            Toast.makeText(this, "Mohon lengkapi seluruh inputan", Toast.LENGTH_SHORT).show();
        } else {
            simpan();
        }

    }

    void simpan() {
        String contentData = txtNamaLengkap.getText().toString() + ";" + txtEmail.getText().toString() + ";" + txtTglLahir.getText().toString() + ";" + txtAlamat.getText().toString() + ";" + txtPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream fileOutputStream = null;

        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write(contentData.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "Register berhasil, silahkan login", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException ex) {
            Toast.makeText(this, "Error : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void checkUser() {

    }
}