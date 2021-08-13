package com.wahyudiapratiwi.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String keyInternal = "KEY_INTERNAL_STORAGE";
    private String keyExternal = "KEY_EXTERNAL_STORAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actionInternalStorage(View view) {
        Intent intent1 = new Intent(this, Internal_Storage.class);
        intent1.putExtra(keyInternal, "Internal Storage");
        startActivity(intent1);
    }

    public void actionExternalStorage(View view) {
        Intent intent2 = new Intent(this, Eksternal_Storage.class);
        intent2.putExtra(keyExternal, "Eksternal Storage");
        startActivity(intent2);
    }
}