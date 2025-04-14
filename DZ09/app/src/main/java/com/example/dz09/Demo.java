package com.example.dz09;

import static com.example.dz09.R.*;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Demo extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        SwitchMaterial switch_day_night = findViewById(R.id.switch_day_night);
        ImageView night = findViewById(R.id.night);
        ImageView day = findViewById(R.id.day);
        ImageView imageComet = findViewById(R.id.imageComet);
        ImageView imageUfo = findViewById(R.id.imageUfo);
        CheckBox checkBoxComet = findViewById(R.id.checkBoxComet);
        CheckBox checkBoxUfo = findViewById(R.id.checkBoxUfo);
        ImageButton imageButtonStar = findViewById(R.id.imageButtonStar);
        ImageButton imageButtonStar2 = findViewById(R.id.imageButtonStar2);


        switch_day_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    night.setVisibility(View.INVISIBLE);
                    day.setVisibility(View.VISIBLE);
                    switch_day_night.setText(R.string.day);

                } else {
                    night.setVisibility(View.VISIBLE);
                    day.setVisibility(View.INVISIBLE);
                    switch_day_night.setText(R.string.night);

                }
            }
        });

        checkBoxComet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageComet.setVisibility(View.VISIBLE);
                } else {
                    imageComet.setVisibility(View.INVISIBLE);
                }

            }
        });

        checkBoxUfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageUfo.setVisibility(View.VISIBLE);
                } else {
                    imageUfo.setVisibility(View.INVISIBLE);
                }
            }
        });

        linearLayout = findViewById(id.scrollLinearLayout);

        imageButtonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStar(0);
            }
        });

        imageButtonStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStar(1);
            }
        });
    }

    private void addStar(int n){
        LayoutInflater inflater = LayoutInflater.from(this);
        if (n==0){
            ConstraintLayout newStar = (ConstraintLayout) inflater.inflate(R.layout.star, linearLayout, false);
            linearLayout.addView(newStar);
        }else {
            ConstraintLayout newStar = (ConstraintLayout) inflater.inflate(R.layout.star_2, linearLayout, false);
            linearLayout.addView(newStar);
        }

    }
}