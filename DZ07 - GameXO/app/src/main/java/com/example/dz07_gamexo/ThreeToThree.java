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

import java.util.Objects;

public class ThreeToThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_to_three);
        // Первое поле
        ImageView img_anim_x_one = findViewById(R.id.img_anim_x_one); // Создаём переменную для обращения к аниации -X- в первом поле
        ImageView img_anim_o_one = findViewById(R.id.img_anim_o_one); // Создаём переменную для обращения к аниации -O- в первом поле
        Button button_one = findViewById(R.id.button_one); // Создаём переменную для обращения к кнопке в первом поле
        // Второе поле
        ImageView img_anim_x_two = findViewById(R.id.img_anim_x_two); // Создаём переменную для обращения к аниации -X- во втором поле
        ImageView img_anim_o_two = findViewById(R.id.img_anim_o_two); // Создаём переменную для обращения к аниации -O- во втором поле
        Button button_two = findViewById(R.id.button_two); // Создаём переменную для обращения к кнопке во втором поле
        // Третье поле
        ImageView img_anim_x_three = findViewById(R.id.img_anim_x_three); // Создаём переменную для обращения к аниации -X- в третьем поле
        ImageView img_anim_o_three = findViewById(R.id.img_anim_o_three); // Создаём переменную для обращения к аниации -O- в третьем поле
        Button button_three = findViewById(R.id.button_three); // Создаём переменную для обращения к кнопке в третьем поле
        // Третье поле
        ImageView img_anim_x_four = findViewById(R.id.img_anim_x_four); // Создаём переменную для обращения к аниации -X- в четвёртом поле
        ImageView img_anim_o_four = findViewById(R.id.img_anim_o_four); // Создаём переменную для обращения к аниации -O- в четвёртом поле
        Button button_four = findViewById(R.id.button_four); // Создаём переменную для обращения к кнопке в четвёртом поле
        // Третье поле
        ImageView img_anim_x_five = findViewById(R.id.img_anim_x_five); // Создаём переменную для обращения к аниации -X- в пятом поле
        ImageView img_anim_o_five = findViewById(R.id.img_anim_o_five); // Создаём переменную для обращения к аниации -O- в пятом поле
        Button button_five = findViewById(R.id.button_five); // Создаём переменную для обращения к кнопке в пятом поле
        // Третье поле
        ImageView img_anim_x_six = findViewById(R.id.img_anim_x_six); // Создаём переменную для обращения к аниации -X- в шестом поле
        ImageView img_anim_o_six = findViewById(R.id.img_anim_o_six); // Создаём переменную для обращения к аниации -O- в шестом поле
        Button button_six = findViewById(R.id.button_six); // Создаём переменную для обращения к кнопке в шестом поле
        // Третье поле
        ImageView img_anim_x_seven = findViewById(R.id.img_anim_x_seven); // Создаём переменную для обращения к аниации -X- в седьмом поле
        ImageView img_anim_o_seven = findViewById(R.id.img_anim_o_seven); // Создаём переменную для обращения к аниации -O- в седьмом поле
        Button button_seven = findViewById(R.id.button_seven); // Создаём переменную для обращения к кнопке в седьмом поле
        // Третье поле
        ImageView img_anim_x_eight = findViewById(R.id.img_anim_x_eight); // Создаём переменную для обращения к аниации -X- в восьмом поле
        ImageView img_anim_o_eight = findViewById(R.id.img_anim_o_eight); // Создаём переменную для обращения к аниации -O- в восьмом поле
        Button button_eight = findViewById(R.id.button_eight); // Создаём переменную для обращения к кнопке в восьмом поле
        // Третье поле
        ImageView img_anim_x_nine = findViewById(R.id.img_anim_x_nine); // Создаём переменную для обращения к аниации -X- в девятом поле
        ImageView img_anim_o_nine = findViewById(R.id.img_anim_o_nine); // Создаём переменную для обращения к аниации -O- в девятом поле
        Button button_nine = findViewById(R.id.button_nine); // Создаём переменную для обращения к кнопке в девятом поле


        TextView playerScore = findViewById(R.id.pl); // Создаём переменную для обращения к текстовому полю -pl- счёт игрока
        TextView computerScore = findViewById(R.id.com); // Создаём переменную для обращения к текстовому полю -com- счёт компьютера
        TextView textTurnWinner = findViewById(R.id.textTurnWiner); // Создаём переменную для обращения к текстовому полю -textTurnWiner-
        Intent intGameMode = getIntent(); // Получаем данные, переданные из другого aktivity
        String gameMode = intGameMode.getStringExtra("game_mode"); // Создаём переменную, и записываем в неё данные -game_mode-, какой режим игры выбран
        int turnWinenr = 1; // Создаём переменную определение хода и победителя: 1 - ходит/победил игрок; 2 - ходит/победил компьютер;
        int scoreTurn = 0;  // Создаём переменную счёта ходов
        boolean motion = true; // Создаём переменную продолжения/завершения игры



        if (Objects.equals(gameMode, "1")){ // Проверка режима игры

        }else{

        }




    }
}