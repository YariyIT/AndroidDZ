package com.example.dz07_gamexo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PartyResult5 extends Dialog {

    private final String message;       // Объявляем переменную для получения сообщения
    private final FiveToFive fiveToFive;       // Объявляем переменную поля 5 х 5

    public PartyResult5(@NonNull Context context, String message, FiveToFive fiveToFive) {
        super(context);
        this.message = message;
        this.fiveToFive = fiveToFive;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_result5);

        TextView text = findViewById(R.id.party_result);       // Создаём переменную для обращения к текстовому полю
        Button new_party_bnt = findViewById(R.id.new_party);       // Создаём переменную для обращения к кнопке запуска новой партии
        text.setText(message);

        new_party_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiveToFive.newParty();       // Запускаем новый матч
                dismiss();       // Закрытие диалогового окна
            }
        });
    }
}

