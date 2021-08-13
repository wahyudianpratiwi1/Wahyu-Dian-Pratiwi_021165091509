package com.wahyudiapratiwi.latihanstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Eksternal_Storage extends AppCompatActivity {
    private String keyExternal = "KEY_EXTERNAL_STORAGE";
    private String FILE_NAME = "catatan.txt";
    public static final int REQUEST_CODE_STORAGE = 100;
    private TextView isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eksternal_storage);
        isi = findViewById(R.id.textView);

    }
    private boolean checkPermissionStorage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                return false;
            }
        } else
            return true;
        }
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case REQUEST_CODE_STORAGE:
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        actionBuatCatatanExt(null);
                        actionBacaCatatanExt(null);
                        actionUbahCatatanExt(null);
                        actionHapusCatatanExt(null);
                    }
                    break;
            }
        }
    public void actionBuatCatatanExt(View view) {
        String isiCatatan = "Isi Data Catatan";
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiCatatan.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void actionUbahCatatanExt(View view) {
        String ubah = "Update Isi Catatan";
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }
        File file=new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void actionBacaCatatanExt(View view) {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILE_NAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line !=null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e){
                System.out.println("Error"+e.getMessage());
            }
            isi.setText(text.toString());
        }
    }

    public void actionHapusCatatanExt(View view) {
        File file = new File(Environment.getExternalStorageDirectory(),FILE_NAME);
        if (file.exists()){
            file.delete();
        }
    }

}