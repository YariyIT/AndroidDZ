package com.example.filemanager.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.PatternMatcher;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filemanager.FileAdapter;
import com.example.filemanager.OnFileSelectedListener;
import com.example.filemanager.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class InternalFragment extends Fragment implements OnFileSelectedListener {

    private FileAdapter fileAdapter;
    private RecyclerView recyclerView;
    private List<File> fileList;
    private ImageView imgBack;
    private TextView tvPathHolder;
    File storage;       // Объявляем переменную для работы с файлами
    View view;
    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_internal, container, false);

        imgBack = view.findViewById(R.id.img_back);
        tvPathHolder = view.findViewById(R.id.tv_path_holder);

        // - - - -  Получаем доступ  к внутреннеей SD карте  - - - -
        String internalStorage = System.getenv("EXTERNAL_STORAGE");
        storage = new File(internalStorage);

        if (getArguments() != null){
            data = getArguments().getString("path");
            storage = new File(data);
        }
        tvPathHolder.setText(storage.getAbsolutePath());
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        runtimePermission();

        return view;
    }

    private void runtimePermission() {
        // API 29 и ниже
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                displayFiles();
            }
        }
        // API 30 и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if (!Environment.isExternalStorageManager()){
                try {
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);       // Для окна с настройками разрешения
                    intent.setData(uri);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", getActivity().getPackageName())));
                    getActivity().startActivityIfNeeded(intent, 101);
                } catch (Exception e){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    getActivity().startActivityIfNeeded(intent, 101);
                }
            }
            if (Environment.isExternalStorageManager()){
                displayFiles();
            }
        }
    }

    public ArrayList<File> findFiles(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singleFile : files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.add(singleFile);
            }
        }

        for (File singleFile : files){
            if (singleFile.getName().toLowerCase().endsWith(".jpeg") ||
                singleFile.getName().toLowerCase().endsWith(".jpg") ||
                singleFile.getName().toLowerCase().endsWith(".png") ||
                singleFile.getName().toLowerCase().endsWith(".mp3") ||
                singleFile.getName().toLowerCase().endsWith(".wav") ||
                singleFile.getName().toLowerCase().endsWith(".mp4") ||
                singleFile.getName().toLowerCase().endsWith(".pdf") ||
                singleFile.getName().toLowerCase().endsWith(".doc") ||
                singleFile.getName().toLowerCase().endsWith(".apk")){
                arrayList.add(singleFile);
            }
        }
        return arrayList;
    }
    private void displayFiles() {
        recyclerView = view.findViewById(R.id.recycler_internal);
        recyclerView.setHasFixedSize(true);       // если -mathc_parent-
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));       // -2- - количество столбцов
        fileList = new ArrayList<>();
        fileList.addAll(findFiles(storage));
        System.out.println("!!!!!!!!!!!" + fileList);
        fileAdapter = new FileAdapter(getContext(), fileList, this);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onFileClicked(File file) {
        if(file.isDirectory()){
            Bundle bundle = new Bundle();       // Создаём переменную для передачи данных между окнами
            bundle.putString("path", file.getAbsolutePath());       // Передаём в неё значение (путь к файлу) по ключу -path-
            InternalFragment internalFragment = new InternalFragment();       // Создаём переменную окна
            internalFragment.setArguments(bundle);       // Передаём в неё данные из переменной -bundle-

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, internalFragment).addToBackStack(null).commit();       // Загружаем в окно (контейнер) новое окно с новыми данными
        }
    }

    @Override
    public void onFileLongClicked(File file) {

    }
}