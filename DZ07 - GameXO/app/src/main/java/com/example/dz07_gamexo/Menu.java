package com.example.dz07_gamexo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RadioGroup gameMode = findViewById(R.id.game_mode);


        Button threeToThree = findViewById(R.id.three_to_three);
        Button fiveToFive = findViewById(R.id.five_to_five);

        threeToThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Menu.this, ThreeToThree.class);
               startActivity(intent);
            }
        });

        fiveToFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, FiveToFive.class);
                startActivity(intent);
            }
        });


    }
}