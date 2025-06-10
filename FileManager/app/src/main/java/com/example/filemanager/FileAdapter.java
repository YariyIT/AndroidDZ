package com.example.filemanager;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.Format;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileViewHolder> {

    private Context context;
    private List<File> file;
    private OnFileSelectedListener listener;

    public FileAdapter(Context context, List<File> files, OnFileSelectedListener listener) {
        this.context = context;
        this.file = files;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.tvName.setText(file.get(position).getName());       // Обращаемся к имени файла по позиции
        holder.tvName.setSelected(true);       // Применяем имя файла
        int items =0;
        if (file.get(position).isDirectory()){       // Если файл по позиции является -ПАПКОЙ-
            File[] files = file.get(position).listFiles();       // Создаём массив и передаём туда список файлов
            for (File singleFile : files){
                if (!singleFile.isHidden()){
                    items +=1;
                }
            }
            holder.tvSize.setText(items + " Files");
        } else {       // Если файл по позиции является -ФАЙЛОМ-
            holder.tvSize.setText(Formatter.formatShortFileSize(context, file.get(position).length()));       // Передаём размер файла
        }
        if (file.get(position).getName().toLowerCase().endsWith(".jpeg")){
            holder.imgFile.setImageResource(R.drawable.baseline_image_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".jpg")) {
            holder.imgFile.setImageResource(R.drawable.baseline_image_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".png")) {
            holder.imgFile.setImageResource(R.drawable.baseline_image_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".pdf")) {
            holder.imgFile.setImageResource(R.drawable.baseline_picture_as_pdf_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".doc")) {
            holder.imgFile.setImageResource(R.drawable.baseline_edit_document_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".mp3")) {
            holder.imgFile.setImageResource(R.drawable.baseline_library_music_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".wav")) {
            holder.imgFile.setImageResource(R.drawable.baseline_library_music_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".mp4")) {
            holder.imgFile.setImageResource(R.drawable.baseline_ondemand_video_24);
        } else if (file.get(position).getName().toLowerCase().endsWith(".apk")) {
            holder.imgFile.setImageResource(R.drawable.outline_apk_document_24);
        } else {
            holder.imgFile.setImageResource(R.drawable.outline_folder_24);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFileClicked(file.get(position));
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onFileLongClicked(file.get(position));
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return file.size();
    }
}
