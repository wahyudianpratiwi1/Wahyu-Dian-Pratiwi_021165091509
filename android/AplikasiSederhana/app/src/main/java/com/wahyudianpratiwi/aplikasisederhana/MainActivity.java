package com.wahyudianpratiwi.aplikasisederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wahyudianpratiwi.aplikasisederhana.activity.KalkulatorActivity;
import com.wahyudianpratiwi.aplikasisederhana.activity.ListViewNegaraActivity;
import com.wahyudianpratiwi.aplikasisederhana.activity.TampilNamaActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgInputNama, imgKalkulator, imgLvNegara;
    TampilNamaActivity tampilNamaActivity = new TampilNamaActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgInputNama = findViewById(R.id.img_tampil_nama);
        imgKalkulator = findViewById(R.id.img_kalkulator);
        imgLvNegara = findViewById(R.id.img_lv_negara);

        imgInputNama.setOnClickListener(this);
        imgKalkulator.setOnClickListener(this);
        imgLvNegara.setOnClickListener(this);

        setTitle("Home");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.img_tampil_nama:
                Intent i = new Intent(this, TampilNamaActivity.class);
                startActivity(i);
                break;
            case R.id.img_kalkulator:
                startActivity(new Intent(this, KalkulatorActivity.class));
                break;
            case R.id.img_lv_negara:
                startActivity(new Intent(this, ListViewNegaraActivity.class));
                break;
        }
    }
}