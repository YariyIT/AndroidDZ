package com.example.trainerintuition;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ChoicePlay extends AppCompatActivity {

    Dialog dialogInDevelopment, dialogMakeForOpeningLevel;       // Объявляем переменные для вызова диалоговоых окон -В РАЗРАБОТКЕ- и -СДЕЛАТЬ ДЛЯ ОТКРЫТИЯ УРОВНЯ-

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_play);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);       // Чтобы верхняя шторка не мешала

        // ------------------------  Вызов диалогового окна -В РАЗРАБОТКЕ-  ------------------------
        dialogInDevelopment = new Dialog(this);
        dialogInDevelopment.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogInDevelopment.setContentView(R.layout.dialog_in_development);
        Objects.requireNonNull(dialogInDevelopment.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Удаляем фон диалогового окна
        dialogInDevelopment.setCancelable(false);       // Запрещаем закрывать диалоговое окно кликом за пределами диалогового окны

        TextView btnClose = dialogInDevelopment.findViewById(R.id.button_close);       // Кнопка Назад
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInDevelopment.dismiss();       // Закрываем диалоговое окно
            }
        });

        //-----------------------------------------------------------


        // ------------------------  Вызов диалогового окна -СДЕЛАТЬ ДЛЯ ОТКРЫТИЯ УРОВНЯ-  ------------------------
        dialogMakeForOpeningLevel = new Dialog(this);
        dialogMakeForOpeningLevel.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogMakeForOpeningLevel.setContentView(R.layout.dialog_make_for_opening_level);

        Objects.requireNonNull(dialogMakeForOpeningLevel.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Удаляем фон диалогового окна
        dialogMakeForOpeningLevel.setCancelable(false);       // Запрещаем закрывать диалоговое окно кликом за пределами диалогового окны

        TextView btnClose2 = dialogMakeForOpeningLevel.findViewById(R.id.button_close);       // Кнопка Назад
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMakeForOpeningLevel.dismiss();       // Закрываем диалоговое окно
            }
        });

        //-----------------------------------------------------------

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);       // Создаём переменную -save- для обращения/создания файла сохранения
        final int difLvl = save.getInt("DifLvl", 2);       // Создаём переменную -difLvl- для обращения/создания значения уровня сложности
        final int prefProgressPictureCards = save.getInt("proPicCar", 1);       // Создаём переменную прогресса игры -Карточки с картинкой-
        final int[] clickAllOpen = {0, 0};       // Создаём счётчик в виде массива, для сброса/активации прогресса/доступа

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
                dialogInDevelopment.show();
//                if (difLvl<3){
//                    dialogMakeForOpeningLevel.show();
//                } else {
//
//                }

            }
        });

        TextView text1Of4 = findViewById(R.id.text_1_of_4);       // Создаём переменную для уровня -1 из 4-
        if (difLvl>=4){
            text1Of4.setBackgroundResource(R.drawable.background_gif_lvl_open);
        }
        text1Of4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInDevelopment.show();
            }
        });

        TextView text1Of5 = findViewById(R.id.text_1_of_5);       // Создаём переменную для уровня -1 из 5-
        if (difLvl>=5){
            text1Of5.setBackgroundResource(R.drawable.background_gif_lvl_open);
        }
        text1Of5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInDevelopment.show();
            }
        });

        ImageView imgResetAllLvl = findViewById(R.id.img_reset_all_lvl);       // Создаём переменную для сброса прогресса
        imgResetAllLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickAllOpen[1]<5 && (difLvl!=2 || prefProgressPictureCards>1)){
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