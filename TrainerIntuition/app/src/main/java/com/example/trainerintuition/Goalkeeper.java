package com.example.trainerintuition;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Goalkeeper extends AppCompatActivity {

    Dialog dialogStart;       // Объявляем переменную для обращения к диалоговому окну в начале игры
    Dialog dialogEndLost, dialogEndVictory, dialogEndAbsoluteVictory;       // Объявляем переменные для обращения к диалоговому окну в конце игры
    private int leftRight;       // Объявляем переменную -leftRight-, куда ударит футболист, -0- левая часть ворот, -1- правая часть ворот
    private int leftRightStop;       // Объявляем переменную -leftRightStop-, выбор игрока лево/право/не нажал -0-/-1-/-2-
    private ImageView imgGoalkeeperTable;       // Объявляем переменную для обращения к полю футбольного поля
    private ImageView imgLeft, imgRight;       // Объявляем переменные для левого и правого полей
    Random random = new Random();       // Создаём переменную для рандомных чисел
    private int count,attempts, errorCounter;       // Объявляем переменные -count- счётчик прогресса, -attempts- колличество попыток, -errorCounter- счётчик ошибок



    // Массив для прогресса игры
    final int[] progress = {
            R.id.point_1, R.id.point_2, R.id.point_3, R.id.point_4, R.id.point_5,
            R.id.point_6, R.id.point_7, R.id.point_8, R.id.point_9, R.id.point_10,
    };

    @SuppressLint({"WrongViewCast", "MissingInflatedId", "ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalkeeper);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);       // Чтобы верхняя шторка не мешала

        // ------------------------  Вызов диалогового окна в начале игры  ------------------------
        dialogStart = new Dialog(this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogStart.setContentView(R.layout.preview_dialog);
        Objects.requireNonNull(dialogStart.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Удаляем фон диалогового окна
        dialogStart.setCancelable(false);       // Запрещаем закрывать диалоговое окно кликом за пределами диалогового окны

        ImageView prevImg = dialogStart.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg.setImageResource(R.drawable.goalkeeper_prev);       // Заменяем превью картинку на превью картинку -Вратарь-
        TextView textPrev = dialogStart.findViewById(R.id.text_description);       // Создаём переменную для обращения к полю теката превью
        textPrev.setText(R.string.goalkeeper_dialog_peview);       // Заменяем превью текст на превью текст -Вратарь-

        dialogStart.show();       // Показать диалоговое окно

        // -----------------------------------------------------------

        // ------------------------  Вызов диалогового окна проигрыша в конце игры  ------------------------
        dialogEndLost = new Dialog(this);
        dialogEndLost.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogEndLost.setContentView(R.layout.dialog_end_lost);       //Путь к диалоговому окну
        Objects.requireNonNull(dialogEndLost.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Прозрачный фон
        dialogEndLost.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);       // Чтобы диалоговое окно расщирялось на всё окно
        dialogEndLost.setCancelable(false);       // Чтобы нельзя было закрыть окно за пределами окна

        ImageView prevImg2 = dialogEndLost.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg2.setImageResource(R.drawable.goalkeeper_prev);       // Заменяем превью картинку на превью картинку -Вратарь-
        TextView textDescriptionEnd = dialogEndLost.findViewById(R.id.text_description);
        textDescriptionEnd.setText(R.string.dialog_lost);

        TextView btnClose2 = dialogEndLost.findViewById(R.id.button_close);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(Goalkeeper.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndLost.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue2 = dialogEndLost.findViewById(R.id.button_continue);
        buttonContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goalkeeper.this, Goalkeeper.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                dialogEndLost.dismiss();
            }
        });

        //-----------------------------------------------------------


        // ------------------------  Вызов диалогового окна выигрыша в конце игры  ------------------------
        dialogEndVictory = new Dialog(this);
        dialogEndVictory.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogEndVictory.setContentView(R.layout.dialog_end_victory);       //Путь к диалоговому окну
        dialogEndVictory.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Прозрачный фон
        dialogEndVictory.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);       // Чтобы диалоговое окно расщирялось на всё окно
        dialogEndVictory.setCancelable(false);       // Чтобы нельзя было закрыть окно за пределами окна

        ImageView prevImg3 = dialogEndVictory.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg3.setImageResource(R.drawable.goalkeeper_prev);       // Заменяем превью картинку на превью картинку -Вратарь-
        TextView textDescriptionEnd2 = dialogEndVictory.findViewById(R.id.text_description);
        textDescriptionEnd2.setText(R.string.dialog_victory);

        TextView btnClose3 = dialogEndVictory.findViewById(R.id.button_close);
        btnClose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(Goalkeeper.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndVictory.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue3 = dialogEndVictory.findViewById(R.id.button_continue);
        buttonContinue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goalkeeper.this, Goalkeeper.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                dialogEndVictory.dismiss();
            }
        });
        //-----------------------------------------------------------

        // ------------------------  Вызов диалогового окна выигрыша в конце игры на 10-м уровне ------------------------
        dialogEndAbsoluteVictory = new Dialog(this);
        dialogEndAbsoluteVictory.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogEndAbsoluteVictory.setContentView(R.layout.dialog_end_absolute_victory);       //Путь к диалоговому окну
        dialogEndAbsoluteVictory.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Прозрачный фон
        dialogEndAbsoluteVictory.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);       // Чтобы диалоговое окно расщирялось на всё окно
        dialogEndAbsoluteVictory.setCancelable(false);       // Чтобы нельзя было закрыть окно за пределами окна

        ImageView prevImg4 = dialogEndAbsoluteVictory.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg4.setImageResource(R.drawable.goalkeeper_prev);       // Заменяем превью картинку на превью картинку -Вратарь-
        TextView textDescriptionEnd3 = dialogEndAbsoluteVictory.findViewById(R.id.text_description);
        textDescriptionEnd3.setText(R.string.dialog_absolute_victory);

        TextView btnClose4 = dialogEndVictory.findViewById(R.id.button_close);
        btnClose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(Goalkeeper.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndAbsoluteVictory.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue4 = dialogEndAbsoluteVictory.findViewById(R.id.button_continue);
        buttonContinue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goalkeeper.this, Play_1_of_2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                dialogEndAbsoluteVictory.dismiss();
            }
        });
        //-----------------------------------------------------------



        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);       // Создаём/обращаемся к файлу сохранения
        final int prefProgressGoalkeeper = save.getInt("LvlGoalkeeper", 1);       // Создаём переменную прогресса игры -Вратать-
        TextView textViewGoalkeeperLvl = findViewById(R.id.text_view_goalkeeper_lvl);       // Создаём переменную для обращения к полю уровня -Вратать-
        String textViewGoalkeeperLvlTmp = (String) textViewGoalkeeperLvl.getText();       // Создаём временную переменную текста уровня
        textViewGoalkeeperLvl.setText(textViewGoalkeeperLvlTmp + prefProgressGoalkeeper);       // Вставляем в поле уровня текст -уровень- и номер уровня

        switch (prefProgressGoalkeeper){       // Определяем уровень и задаём переменные количества попыток и ошибок, соответственно уровню
            case 1:
                attempts = 4;
                errorCounter = 3;
                break;
            case 2:
                attempts = 5;
                errorCounter = 3;
                break;
            case 3:
                attempts = 6;
                errorCounter = 3;
                break;
            case 4:
                attempts = 7;
                errorCounter = 3;
                break;
            case 5:
                attempts = 8;
                errorCounter = 3;
                break;
            case 6:
                attempts = 9;
                errorCounter = 3;
                break;
            case 7:
                attempts = 10;
                errorCounter = 3;
                break;
            case 8:
                attempts = 10;
                errorCounter = 2;
                break;
            case 9:
                attempts = 10;
                errorCounter = 1;
                break;
            case 10:
                attempts = 10;
                errorCounter = 0;
                break;
        }



        // Закрашиваем нужное количество очков прогресса серым цветом
        for (int i = 0; i < attempts; i++) {
            TextView textView = findViewById(progress[i]);
            textView.setBackgroundResource(R.drawable.point);
        }

        imgGoalkeeperTable = findViewById(R.id.img_goalkeeper_table);       // Создаём переменную для обращения к полю анимации футбольного поля
        imgLeft = findViewById(R.id.img_left);       // Создаём переменную для обращения к полю левой стрелке
        imgRight = findViewById(R.id.img_right);       // Создаём переменную для обращения к полю правой стрелке
        leftRight = rand(2);       // Определяем куда будет бить футболист -0- левый край -1- правый край
        count = 0;       // Устанавливаем счётчик хода на -0-

        imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_time_five);       // Присваиваем игровому полю анимацию отсчёта времени
        imgLeft.setImageResource(R.drawable.baseline_chevron_left_24);       // Присваиваем левому полю левую стрелку
        imgLeft.setBackgroundResource(R.drawable.background_gif_lvl_open);
        imgRight.setImageResource(R.drawable.outline_chevron_right_24);       // Присваиваем левому полю левую стрелку
        imgRight.setBackgroundResource(R.drawable.background_gif_lvl_open);


        TextView btnClose = dialogStart.findViewById(R.id.button_close);       // Кнопка Назад
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goalkeeper.this, Play_1_of_2.class);
                startActivity(intent);       // Возвращаемся на предидущее окно
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                dialogStart.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button btnContinue = dialogStart.findViewById(R.id.button_continue);       // Кнопка Продолжить
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogStart.dismiss();       // Закрываем диалоговое окно
                leftRightStop = 2;
                Handler handler = new Handler();       // Делаем небольшую задержку после закрытия диалогового окна
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию отсчёта времени
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                penaltyKicks();
                            }
                        }, 6000);
                    }
                }, 2000);


            }
        });



        // Обрабатываем нажате на левую стрелку
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ----------- Делаем, чтобы левая и правая стрелки были не кликабельны пока проходит анимация ------------
                imgLeft.setClickable(false);
                imgRight.setClickable(false);
                imgRight.setBackgroundResource(R.drawable.background_gif_lvl_closed);

                // -------------------------------------------------------------------------------------------------------------------------------

                leftRightStop = 0;

            }
        });


        // Обрабатываем нажате на правую карту
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ----------- Делаем, чтобы левая и правая стрелки были не кликабельны пока проходит анимация ------------
                imgLeft.setClickable(false);
                imgRight.setClickable(false);
                imgLeft.setBackgroundResource(R.drawable.background_gif_lvl_closed);

                // -------------------------------------------------------------------------------------------------------------------------------

                leftRightStop = 1;

            }
        });

        TextView bntBack = findViewById(R.id.button_back_1_2);       // Кнопка выхода в меню выбора игр
        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goalkeeper.this, Play_1_of_2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });

    }

    private void penaltyKicks() {
        imgLeft.setBackgroundResource(R.drawable.background_gif_lvl_closed);
        imgRight.setBackgroundResource(R.drawable.background_gif_lvl_closed);
        imgLeft.setClickable(false);
        imgRight.setClickable(false);


        if (leftRight == 0){
            switch (leftRightStop){
                case 0:
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_left_left);
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -удар влево, вратарь влево-
                    TextView textView = findViewById(progress[count]);
                    textView.setBackgroundResource(R.drawable.point_green);
                    leftRightStop=2;
                    count++;
                    break;
                case 1:
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_left_right);
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -удар влево, вратарь вправо-
                    TextView textView2 = findViewById(progress[count]);
                    textView2.setBackgroundResource(R.drawable.point_red);
                    leftRightStop=2;
                    errorCounter--;
                    count++;
                    break;
                case 2:
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_left_stop);
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -удар влево, вратарь стоит-
                    TextView textView3 = findViewById(progress[count]);
                    textView3.setBackgroundResource(R.drawable.point_red);
                    leftRightStop=2;
                    errorCounter--;
                    count++;
                    break;
                default:
                    break;
            }
        }else {
            switch (leftRightStop){
                case 0:
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_right_left);
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -удар вправо, вратарь влево-
                    TextView textView = findViewById(progress[count]);
                    textView.setBackgroundResource(R.drawable.point_red);
                    leftRightStop=2;
                    errorCounter--;
                    count++;
                    break;
                case 1:
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_right_right);
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -удар вправо, вратарь вправо-
                    TextView textView2 = findViewById(progress[count]);
                    textView2.setBackgroundResource(R.drawable.point_green);
                    leftRightStop=2;
                    count++;
                    break;
                case 2:
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_right_stop);
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -удар вправо, вратарь стоит-
                    TextView textView3 = findViewById(progress[count]);
                    textView3.setBackgroundResource(R.drawable.point_red);
                    leftRightStop=2;
                    errorCounter--;
                    count++;
                    break;
                default:
                    break;
            }

        }

        // ------------ Совершать ходы, если ещё остались попытки

        if (errorCounter>=0 && count<attempts){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgGoalkeeperTable.setImageResource(R.drawable.goalkeeper_animation_time_five);       // Присваиваем игровому полю анимацию отсчёта времени
                    ((AnimationDrawable) imgGoalkeeperTable.getDrawable()).start();       // Запускаем анимацию -отсчёта времени-
                    leftRight = rand(2);
                    imgLeft.setClickable(true);
                    imgRight.setClickable(true);
                    imgLeft.setBackgroundResource(R.drawable.background_gif_lvl_open);
                    imgRight.setBackgroundResource(R.drawable.background_gif_lvl_open);

                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            penaltyKicks();
                        }
                    }, 6000);
                }
            }, 3000);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    chek();
                }
            }, 3000);
        }
        // ---------------------------------------------------

    }
    private int rand ( int n){
        return random.nextInt(n);
    }

    private void chek(){
        if (errorCounter < 0) {
            dialogEndLost.show();
        } else if (count == attempts) {
            if (attempts < 10) {
                SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);       // Создаём/обращаемся к файлу сохранения
                final int prefProgressGoalkeeper = save.getInt("LvlGoalkeeper", 1);       // Создаём переменную прогресса игры -Вратать-
                SharedPreferences.Editor editor = save.edit();       // Создаём переменную для редактирования сохранения
                editor.putInt("LvlGoalkeeper", prefProgressGoalkeeper + 1);       // Сохраняем по ключу -Level-, значение -2-
                editor.apply();       // Сохраняем данные
                dialogEndVictory.show();
            }else {
                dialogEndAbsoluteVictory.show();
            }
        }
    }
}