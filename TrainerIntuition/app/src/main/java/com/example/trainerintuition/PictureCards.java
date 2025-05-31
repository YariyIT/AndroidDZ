package com.example.trainerintuition;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PictureCards extends AppCompatActivity {

    Dialog dialogStart;       // Объявляем переменную для обращения к диалоговому окну в начале игры
    Dialog dialogEndLost, dialogEndVictory, dialogEndAbsoluteVictory;       // Объявляем переменные для обращения к диалоговому окну в конце игры
    private int picture, spaceOrPicture;       // Объявляем переменные -picture- какая картинка на карте, -spaceOrPicture- карта пустая или с картинкой
    private ImageView imgPictureCardsTable;       // Объявляем переменную для обращения к полю колоды карт
    private ImageView imgCard1, imgCard2;       // Объявляем переменные для полей левой и правой карт соответственно
    private Animation animCard;       // Объявляем переменную для альфа анимации левой и правой карт
    Random random = new Random();       // Создаём переменную для рандомных чисел
    private int count,attempts, errorCounter;       // Объявляем переменные -count- счётчик прогресса, -attempts- колличество попыток, -errorCounter- счётчик ошибок

    @SuppressLint({"WrongViewCast", "MissingInflatedId", "ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_cards);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);       // Чтобы верхняя шторка не мешала

        // ------------------------  Вызов диалогового окна в начале игры  ------------------------
        dialogStart = new Dialog(this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogStart.setContentView(R.layout.preview_dialog);
        Objects.requireNonNull(dialogStart.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Удаляем фон диалогового окна
        dialogStart.setCancelable(false);       // Запрещаем закрывать диалоговое окно кликом за пределами диалогового окны
        dialogStart.show();       // Показать диалоговое окно

        //-----------------------------------------------------------

        // ------------------------  Вызов диалогового окна проигрыша в конце игры  ------------------------
        dialogEndLost = new Dialog(this);
        dialogEndLost.requestWindowFeature(Window.FEATURE_NO_TITLE);       // Скрываем заголовок
        dialogEndLost.setContentView(R.layout.dialog_end_lost);       //Путь к диалоговому окну
        dialogEndLost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));       // Прозрачный фон
        dialogEndLost.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);       // Чтобы диалоговое окно расщирялось на всё окно
        dialogEndLost.setCancelable(false);       // Чтобы нельзя было закрыть окно за пределами окна

        TextView textDescriptionEnd = dialogEndLost.findViewById(R.id.text_description);
        textDescriptionEnd.setText(R.string.dialog_lost);

        TextView btnClose2 = dialogEndLost.findViewById(R.id.button_close);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(PictureCards.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndLost.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue2 = dialogEndLost.findViewById(R.id.button_continue);
        buttonContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureCards.this, PictureCards.class);
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

        TextView textDescriptionEnd2 = dialogEndVictory.findViewById(R.id.text_description);
        textDescriptionEnd2.setText(R.string.dialog_victory);

        TextView btnClose3 = dialogEndVictory.findViewById(R.id.button_close);
        btnClose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(PictureCards.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndVictory.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue3 = dialogEndVictory.findViewById(R.id.button_continue);
        buttonContinue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureCards.this, PictureCards.class);
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

        TextView textDescriptionEnd3 = dialogEndAbsoluteVictory.findViewById(R.id.text_description);
        textDescriptionEnd3.setText(R.string.dialog_absolute_victory);

        TextView btnClose4 = dialogEndVictory.findViewById(R.id.button_close);
        btnClose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вернёмся к выбору уровня
                Intent intent = new Intent(PictureCards.this, Play_1_of_2.class);
                startActivity(intent);
                dialogEndAbsoluteVictory.dismiss();       // Закрываем диалоговое окно
            }
        });

        Button buttonContinue4 = dialogEndAbsoluteVictory.findViewById(R.id.button_continue);
        buttonContinue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureCards.this, Play_1_of_2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
                dialogEndAbsoluteVictory.dismiss();
            }
        });
        //-----------------------------------------------------------


        Timer timer = new Timer();       // Создаём переменную таймера

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);       // Создаём/обращаемся к файлу сохранения
        final int prefProgressPictureCards = save.getInt("LvlPicCar", 1);       // Создаём переменную прогресса игры -Карточки с картинкой-
        TextView textViewPictureCardLvl = findViewById(R.id.text_view_picture_card_lvl);       // Создаём переменную для обращения к полю уровня -карточек с картинками-
        String textViewPictureCardLvlTmp = (String) textViewPictureCardLvl.getText();       // Создаём временную переменную текста уровня
        textViewPictureCardLvl.setText(textViewPictureCardLvlTmp + prefProgressPictureCards);       // Вставляем в поле уровня текст -уровень- и номер уровня

        switch (prefProgressPictureCards){       // Определяем уровень и задаём переменные количества попыток и ошибок, соответственно уровню
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

        imgPictureCardsTable = findViewById(R.id.img_picture_cards_table);       // Создаём переменную для обращения к полю анимации колоды карт
        imgCard1 = findViewById(R.id.img_card1);       // Создаём переменную для обращения к полю левой карты
        imgCard2 = findViewById(R.id.img_card2);       // Создаём переменную для обращения к полю правой карты

        picture = rand(4);       // Определяем рисунок карточки
        spaceOrPicture = rand(2);       // Определяем будет ли карточка пустая или с рисунком
        count = 0;       // Устанавливаем счётчик хода на -0-

        TextView btnClose = dialogStart.findViewById(R.id.button_close);       // Кнопка Назад
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureCards.this, Play_1_of_2.class);
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
                Handler handler = new Handler();       // Делаем небольшую задержку после закрытия диалогового окна
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPictureCardsTable.setImageResource(R.drawable.picture_cards_animation);       // Присваиваем игровому полю анимацию сдавания карты
                        imgCard1.setImageResource(R.drawable.card_space);       // Присваиваем полю левой карты пустую карту
                        // Определяем, какой рисунок карты и присваиваем правому полю соответствующую картинку
                        switch (picture) {
                            case 0:
                                imgCard2.setImageResource(R.drawable.card_star);
                                break;
                            case 1:
                                imgCard2.setImageResource(R.drawable.card_square);
                                break;
                            case 2:
                                imgCard2.setImageResource(R.drawable.card_circle);
                                break;
                            case 3:
                                imgCard2.setImageResource(R.drawable.card_triangle);
                                break;
                        }
                        ((AnimationDrawable) imgPictureCardsTable.getDrawable()).start();       // Запускаем анимацию сдавания карты
                        animCard = AnimationUtils.loadAnimation(PictureCards.this, R.anim.anim_alpha);       // Присваиваем переменной -animCard- анимацию проявления с задержкой
                        imgCard1.startAnimation(animCard);       // Занпускаем анимацию у поля левой карты
                        // Запускаем у правого поля анимацию проявления с задержкой
                        imgCard2.startAnimation(animCard);
                    }
                }, 1000);


            }
        });



        // Обрабатываем нажате на левую карту
        imgCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // ----------- Делаем, чтобы левая и правая карточки были не кликабельны пока проходит очередная анимация сдачи карт ------------
                imgCard1.setClickable(false);
                imgCard2.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgCard1.setClickable(true);
                        imgCard2.setClickable(true);
                    }
                }, 2000);
                // -------------------------------------------------------------------------------------------------------------------------------

                if (spaceOrPicture == 0) {       // Если пустая карта, заменяем отображение колоды с картой, с пустой картинкой
                    imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_space);       // Открываем карту, отображая пустую карту
                    TextView textView = findViewById(progress[count]);       // Создаём переменную для текстового поля прогресса по номеру попытки
                    textView.setBackgroundResource(R.drawable.point_green);       // Заменяем его на зелёный
                    count++;       // увеличиваем счётчик на 1
                } else {       // В противном случае, с соответствующей картинкой
                    TextView textView = findViewById(progress[count]);
                    textView.setBackgroundResource(R.drawable.point_red);
                    count++;
                    errorCounter--;
                    switch (picture) {
                        case 0:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_star);
                            break;
                        case 1:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_square);
                            break;
                        case 2:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_circle);
                            break;
                        case 3:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_triangle);
                            break;
                    }
                }
                if (errorCounter < 0) {       // Если количество ошибок больше допустимого
                    dialogEndLost.show();
                } else if (count == attempts) {
                    if (attempts < 10) {
                        SharedPreferences.Editor editor = save.edit();       // Создаём переменную для редактирования сохранения
                        editor.putInt("LvlPicCar", prefProgressPictureCards + 1);       // Сохраняем по ключу -Level-, значение увеличенное на -1-
                        editor.apply();       // Сохраняем данные
                        dialogEndVictory.show();
                    } else {
                        dialogEndAbsoluteVictory.show();
                    }


                } else {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            motion();       // Запускаем метод следующей попытки после срабатывания таймера
                        }
                    }, 1000);
                }


            }
        });

        // Обрабатываем нажате на правую карту
        imgCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ----------- Делаем, чтобы левая и правая карточки были не кликабельны пока проходит очередная анимация сдачи карт ------------
                imgCard1.setClickable(false);
                imgCard2.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgCard1.setClickable(true);
                        imgCard2.setClickable(true);
                    }
                }, 2000);
                // -------------------------------------------------------------------------------------------------------------------------------

                if (spaceOrPicture == 0) {       // Если пустая карта, заменяем отображение колоды с картой, с пустой картинкой
                    imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_space);
                    TextView textView = findViewById(progress[count]);
                    textView.setBackgroundResource(R.drawable.point_red);
                    count++;
                    errorCounter--;
                } else {
                    TextView textView = findViewById(progress[count]);
                    textView.setBackgroundResource(R.drawable.point_green);
                    count++;
                    switch (picture) {       // В противном случае, с соответствующей картинкой
                        case 0:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_star);
                            break;
                        case 1:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_square);
                            break;
                        case 2:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_circle);
                            break;
                        case 3:
                            imgPictureCardsTable.setImageResource(R.drawable.deck_of_cards_card_triangle);
                            break;
                    }
                }
                if (errorCounter < 0) {
                    dialogEndLost.show();
                } else if (count == attempts) {
                        if (attempts < 10) {
                        SharedPreferences.Editor editor = save.edit();       // Создаём переменную для редактирования сохранения
                        editor.putInt("LvlPicCar", prefProgressPictureCards + 1);       // Сохраняем по ключу -Level-, значение -2-
                        editor.apply();       // Сохраняем данные
                        dialogEndVictory.show();
                    } else {
                        dialogEndAbsoluteVictory.show();
                    }
                } else {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            motion();       // Запускаем метод следующей попытки после срабатывания таймера
                        }
                    }, 1000);
                }

            }
        });

        TextView bntBack = findViewById(R.id.button_back_1_2);       // Кнопка выхода в меню выбора игр
        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureCards.this, Play_1_of_2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });

    }

    private int rand(int n) {
        return random.nextInt(n);
    }

    private void motion() {
        picture = rand(4);
        spaceOrPicture = rand(2);
        imgPictureCardsTable.setImageResource(R.drawable.picture_cards_animation);
        ((AnimationDrawable) imgPictureCardsTable.getDrawable()).start();
        imgCard1.setImageResource(R.drawable.card_space);
        imgCard1.startAnimation(animCard);
        switch (picture) {
            case 0:
                imgCard2.setImageResource(R.drawable.card_star);
                imgCard2.startAnimation(animCard);
                break;
            case 1:
                imgCard2.setImageResource(R.drawable.card_square);
                imgCard2.startAnimation(animCard);
                break;
            case 2:
                imgCard2.setImageResource(R.drawable.card_circle);
                imgCard2.startAnimation(animCard);
                break;
            case 3:
                imgCard2.setImageResource(R.drawable.card_triangle);
                imgCard2.startAnimation(animCard);
                break;
        }
    }
}