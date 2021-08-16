package com.wahyudiapratiwi.catatanharian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class InsertAndViewActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int REQUEST_CODE_STORAGE = 100;
    int eventID = 0;
    EditText edtFileName, edtContent;
    Button btnSimpan;
    boolean isEditable = false;
    String fileName = "";
    String tempCatatan = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_and_view);
        setTitle("Catatan Baru");
        edtFileName = findViewById(R.id.edtFileName);
        edtContent = findViewById(R.id.edtContent);
        btnSimpan = findViewById(R.id.btnSubmit);
        btnSimpan.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            fileName = extras.getString("filename");
            edtFileName.setText(fileName);
            getSupportActionBar().setTitle("Ubah Catatan");
        } else {
            getSupportActionBar().setTitle("Tambah Catatan");
        }
        eventID=1;
        if (Build.VERSION.SDK_INT>=23){
            if (periksaIzinPenyimpanan()){
                bacaFile();
            }
        } else {
            bacaFile();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions, @NonNull int[]grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        switch (requestCode){
            case REQUEST_CODE_STORAGE:
                if (grantResult[0]==PackageManager.PERMISSION_GRANTED){
                    if (eventID==1){
                        bacaFile();
                    } else {
                        tampilkanDialogKonfirmasiPenyimpanan();
                    }
                }
                break;
        }
    }

    private void bacaFile() {
        String path = Environment.getExternalStorageDirectory().toString()+"/kominfo.proyek1";
        File file = new File(path, edtFileName.getText().toString());
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line!=null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error"+e.getMessage());
            }
            tempCatatan = text.toString();
            edtContent.setText(text.toString());
        }
    }

    private boolean periksaIzinPenyimpanan() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:
                eventID =2;
                if (!tempCatatan.equals(edtContent.getText().toString())){
                    if (Build.VERSION.SDK_INT>=23){
                        if (periksaIzinPenyimpanan()){
                            tampilkanDialogKonfirmasiPenyimpanan();
                        }
                    }else {
                        tampilkanDialogKonfirmasiPenyimpanan();
                    }
                }
                break;
        }
    }

    private void tampilkanDialogKonfirmasiPenyimpanan() {
        new AlertDialog.Builder(this)
                .setTitle("Simpan Catatan")
                .setMessage("Apakah Anda Yakin Ingin Menyimpan Catatan Ini?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        buatDanUbah();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
    void buatDanUbah(){
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }
        String path = Environment.getExternalStorageDirectory().toString()+"/kominfo.proyek1";
        File parent = new File(path);
        if (parent.exists()){
            File file = new File(path,edtFileName.toString());
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file);
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
                streamWriter.append(edtContent.getText());
                streamWriter.flush();
                streamWriter.close();
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            parent.mkdir();
            File file = new File(path, edtFileName.getText().toString());
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream =  new FileOutputStream(file, false);
                outputStream.write(edtContent.getText().toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onBackPressed();
    }
    @Override
    public void onBackPressed(){
        if (!tempCatatan.equals(edtContent.getText().toString())){
            tampilkanDialogKonfirmasiPenyimpanan();
        }
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}