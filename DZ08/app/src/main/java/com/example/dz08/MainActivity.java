package com.example.dz08;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView sampleTextView = findViewById(R.id.sampleTextView);       // Создаём переменную для обращения к текстовому полю
        CheckBox boldCheckBox = findViewById(R.id.boldCheckBox);       // Создаём переменную для обращения к первому чекбоксу
        CheckBox italicCheckBox = findViewById(R.id.italicCheckBox);       // Создаём переменную для обращения ко второму чекбоксу

        boldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {       // Прослушиваем первый чекбокс
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (italicCheckBox.isChecked()){
                        sampleTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                    }else {
                        sampleTextView.setTypeface(null, Typeface.BOLD);
                    }
                }else {
                    if (italicCheckBox.isChecked()){
                        sampleTextView.setTypeface(null, Typeface.ITALIC);
                    }else {
                        sampleTextView.setTypeface(null, Typeface.NORMAL);
                    }
                }

            }
        });

        italicCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {       // Прослушиваем второй чекбокс
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (boldCheckBox.isChecked()){
                        sampleTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                    }else {
                        sampleTextView.setTypeface(null, Typeface.BOLD);
                    }
                }else {
                    if (boldCheckBox.isChecked()){
                        sampleTextView.setTypeface(null, Typeface.BOLD);
                    }else {
                        sampleTextView.setTypeface(null, Typeface.NORMAL);
                    }
                }
            }
        });
    }
}