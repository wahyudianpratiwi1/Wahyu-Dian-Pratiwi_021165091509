package com.wahyudiapratiwi.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Internal_Storage extends AppCompatActivity {
    private String keyInternal = "KEY_INTERNAL_STORAGE";
    private String FILE_NAME = "catatan.txt";
    private TextView isi;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        isi = findViewById(R.id.isi_catatan);
        editText = findViewById(R.id.edt_catatan);
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            String newTitle = extras.getString(keyInternal);
            setTitle(newTitle);
        }
    }

    public void actionBuatCatatan(View view) {
        String isiCatatan = "Membuat catatan baru";
        File file = new File(getFilesDir(), FILE_NAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiCatatan.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionUbahCatatan(View view) {
        String ubah = editText.getText().toString();
        if (!ubah.isEmpty()){
            File file = new File(getFilesDir(), FILE_NAME);
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, true);
                outputStream.write(ubah.getBytes());
                outputStream.flush();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "Text Kosong!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void actionBacaCatatan(View view) {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILE_NAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new  BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line !=null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            }catch (IOException e){
                System.out.println("Error"+e.getMessage());
            }
            isi.setText(text.toString());
        }
    }

    public void actionHapusCatatan(View view) {
        File file = new File(getFilesDir(), FILE_NAME);
        if (file.exists()){
            file.delete();
        }
    }
}