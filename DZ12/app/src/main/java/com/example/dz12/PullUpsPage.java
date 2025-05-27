package com.example.dz12;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


public class PullUpsPage extends Fragment {


    Spinner spinnerPullUps;       // Объявляем переменную для спинера

    TextView textViewPullUps;       // Объявляем переменную для текстового поля

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull_ups_page, container, false);       // Объявляем переменную -view- для возможности написания кода внутри

        spinnerPullUps = view.findViewById(R.id.spinner_pull_ups);       // Привязываем переменную -spinnerPullUps- к полю спинера -spinner_pull_ups-
        textViewPullUps = view.findViewById(R.id.text_view_pull_ups);       // Привязываем переменную -textViewPullUps- к текстовому полю -text_view_pull_ups-

        spinnerPullUps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {       // Создаём слушатель для спинера, который срабатывает при выборе
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = getTextPullUps(position);       // Создаём текстовую переменную, передавая в неё значение полученное через метод -getTextPullUps- по позиции -position-
                textViewPullUps.setText(text);       // Добавляем в текстовое поле полученное текстовое значение

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    private String getTextPullUps(int position){       // Метод получения значения по позиции
        String[] description = getResources().getStringArray(R.array.pull_ups_text);
        return description[position];
    }
}