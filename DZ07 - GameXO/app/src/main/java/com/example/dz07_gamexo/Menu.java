package com.example.dz07_gamexo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RadioButton rand = findViewById(R.id.random); // Создаём переменную для обращения к радиокнопке игрового режима
        Button threeToThree = findViewById(R.id.three_to_three); // Создаём переменную для обращения к кнопке перехода на экран с полем 3 х 3
        Button fiveToFive = findViewById(R.id.five_to_five); // Создаём переменную для обращения к кнопке перехода на экран с полем 5 х 5


        threeToThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this, ThreeToThree.class);
                if(rand.isChecked()) {
                    intent.putExtra("game_mode", "1");
                }else {
                    intent.putExtra("game_mode", "0");
                }
                startActivity(intent);
            }
        });

        fiveToFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this, FiveToFive.class);
                if(rand.isChecked()) {
                    intent.putExtra("game_mode", "1");
                }else {
                    intent.putExtra("game_mode", "0");
                }
                startActivity(intent);
            }
        });


    }
}