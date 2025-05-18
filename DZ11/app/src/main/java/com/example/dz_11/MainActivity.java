package com.example.dz_11;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    TextView f1, f2, f3;

    Fragment1 firstF = new Fragment1();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f1 = findViewById(R.id.fragment_one);
        f2 = findViewById(R.id.fragment_tow);
        f3 = findViewById(R.id.fragment_three);
        repFr(firstF);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repFr(firstF);
            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 secondFr = new Fragment2();
                repFr(secondFr);
            }
        });

        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment3 thirdFr = new Fragment3();
                repFr(thirdFr);
            }
        });
    }

    private void repFr(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }
}