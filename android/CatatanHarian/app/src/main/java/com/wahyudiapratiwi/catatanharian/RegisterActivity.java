package com.wahyudiapratiwi.catatanharian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtEmail, edt_nama, edtSekolah, edtAlamat;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edtEmail);
        edt_nama = findViewById(R.id.edtNama);
        edtSekolah = findViewById(R.id.edtSekolah);
        edtAlamat = findViewById(R.id.edtAlamat);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()){
                    simpanFileData();
                } else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void simpanFileData() {
        String isiFile = edtUsername.getText().toString()+";"+
                edtPassword.getText().toString()+";"+
                edtEmail.getText().toString()+";"+
                edt_nama.getText().toString()+";"+
                edtSekolah.getText().toString()+";"+
                edtAlamat.getText().toString();
        File file = new File(getFilesDir(),edtUsername.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this,"Register Berhasil",Toast.LENGTH_SHORT).show();
        onBackPressed();
        }

    private boolean isValidation() {
        if (edtUsername.getText().toString().equals("")||
        edtPassword.getText().toString().equals("")||
        edtEmail.getText().toString().equals("")||
        edt_nama.getText().toString().equals("")||
        edtAlamat.getText().toString().equals("")||
        edtSekolah.getText().toString().equals("")){
            return false;
        } else {
            return true;
        }
    }
}