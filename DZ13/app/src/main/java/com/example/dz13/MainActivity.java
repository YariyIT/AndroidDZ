package com.example.dz13;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textPermissions = findViewById(R.id.text_permissions);
        String perEnabled = "Разрешения есть";
        String perDisabled = "Резрешений нет";
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {      // Проверяем, предоставлено ли приложению определённое разрешение
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 100);       // Запрос у пользователя на разрешение
            }
        }
        // Разрешение для Android 11 (API 30) и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if (!Environment.isExternalStorageManager()){
                try {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageCodePath())));
                    startActivityIfNeeded(intent, 101);
                }catch (Exception e){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivityIfNeeded(intent, 101);
                }
            }
        }
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {      // Проверяем, предоставлено ли приложению определённое разрешение
                textPermissions.setText(perEnabled);
            } else {
                textPermissions.setText(perDisabled);
            }
        }
        // Разрешение для Android 11 (API 30) и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if (Environment.isExternalStorageManager()){
                textPermissions.setText(perEnabled);
            } else {
                textPermissions.setText(perDisabled);
            }
        }

    }
}