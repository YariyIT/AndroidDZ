package com.example.trainerintuition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);       // Чтобы верхняя шторка не мешала

        TextView pressToContinue = findViewById(R.id.press_to_continue);       // Создаём переменную для перехода в меню
        pressToContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });

    }
}