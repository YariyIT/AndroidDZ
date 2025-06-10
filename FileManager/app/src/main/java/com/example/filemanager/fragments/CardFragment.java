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


public class CardFragment extends Fragment implements OnFileSelectedListener {

    private FileAdapter fileAdapter;
    private RecyclerView recyclerView;
    private List<File> fileList;
    private ImageView imgBack;
    private TextView tvPathHolder;
    File storageSd;       // Объявляем переменную для работы с файлами
    View view;
    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card, container, false);

        imgBack = view.findViewById(R.id.img_back);
        tvPathHolder = view.findViewById(R.id.tv_path_holder);


        // получаем доступ к внешней карте памяти
        File[] externalDirs = ContextCompat.getExternalFilesDirs(getContext(), null);
        // Метод getExternalFilesDirs() Получает массив путей, где приложение может хранить файлы:
        // externalDirs[0] — это внутренняя память (например: /storage/emulated/0/Android/data/твоё_приложение/files).
        // externalDirs[1] — это SD-карта, если она вставлена (например: /storage/XXXX-XXXX/Android/data/твоё_приложение/files)
        if (externalDirs.length > 1 && externalDirs[1] != null) {
            // Проверка: В массиве есть хотя бы 2 пути (length > 1) и Второй путь (SD-карта) реально существует (не null) если проще то строка читается так "Если в массиве есть второй путь, и он реально указывает на SD-карту — работаем с ним!"
            // Внешняя SD-карта доступна. Действие:
            storageSd = new File(externalDirs[1].getAbsolutePath().substring(0, 19));
            // Запоминаешь путь к папке на SD-карте
            tvPathHolder.setText("SD-карта: ");

            // вызов метода запроса разрешений
        } else {           // Если SD-карты нет — используем внутреннюю
            storageSd = new File(Environment.getExternalStorageDirectory().getAbsolutePath()); //Используем стандартный путь к внутренней памяти
            tvPathHolder.setText("Встроенная память: ");
            //Отображаем, что работаем с внутренней
        }
        if (getArguments() != null){
            data = getArguments().getString("path");
            storageSd = new File(data);
        }
        runtimePermission();       //

        // - - - -  Получаем доступ  к внутреннеей SD карте  - - - -
//        String internalStorage = System.getenv("REMOVABLE_STORAGE");
//        storageSd = new File(internalStorage);
//
//        tvPathHolder.setText(storageSd.getAbsolutePath());
//        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//        runtimePermission();
        return view;
    }

    private void runtimePermission() {
        // API 29 и ниже
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                displayFiles();
            }
        }
        // API 30 и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);       // Для окна с настройками разрешения
                    intent.setData(uri);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", getActivity().getPackageName())));
                    getActivity().startActivityIfNeeded(intent, 101);
                } catch (Exception e) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    getActivity().startActivityIfNeeded(intent, 101);
                }
            }
            if (Environment.isExternalStorageManager()) {
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


        // - - - -  Цыкл добавления определённых типов файлов
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
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fileList = new ArrayList<>();
        fileList.addAll(findFiles(storageSd));
        System.out.println(fileList);
        fileAdapter = new FileAdapter(getContext(), fileList, this);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onFileClicked(File file) {
        if(file.isDirectory()){
            Bundle bundle = new Bundle();       // Создаём переменную для передачи данных между окнами
            bundle.putString("path", file.getAbsolutePath());       // Передаём в неё значение (путь к файлу) по ключу -path-
            CardFragment cardFragment = new CardFragment();       // Создаём переменную окна
            cardFragment.setArguments(bundle);       // Передаём в неё данные из переменной -bundle-

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cardFragment).addToBackStack(null).commit();       // Загружаем в окно (контейнер) новое окно с новыми данными
        }
    }

    @Override
    public void onFileLongClicked(File file) {

    }

}