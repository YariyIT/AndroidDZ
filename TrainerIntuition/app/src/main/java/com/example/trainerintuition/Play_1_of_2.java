package com.example.trainerintuition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Play_1_of_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1_of2);

        LinearLayout pictureCards = findViewById(R.id.picture_cards);
        pictureCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play_1_of_2.this, PictureCards.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });

        Button bntMenuBack = findViewById(R.id.bnt_dif_back);
        bntMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play_1_of_2.this, ChoicePlay.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_alfa_up, R.anim.anim_alfa_down);       // Мягкий переход
            }
        });
    }
}