package com.example.dz10;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button buttonSave;       // Объявляем переменную кнопки сохранения
    SharedPreferences preference;       // Объявляем переменную для сохранений

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_COST = "cost";
    private static final String KEY_SALE = "sale";
    private static final String KEY_TIP = "tip";

    private String currencyTip, saleIntText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = findViewById(R.id.calculate_button);       // Создаём переменную и присваеваем ей ссылку на кнопку расчёта чаевых
        Button saveButton = findViewById(R.id.save_button);       // Создаём переменную и присваеваем ей ссылку на кнопку сохранения результата
        EditText coastOfService = findViewById(R.id.coast_of_service);       // Создаём переменную и присваеваем ей ссылку на редактируемый текст
        TextView saleText = findViewById(R.id.sale);       // Создаём переменную и присваеваем ей ссылку на текст скидки
        TextView tipResult = findViewById(R.id.tip_result);       // Создаём переменную и присваеваем ей ссылку на текст чаевых

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keyword = coastOfService.getText().toString();       // Получаем сторку
                int cost = Integer.parseInt(keyword);       // Переводим её в число
                int sale = 0;       // Объявляем переменную скидки
                // Делаем проверку на скидку и приеменяем, если есть, применяем
                if (cost > 1000){
                    sale = (int) (cost * 0.05);
                    cost -= sale;
                } else if (cost > 500) {
                    sale = (int) (cost * 0.03);
                    cost -= sale;
                }

                double tip = 0;
                RadioGroup tipOptions = findViewById(R.id.tip_options);
                int selectedId = tipOptions.getCheckedRadioButtonId();       // Получаем Id элемента
                if (selectedId== R.id.options_ten_percent){
                    tip = cost * 0.1;
                }else if (selectedId == R.id.options_seven_percent){
                    tip = cost * 0.07;
                } else if (selectedId == R.id.options_five_percent) {
                    tip = cost * 0.05;
                }

                SwitchMaterial roundSwitch = findViewById(R.id.round_switch);
                boolean round = roundSwitch.isChecked();

                if (round){
                    tip = Math.ceil(tip);       // Округляем в большую сторону
                }


                Locale usLocale = new Locale("en", "US");       // Создаём локализацию (Американская)
                NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(usLocale);       // Создаём переменную и передаём в неё локализацию
                currencyTip = usCurrencyFormat.format(tip);       // Создаём строковую переменную для вывода результата (сумма чаевых) и передаём в неё локализированный вариант результата
                saleIntText = usCurrencyFormat.format(sale);       // Создаём строковую переменную для вывода результата (сумма скидки) и передаём в неё локализированный вариант результата

                String text = " Оставьте на чай: " + currencyTip;
                tipResult.setText(text);
                String textSale = " Ваша скидка: " + saleIntText;
                saleText.setText(textSale);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preference.edit();       // Создаём переменную для редактирования сохранённых данных
                editor.putString(KEY_COST, coastOfService.getText().toString());
                editor.putString(KEY_SALE, currencyTip);
                editor.putString(KEY_TIP, saleIntText);
                editor.apply();
                Toast.makeText(MainActivity.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
            }
        });
    }
}