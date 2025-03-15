package com.example.dz04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView res;
    private EditText n1, n2;
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = findViewById(R.id.res);
        n1 = findViewById(R.id.editTextNumber3);
        n2 = findViewById(R.id.editTextNumber4);
        btn1 = findViewById(R.id.sum);
        btn2 = findViewById(R.id.dif);
        btn3 = findViewById(R.id.com);
        btn4 = findViewById(R.id.div);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer num1 =Integer.parseInt(n1.getText().toString());
                Integer num2 =Integer.parseInt(n2.getText().toString());
                res.setText(String.valueOf(num1 + num2));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer num1 =Integer.parseInt(n1.getText().toString());
                Integer num2 =Integer.parseInt(n2.getText().toString());
                res.setText(String.valueOf(num1 - num2));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer num1 =Integer.parseInt(n1.getText().toString());
                Integer num2 =Integer.parseInt(n2.getText().toString());
                res.setText(String.valueOf(num1 * num2));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double num1 =Double.parseDouble(n1.getText().toString());
                Double num2 =Double.parseDouble(n2.getText().toString());
                if(num2 == 0){res.setText("На 0 делить нельзя.");}
                else {res.setText(String.valueOf(num1 / num2));}

            }
        });
    }
}