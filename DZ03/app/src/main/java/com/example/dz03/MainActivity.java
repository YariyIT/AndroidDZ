package com.example.dz03;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView firstT;
    private TextView secondT;
    private Button fB;
    private Button sB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dz03);

        firstT = findViewById(R.id.textView);
        secondT = findViewById(R.id.textView2);
        fB = findViewById(R.id.button);
        sB = findViewById(R.id.button2);

        fB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstT.setText("Первая кнопка была нажата");
                firstT.setTextSize(20);
            }
        });

        sB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondT.setText("Вторая кнопка была нажата");
                secondT.setTextSize(30);
            }
        });

    }
}