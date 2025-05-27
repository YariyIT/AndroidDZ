package com.example.trainerintuition;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChoicePlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_play);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);       // Чтобы верхняя шторка не мешала

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int difLvl = save.getInt("DifLvl", 2);
        final int[] clickAllOpen = {0, 0};

        ImageView imgOpenAllLvl = findViewById(R.id.img_open_all_lvl);       // Создаём переменную для открытия всех уровней, для проверки
        imgOpenAllLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickAllOpen[0]<5 && difLvl!=5){
                    clickAllOpen[0]++;
                }
                if (clickAllOpen[0] == 5){
                    clickAllOpen[1] = 0;
                    SharedPreferences.Editor editor = save.edit();
                    editor.putInt("DifLvl", 5);
                    editor.apply();
                    Intent intent = new Intent(ChoicePlay.this, ChoicePlay.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                }
            }
        });

        TextView text1Of2 = findViewById(R.id.text_1_of_2);       // Создаём переменную для уровня -1 из 2-
        text1Of2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difLvl>=2){
                    Intent intent = new Intent(ChoicePlay.this, Play_1_of_2.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                }
            }
        });

        TextView text1Of3 = findViewById(R.id.text_1_of_3);       // Создаём переменную для уровня -1 из 3-
        if (difLvl>=3){
            text1Of3.setBackgroundResource(R.drawable.background_gif_lvl_open);
        }
        text1Of3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView text1Of4 = findViewById(R.id.text_1_of_4);       // Создаём переменную для уровня -1 из 4-
        if (difLvl>=4){
            text1Of4.setBackgroundResource(R.drawable.background_gif_lvl_open);
        }
        text1Of4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView text1Of5 = findViewById(R.id.text_1_of_5);       // Создаём переменную для уровня -1 из 5-
        if (difLvl>=5){
            text1Of5.setBackgroundResource(R.drawable.background_gif_lvl_open);
        }
        text1Of5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView imgResetAllLvl = findViewById(R.id.img_reset_all_lvl);
        imgResetAllLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickAllOpen[1]<5 && difLvl!=2){
                    clickAllOpen[1]++;
                }
                if (clickAllOpen[1] == 5){
                    clickAllOpen[0] = 0;
                    SharedPreferences.Editor editor = save.edit();
                    editor.putInt("DifLvl", 2);
                    editor.putInt("proPicCar", 1);
                    editor.apply();
                    Intent intent = new Intent(ChoicePlay.this, ChoicePlay.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                }
            }
        });

        Button bntMenuBack = findViewById(R.id.bnt_menu_back);
        bntMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoicePlay.this, Menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });
    }
}