package com.example.dz12;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class PushUpsPage extends Fragment {

    EditText editText;       // Объявляем переменную для вводимого текста
    Button add;       // Объявляем переменную для кнопки добавления заметки
    RecyclerView recycleView;       // Объявляем переменную для поля заметок

    List<String> dataList = new ArrayList<>();       // Создаём текстовый лист
    DataAdapter adapter;       // Объявляем переменную для обращения к классу -DataAdapter-

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_push_ups_page, container, false);       // Объявляем переменную -view- для возможности написания кода внутри

        editText = view.findViewById(R.id.editText);       // Связываем переменную -editText- с полем -editText-
        add = view.findViewById(R.id.add);       // Связываем переменную -add- с полем -add-
        recycleView = view.findViewById(R.id.recycleView);       // Связываем переменную -recycleView- с полем -recycleView-

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new DataAdapter(dataList);
        recycleView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                dataList.add(data);
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }


}