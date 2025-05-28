package com.example.trainerintuition;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class Play_1_of_2 extends AppCompatActivity {

    Dialog dialogInDevelopment, dialogMakeForOpeningGame;       // Объявляем переменные для вызова диалоговоых окон -В РАЗРАБОТКЕ- и -СДЕЛАТЬ ДЛЯ ОТКРЫТИЯ ИГРЫ-
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1_of2);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int lvlPicCar = save.getInt("LvlPicCar", 1);

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
        dialogMakeForOpeningGame = new Dialog(this);
        dialogMakeForOpeningGame.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogMakeForOpeningGame.setContentView(R.layout.dialog_make_for_opening_game);

        Objects.requireNonNull(dialogMakeForOpeningGame.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Удаляем фон диалогового окна
        dialogMakeForOpeningGame.setCancelable(false);       // Запрещаем закрывать диалоговое окно кликом за пределами диалогового окны

        TextView btnClose2 = dialogMakeForOpeningGame.findViewById(R.id.button_close);       // Кнопка Назад
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMakeForOpeningGame.dismiss();       // Закрываем диалоговое окно
            }
        });

        //-----------------------------------------------------------

        LinearLayout pictureCards = findViewById(R.id.picture_cards);
        pictureCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play_1_of_2.this, PictureCards.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });

        LinearLayout rabbitHat = findViewById(R.id.where_is_the_rabbit);
        if (lvlPicCar>=4){       // Если у предыдущей игры пройден 3-ий уровень
            rabbitHat.setBackgroundResource(R.drawable.background_gif_lvl_open);       // Отображаем бэкграунд цветом открытой игры
        }

        rabbitHat.setOnClickListener(new View.OnClickListener() {       // Проверяем нажание на игру -Где кролик-
            @Override
            public void onClick(View v) {
                if (lvlPicCar>=4){       // Если у предыдущей игры пройден 3-ий уровень
                    Intent intent = new Intent(Play_1_of_2.this, WhereIsTheRabbit.class);       // Создаём переменную для перехода к игре -Где кролик-
                    startActivity(intent);       // Переходим к игре -Где кролик-
                    overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                } else {       // Если нет
                    dialogMakeForOpeningGame.show();       // Отображаем диалоговое окно -Что нужно сделать для открытия игры-
                }
            }
        });

        // ------------  Переход обратно в меню  ----------------
        Button bntMenuBack = findViewById(R.id.bnt_dif_back);
        bntMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play_1_of_2.this, ChoicePlay.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });
        // ------------------------------------------------------
    }
}