package com.example.dz07_gamexo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class PartyResult3 extends Dialog {

    private final String message;       // Объявляем переменную для получения сообщения
    private final ThreeToThree threeToThree;       // Объявляем переменную поля 3 х 3

    public PartyResult3(@NonNull Context context, String message, ThreeToThree threeToThree) {       // Создаём конструктор метода
        super(context);
        this.message = message;
        this.threeToThree = threeToThree;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_result3);

        TextView text = findViewById(R.id.party_result);       // Создаём переменную для обращения к текстовому полю
        Button new_party_bnt = findViewById(R.id.new_party);       // Создаём переменную для обращения к кнопке запуска новой партии
        text.setText(message);

        new_party_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}