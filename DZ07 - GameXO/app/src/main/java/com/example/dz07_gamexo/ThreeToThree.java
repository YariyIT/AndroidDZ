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
    private final int[][] fieldCheckWin = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};       // Создаём массив для проверки победы
    private int scorePlayer = 0;
    private int scoreComp = 0;


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
                if (checkPositionField(0)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 0, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(1)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 1, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(2)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 2, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(3)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 3, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(4)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 4, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(5)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 5, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(6)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 6, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(7)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 7, gm);       // запускаем метод совершения хода
                }
            }
        });

        image[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPositionField(8)) {       // Проверяем, пуста ли ячейка поля
                    action((ImageView) v, 8, gm);       // запускаем метод совершения хода
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

    private void action(ImageView imageView, int positionField, int gm) {       // Метод совершения хода
        scoreTurn++;
        textTurn.setText(String.valueOf(scoreTurn));
        if (turnWinner == 1){
            playField[positionField] = 1;
            // Ходит игрок
            imageView.setImageResource(R.drawable.anim_x);       // Заменяем пустое изображение анимацией КРЕСТИКА
            ((AnimationDrawable) image[positionField].getDrawable()).start();       // Запускаем анимацию отрисовки КРЕСТИКА
            if (checkWin()) {       // Проверяем являлся ли ход победным
                PartyResult3 partyResult3 = new PartyResult3(ThreeToThree.this, "Вы победили!", ThreeToThree.this);       // Открываем диалоговое окно
                partyResult3.setCancelable(false);       // Делаем, чтобы диалоговое окно не зкрывалось при нажатии за пределами
                partyResult3.show();       // Показываем диалоговое окно
                scorePlayer++;       // Увеличиваем счётчик побед у игрока
                playerScore.setText(String.valueOf(scorePlayer));       // Передаём значение побед в соответствующее поле
            }else if (scoreTurn == 9){       // Если ни кто не победил
                PartyResult3 partyResult3 = new PartyResult3(ThreeToThree.this, "Ничья!", ThreeToThree.this);       // Открываем диалоговое окно
                partyResult3.setCancelable(false);       // Делаем, чтобы диалоговое окно не зкрывалось при нажатии за пределами
                partyResult3.show();       // Показываем диалоговое окно
            }else {
                turnWinner = 2;
            }

        } else {
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
                    scoreComp++;       // Увеличиваем счётчик побед у компьютера
                    computerScore.setText(String.valueOf(scoreComp));       // Передаём значение побед в соответствующее поле
                }
            }else{
                int posComp = intellegentTurn();       // Создаём переменную для получения позиции хода компьютера, и запускаем интеллектуальный ход
                playField[posComp] = 2;
                ImageView imageView1 = image[posComp];       // Создаём переменную для поля хода компьютера, и добавляем на ссылку поля по полученному индексу
                imageView1.setImageResource(R.drawable.anim_o);       // Заменяем пустое изображение анимацией НОЛИКА
                ((AnimationDrawable) image[posComp].getDrawable()).start();       // Запускаем анимацию отрисовки НОЛИКА
                if (checkWin()){       // Проверяем являлся ли ход победным
                    PartyResult3 partyResult3 = new PartyResult3(ThreeToThree.this, "Компьютер победил!", ThreeToThree.this);       // Открываем диалоговое окно
                    partyResult3.setCancelable(false);       // Делаем, чтобы диалоговое окно не зкрывалось при нажатии за пределами
                    partyResult3.show();       // Показываем диалоговое окно
                    scoreComp++;       // Увеличиваем счётчик побед у компьютера
                    computerScore.setText(String.valueOf(scoreComp));       // Передаём значение побед в соответствующее поле
                }
            }
            turnWinner = 1;
        }
        if (turnWinner == 2 && scoreTurn != 9){
            action(imageView, 8, gm);
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

    private int randTurn(){       // Метод рандомного хода
        int tmp;
        List<int[]> randField = new ArrayList<>();       // Создаём динамический массив
        for (int i = 0; i < playField.length; i++) {
            if (playField[i] == 0){
                randField.add(new int[] {i});       // Добавляем в него координаты пустых ячеек
            }
        }
        tmp = (int) (Math.random()*randField.size());       // Рандомно определяем координаты хода
        int[] turn = randField.get(tmp);       // Создаём обычный массив и переносим в него значение координаты
        tmp = turn[0];       // Переносим координату в интовую переменную
        return tmp;       // Возвращаем координату рандомного хода
    }

    private int intellegentTurn(){       // Метод интеллектуального хода
        int tmp;
        tmp = checkWinTurn(2);       // Запускаем проверку победного хода компьютера
        if (tmp !=9) {
            return tmp;       // Если есть такой ход, возвращаем его координаты
        }
        tmp = checkWinTurn(1);       // Запускаем проверку победного хода игрока
        if (tmp !=9) {
            return tmp;       // Если есть такой ход, возвращаем его координаты
        }
        tmp = randTurn();       // Запускаем рандомный ход
        return tmp;       // Возвращаем его координаты
    }

    private int checkWinTurn(int playerOrComp){
        int tmp = 9;
        // Проверка победного хода
        for (int i = 0; i < 8; i++) {
                // Если две ячейки заняты одинаковыми (Х или О), а одна пустая, то вернуть координаты пустой
                if ((playField[fieldCheckWin[i][0]] == playField[fieldCheckWin[i][1]] && playField[fieldCheckWin[i][0]] == playerOrComp) && playField[fieldCheckWin[i][2]] == 0){
                    tmp = fieldCheckWin[i][2];
                    return tmp;
                }
                if ((playField[fieldCheckWin[i][0]] == playField[fieldCheckWin[i][2]] && playField[fieldCheckWin[i][0]] == playerOrComp) && playField[fieldCheckWin[i][1]] == 0){
                    tmp = fieldCheckWin[i][1];
                    return tmp;
                }
                if ((playField[fieldCheckWin[i][2]] == playField[fieldCheckWin[i][1]] && playField[fieldCheckWin[i][2]] == playerOrComp) && playField[fieldCheckWin[i][0]] == 0){
                    tmp = fieldCheckWin[i][0];
                    return tmp;
                }
        }
        return tmp;
    }

    public void newParty(){       // Метод подготовки нового матча
        playField = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};       //  -new int[]- Перезаписываем массив (обнуляем его)
        turnWinner = 1;       // Возвращаем ход игроку
        scoreTurn = 0;       // Сбрасываем счётчик ходов на 0

        image[0] = findViewById(R.id.image1);       // Присваеваем переменной -image1- ссылку для обращения к полю -image1-
        image[1] = findViewById(R.id.image2);
        image[2] = findViewById(R.id.image3);
        image[3] = findViewById(R.id.image4);
        image[4] = findViewById(R.id.image5);
        image[5] = findViewById(R.id.image6);
        image[6] = findViewById(R.id.image7);
        image[7] = findViewById(R.id.image8);
        image[8] = findViewById(R.id.image9);

        image[0].setImageResource(R.drawable.xoimg0);       // Заменяем анимации на прозрачные картинки
        image[1].setImageResource(R.drawable.xoimg0);
        image[2].setImageResource(R.drawable.xoimg0);
        image[3].setImageResource(R.drawable.xoimg0);
        image[4].setImageResource(R.drawable.xoimg0);
        image[5].setImageResource(R.drawable.xoimg0);
        image[6].setImageResource(R.drawable.xoimg0);
        image[7].setImageResource(R.drawable.xoimg0);
        image[8].setImageResource(R.drawable.xoimg0);

    }
}