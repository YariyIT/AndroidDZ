package com.example.dz07_gamexo;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThreeToThree extends AppCompatActivity {

    private ImageView[] image = new ImageView[9];       // Объявляем переменные для обращения к полям

    private TextView playerScore;       // Создаём переменную для обращения к текстовому полю -pl- счёт игрока
    private TextView computerScore;       // Создаём переменную для обращения к текстовому полю -com- счёт компьютера
    private TextView textTurn;       // Создаём переменную для обращения к текстовому полю -textTurn-

    private int turnWinner = 1;       // Создаём переменную определение хода и победителя: 1 - ходит/победил игрок; 2 - ходит/победил компьютер;
    private int scoreTurn = 0;       // Создаём переменную счёта ходов
    private boolean motion = true;       // Создаём переменную продолжения/завершения игры
    private int[] playField = {0, 0, 0, 0, 0, 0, 0, 0, 0};       // Создаём массив игрового поля
    private final int[][] fieldCheckWin = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_to_three);

        playerScore = findViewById(R.id.pl);
        computerScore = findViewById(R.id.com);
        textTurn = findViewById(R.id.textTurn);

        Intent intGameMode = getIntent();       // Получаем данные, переданные из другого aktivity
        String gameMode = intGameMode.getStringExtra("game_mode");       // Создаём переменную, и записываем в неё данные -game_mode-, какой режим игры выбран
        int gm = Integer.parseInt(gameMode);

        image[0] = findViewById(R.id.image1);       // Присваеваем переменной -image1- ссылку для обращения к полю -image1-
        image[1] = findViewById(R.id.image2);
        image[2] = findViewById(R.id.image3);
        image[3] = findViewById(R.id.image4);
        image[4] = findViewById(R.id.image5);
        image[5] = findViewById(R.id.image6);
        image[6] = findViewById(R.id.image7);
        image[7] = findViewById(R.id.image8);
        image[8] = findViewById(R.id.image9);

        image[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTurn.setText("Нажатие");
                if (checkPositionField(0)) {
                    action((ImageView) v, 0);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(1)) {
                    action((ImageView) v, 1);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(2)) {
                    action((ImageView) v, 2);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(3)) {
                    action((ImageView) v, 3);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(4)) {
                    action((ImageView) v, 4);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(5)) {
                    action((ImageView) v, 5);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(6)) {
                    action((ImageView) v, 6);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(7)) {
                    action((ImageView) v, 7);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        image[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(8)) {
                    action((ImageView) v, 8);       // запускаем метод совершения хода
                    try {
                        actionComp(gm);       // запускаем метод совершения хода
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }
    private boolean checkPositionField(int positionField){       // Метод проверки поля по позиции пусто/занято
        boolean space = false;       // Создаём переменную для возвращения значения поле занято/свободно, и присваеваем значение -занято-
        if (playField[positionField] == 0){       // Проверяем поле на пустоту
            space = true;       // Если пустое, присваеваем значение -true-
        }
        return space;       // Возвращаем значение поля пусто/занято
    }

    private void action(ImageView imageView, int positionField) {       // Метод совершения хода

        scoreTurn++;

        playField[positionField] = 1;
        textTurn.setText("+");
        // Ходит игрок
            imageView.setImageResource(R.drawable.anim_x);       // Заменяем пустое изображение анимацией КРЕСТИКА
            ((AnimationDrawable) image[positionField].getDrawable()).start();       // Запускаем анимацию отрисовки КРЕСТИКА

            if (checkWin()) {       // Проверяем являлся ли ход победным
                textTurn.setText("Вы победили!");
                PartyResult3 partyResult3 = new PartyResult3(ThreeToThree.this, "Вы победили!", ThreeToThree.this);       // Открываем диалоговое окно
                partyResult3.setCancelable(false);       // Делаем, чтобы диалоговое окно не зкрывалось при нажатии за пределами
                partyResult3.show();       // Показываем диалоговое окно
            }

//
//





    }

    private void actionComp(int gm) throws InterruptedException {
        Thread.sleep(1500);
        if (scoreTurn == 5){       // Если ни кто не победил
            PartyResult3 partyResult3 = new PartyResult3(ThreeToThree.this, "Ничья!", ThreeToThree.this);       // Открываем диалоговое окно
            partyResult3.setCancelable(false);       // Делаем, чтобы диалоговое окно не зкрывалось при нажатии за пределами
            partyResult3.show();       // Показываем диалоговое окно
        }else {
            textTurn.setText("Ходит компьютер");

            if (gm == 1){       // Проверка режима игры
                // Если режим игры рандомный
                int posComp = randTurn();       // Создаём переменную для получения позиции хода компьютера, и запускаем рандомный ход
                playField[posComp] = 2;
                ImageView imageView1 = image[posComp];       // Создаём переменную для поля хода компьютера, и добавляем на ссылку поля по индексу, полученному рандомно
                imageView1.setImageResource(R.drawable.anim_o);       // Заменяем пустое изображение анимацией НОЛИКА
                ((AnimationDrawable) image[posComp].getDrawable()).start();       // Запускаем анимацию отрисовки НОЛИКА
                if (checkWin()){       // Проверяем являлся ли ход победным
                    PartyResult3 partyResult3 = new PartyResult3(ThreeToThree.this, "Компьютер победил!", ThreeToThree.this);       // Открываем диалоговое окно
                    partyResult3.setCancelable(false);       // Делаем, чтобы диалоговое окно не зкрывалось при нажатии за пределами
                    partyResult3.show();       // Показываем диалоговое окно
                }
            }else{

            }
            textTurn.setText("Ходите");
            turnWinner = 1;
        }
    }

    private boolean checkWin(){       // Метод проверки победы
        boolean space = false;
        for (int i = 0; i < fieldCheckWin.length; i++) {
            if(playField[fieldCheckWin[i][0]] == turnWinner && playField[fieldCheckWin[i][1]] == turnWinner && playField[fieldCheckWin[i][2]] == turnWinner){
                space = true;
            }
        }
        return space;
    }

    private int randTurn(){
        int tmp;
        List<int[]> randField = new ArrayList<>();
        for (int i = 0; i < playField.length; i++) {
            if (playField[i] == 0){
                randField.add(new int[] {i});
            }
        }
        tmp = (int) (Math.random()*randField.size());
        int[] turn = randField.get(tmp);
        tmp = turn[0];
        return tmp;
    }
}