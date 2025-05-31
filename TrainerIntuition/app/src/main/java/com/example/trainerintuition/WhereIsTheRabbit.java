package com.example.trainerintuition;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class WhereIsTheRabbit extends AppCompatActivity {

    Dialog dialogStart;       // Объявляем переменную для обращения к диалоговому окну в начале игры
    Dialog dialogEndLost, dialogEndVictory, dialogEndAbsoluteVictory;       // Объявляем переменные для обращения к диалоговому окну в конце игры
    private int spaceOrRabbit;       // Объявляем переменную -spaceOrRabbit- наличия кролика в шляпе
    private ImageView imgHat1, imgHat2;       // Объявляем переменные для полей левой и правой карт соответственно
    private Animation animCard;       // Объявляем переменную для альфа анимации левой и правой карт
    Random random = new Random();       // Создаём переменную для рандомных чисел
    private int count,attempts, errorCounter;       // Объявляем переменные -count- счётчик прогресса, -attempts- колличество попыток, -errorCounter- счётчик ошибок


    @SuppressLint({"WrongViewCast", "MissingInflatedId", "ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_is_the_rabbit);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);       // Чтобы верхняя шторка не мешала

        // ------------------------  Вызов диалогового окна в начале игры  ------------------------
        dialogStart = new Dialog(this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogStart.setContentView(R.layout.preview_dialog);
        Objects.requireNonNull(dialogStart.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Удаляем фон диалогового окна
        dialogStart.setCancelable(false);       // Запрещаем закрывать диалоговое окно кликом за пределами диалогового окны

        ImageView prevImg = dialogStart.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg.setImageResource(R.drawable.rabbit_hat_hat);       // Заменяем превью картинку на превью картинку -где кролик-
        TextView textPrev = dialogStart.findViewById(R.id.text_description);       // Создаём переменную для обращения к полю теката превью
        textPrev.setText(R.string.where_is_the_rabbit_dialog_prew);       // Заменяем превью текст на превью текст -где кролик-


        TextView btnClose = dialogStart.findViewById(R.id.button_close);       // Кнопка Назад
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhereIsTheRabbit.this, Play_1_of_2.class);
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
            }
        });

        dialogStart.show();       // Показать диалоговое окно

        //-----------------------------------------------------------

        // ------------------------  Вызов диалогового окна проигрыша в конце игры  ------------------------
        dialogEndLost = new Dialog(this);
        dialogEndLost.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogEndLost.setContentView(R.layout.dialog_end_lost);       //Путь к диалоговому окну
        Objects.requireNonNull(dialogEndLost.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Прозрачный фон
        dialogEndLost.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);       // Чтобы диалоговое окно расщирялось на всё окно
        dialogEndLost.setCancelable(false);       // Чтобы нельзя было закрыть окно за пределами окна

        ImageView prevImg2 = dialogEndLost.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg2.setImageResource(R.drawable.rabbit_hat_hat);       // Заменяем превью картинку на превью картинку -где кролик-
        TextView textDescriptionEnd = dialogEndLost.findViewById(R.id.text_description);
        textDescriptionEnd.setText(R.string.dialog_lost);

        TextView btnClose2 = dialogEndLost.findViewById(R.id.button_close);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(WhereIsTheRabbit.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndLost.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue2 = dialogEndLost.findViewById(R.id.button_continue);
        buttonContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhereIsTheRabbit.this, WhereIsTheRabbit.class);
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
        Objects.requireNonNull(dialogEndVictory.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Прозрачный фон
        dialogEndVictory.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);       // Чтобы диалоговое окно расщирялось на всё окно
        dialogEndVictory.setCancelable(false);       // Чтобы нельзя было закрыть окно за пределами окна

        ImageView prevImg3 = dialogEndVictory.findViewById(R.id.preview_img);       // Создаём переменную для обращения к полю картинки превью
        prevImg3.setImageResource(R.drawable.rabbit_hat_hat);       // Заменяем превью картинку на превью картинку -где кролик-
        TextView textDescriptionEnd2 = dialogEndVictory.findViewById(R.id.text_description);
        textDescriptionEnd2.setText(R.string.dialog_victory);

        TextView btnClose3 = dialogEndVictory.findViewById(R.id.button_close);
        btnClose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(WhereIsTheRabbit.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndVictory.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue3 = dialogEndVictory.findViewById(R.id.button_continue);
        buttonContinue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhereIsTheRabbit.this, WhereIsTheRabbit.class);
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
        prevImg4.setImageResource(R.drawable.rabbit_hat_hat);       // Заменяем превью картинку на превью картинку -где кролик-
        TextView textDescriptionEnd3 = dialogEndAbsoluteVictory.findViewById(R.id.text_description);
        textDescriptionEnd3.setText(R.string.dialog_absolute_victory);

        TextView btnClose4 = dialogEndVictory.findViewById(R.id.button_close);
        btnClose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(WhereIsTheRabbit.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndAbsoluteVictory.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue4 = dialogEndAbsoluteVictory.findViewById(R.id.button_continue);
        buttonContinue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhereIsTheRabbit.this, Play_1_of_2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                dialogEndAbsoluteVictory.dismiss();
            }
        });
        //-----------------------------------------------------------


//        Timer timer = new Timer();       // Создаём переменную таймера

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);       // Создаём/обращаемся к файлу сохранения
        final int prefProgressWhereRabbit = save.getInt("LvlWheRab", 1);       // Создаём переменную прогресса игры -Карточки с картинкой-
        TextView textViewWhereRabbitLvl = findViewById(R.id.text_view_where_is_the_rabbit_lvl);       // Создаём переменную для обращения к полю уровня -где кролик-
        String textViewWhereRabbitLvlTmp = (String) textViewWhereRabbitLvl.getText();       // Создаём временную переменную текста уровня
        textViewWhereRabbitLvl.setText(textViewWhereRabbitLvlTmp + prefProgressWhereRabbit);       // Вставляем в поле уровня текст -уровень- и номер уровня

        switch (prefProgressWhereRabbit){       // Определяем уровень и задаём переменные количества попыток и ошибок, соответственно уровню
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

        // Массив для прогресса игры
        final int[] progress = {
                R.id.point_1, R.id.point_2, R.id.point_3, R.id.point_4, R.id.point_5,
                R.id.point_6, R.id.point_7, R.id.point_8, R.id.point_9, R.id.point_10,
        };

        // Закрашиваем нужное количество очков прогресса серым цветом
        for (int i = 0; i < attempts; i++) {
            TextView textView = findViewById(progress[i]);
            textView.setBackgroundResource(R.drawable.point);
        }

        imgHat1 = findViewById(R.id.img_hat1);       // Создаём переменную для обращения к полю левой шляпы
        imgHat2 = findViewById(R.id.img_hat2);       // Создаём переменную для обращения к полю правой шляпы

        spaceOrRabbit = rand(2);       // Определяем в какой шляпе спрятался кролик, -0- в левой, -1- в правой
        count = 0;       // Устанавливаем счётчик хода на -0-

        imgHat1.setImageResource(R.drawable.hat);       // Присваиваем левому полю картинку шляпы
        imgHat2.setImageResource(R.drawable.hat);       // Присваиваем правому полю картинку шляпы

        // Обрабатываем нажате на левую шляпу
        imgHat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spaceOrRabbit == 0) {       // Если кролик в левой шляпе
                    imgHat1.setImageResource(R.drawable.rabbit_hat_animation);       // Заменяем левую картинку на анимацию с кроликом
                    ((AnimationDrawable) imgHat1.getDrawable()).start();
                    TextView textView = findViewById(progress[count]);       // Создаём переменную для текстового поля прогресса по номеру попытки
                    textView.setBackgroundResource(R.drawable.point_green);       // Заменяем его на зелёный
                    count++;       // Увеличиваем счётчик на 1
                } else {       // В противном случае
                    imgHat2.setImageResource(R.drawable.rabbit_hat_animation);       // Заменяем правую картинку на анимацию с кроликом
                    ((AnimationDrawable) imgHat2.getDrawable()).start();
                    TextView textView = findViewById(progress[count]);       // Создаём переменную для текстового поля прогресса по номеру попытки
                    textView.setBackgroundResource(R.drawable.point_red);       // Заменяем его на красный
                    count++;       // Увеличиваем счётчик на 1
                    errorCounter--;       // Уменьшаем счётчик ошибок на 1
                }
                if (errorCounter < 0) {       // Если количество ошибок больше допустимого

                    // -------  Чтобы сначало прошла анимация последнего кролика, а потом всплыло диалоговое окно -------
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogEndLost.show();       // Показываем диалог проигрыша
                        }
                    }, 3500);
                    // ---------------------------------------------------------------------------------------------------

                } else if (count == attempts) {       // Если счётчик ходов стал равным количеству ходов на уровне
                    if (attempts < 10) {       // Проверяем, если не 10-ый уровень
                        SharedPreferences.Editor editor = save.edit();       // Создаём переменную для редактирования сохранения
                        editor.putInt("LvlWheRab", prefProgressWhereRabbit + 1);       // Сохраняем по ключу -Level-, значение увеличенное на -1-
                        editor.apply();       // Сохраняем данные

                        // -------  Чтобы сначало прошла анимация последнего кролика, а потом всплыло диалоговое окно -------
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogEndVictory.show();       // Показываем диалог победы
                            }
                        }, 3500);
                        // ---------------------------------------------------------------------------------------------------

                    } else {
                        // -------  Чтобы сначало прошла анимация последнего кролика, а потом всплыло диалоговое окно -------
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogEndAbsoluteVictory.show();       // Показываем диалог прохождения всех уровней
                            }
                        }, 3500);
                        // ---------------------------------------------------------------------------------------------------

                    }
                } else {
                    spaceOrRabbit = rand(2);
                }
            }
        });

        // Обрабатываем нажате на правую шляпу
        imgHat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spaceOrRabbit == 1) {       // Если кролик в левой шляпе
                    imgHat2.setImageResource(R.drawable.rabbit_hat_animation);       // Заменяем правцю картинку на анимацию с кроликом
                    ((AnimationDrawable) imgHat2.getDrawable()).start();
                    TextView textView = findViewById(progress[count]);       // Создаём переменную для текстового поля прогресса по номеру попытки
                    textView.setBackgroundResource(R.drawable.point_green);       // Заменяем его на зелёный
                    count++;       // Увеличиваем счётчик на 1
                } else {       // В противном случае
                    imgHat1.setImageResource(R.drawable.rabbit_hat_animation);       // Заменяем левую картинку на анимацию с кроликом
                    ((AnimationDrawable) imgHat1.getDrawable()).start();
                    TextView textView = findViewById(progress[count]);       // Создаём переменную для текстового поля прогресса по номеру попытки
                    textView.setBackgroundResource(R.drawable.point_red);       // Заменяем его на красный
                    count++;       // Увеличиваем счётчик на 1
                    errorCounter--;       // Уменьшаем счётчик ошибок на 1

                }
                if (errorCounter < 0) {       // Если количество ошибок больше допустимого

                    // -------  Чтобы сначало прошла анимация последнего кролика, а потом всплыло диалоговое окно -------
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogEndLost.show();       // Показываем диалог проигрыша
                        }
                    }, 3500);
                    // ---------------------------------------------------------------------------------------------------

                } else if (count == attempts) {       // Если счётчик ходов стал равным количеству ходов на уровне
                    if (attempts < 10) {       // Проверяем, если не 10-ый уровень
                        SharedPreferences.Editor editor = save.edit();       // Создаём переменную для редактирования сохранения
                        editor.putInt("LvlWheRab", prefProgressWhereRabbit + 1);       // Сохраняем по ключу -Level-, значение увеличенное на -1-
                        editor.apply();       // Сохраняем данные
                        // -------  Чтобы сначало прошла анимация последнего кролика, а потом всплыло диалоговое окно -------
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogEndVictory.show();       // Показываем диалог победы
                            }
                        }, 3500);
                        // ---------------------------------------------------------------------------------------------------
                    } else {
                        // -------  Чтобы сначало прошла анимация последнего кролика, а потом всплыло диалоговое окно -------
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogEndAbsoluteVictory.show();       // Показываем диалог прохождения всех уровней
                            }
                        }, 3500);
                        // ---------------------------------------------------------------------------------------------------
                    }
                } else {
                    spaceOrRabbit = rand(2);
                }
            }
        });

        TextView bntBack = findViewById(R.id.button_back_1_2);       // Кнопка выхода в меню выбора игр
        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhereIsTheRabbit.this, Play_1_of_2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });

    }


    private int rand(int n) {
        return random.nextInt(n);
    }

}