package com.wahyudianpratiwi.aplikasisederhana.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wahyudianpratiwi.aplikasisederhana.R;

public class TampilNamaActivity extends AppCompatActivity {
    private EditText edtName;
    private Button btnTampil;
    private TextView txtOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_nama);
        setTitle("Tampil Nama");
        edtName = findViewById(R.id.edt_nama);
        btnTampil = findViewById(R.id.btn_tampil);
        txtOutput = findViewById(R.id.txt_output);
    }

    public void actionTampil(View view) {
        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nama Kosong!", Toast.LENGTH_SHORT).show();
        } else {
            txtOutput.setText(edtName.getText().toString());
        }
    }
}