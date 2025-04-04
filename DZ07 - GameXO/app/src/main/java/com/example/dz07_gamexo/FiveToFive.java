package com.example.dz07_gamexo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FiveToFive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_to_five);

        ImageView img = findViewById(R.id.img_anim_x_one);
        Button btn = findViewById(R.id.button_one);
        ImageView img1 = findViewById(R.id.img_anim_o_two);
        Button btn1 = findViewById(R.id.button_two);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AnimationDrawable) img.getDrawable()).start();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AnimationDrawable) img1.getDrawable()).start();
            }
        });
    }
}