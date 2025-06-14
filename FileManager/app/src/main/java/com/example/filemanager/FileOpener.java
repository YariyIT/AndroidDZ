package com.example.filemanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

public class FileOpener {
    public static void openFile(Context context, File file){
        File selectedFile = file;

        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);

        Intent intent = new Intent(Intent.ACTION_VIEW);       // Создаём намеренье просмотра объекта
        if (uri.toString().contains(".doc")){       // Проверяем расширеие файла
            intent.setDataAndType(uri, "application/msword");       // Открываем файл соответствующим приложением
        } else if (uri.toString().contains(".pdf")){       // Проверяем расширеие файла
            intent.setDataAndType(uri, "application/pdf");       // Открываем файл соответствующим приложением
        } else if (uri.toString().contains(".mp3") || uri.toString().contains(".wav")){       // Проверяем расширеие файла
            intent.setDataAndType(uri, "audio/x-wav");       // Открываем файл соответствующим приложением
        } else if (uri.toString().contains(".jpeg") || uri.toString().contains(".jpg") || uri.toString().contains(".png")){       // Проверяем расширеие файла
            intent.setDataAndType(uri, "image/jpeg");       // Открываем файл соответствующим приложением
        } else if (uri.toString().contains(".mp4")){       // Проверяем расширеие файла
            intent.setDataAndType(uri, "video/*");       // Открываем файл соответствующим приложением
        } else {
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);
    }
}
