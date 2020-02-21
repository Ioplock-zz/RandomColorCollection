package com.example.baseadapterban;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.example.baseadapterban.MainActivity.favorite;

public class RandomColorAdapter extends ArrayAdapter<ColorR> {
    public RandomColorAdapter(@NonNull Context context, ArrayList<ColorR> resource) { // Конструктор где указываем свою разметку
        super(context, R.layout.item_list , resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ColorR color = getItem(position); // Константа цвета полученного из массива

        if(convertView == null) { // Если convertView == null тогда сами создаём его из раметки
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, null);
        }

        ((TextView) convertView.findViewById(R.id.string_color)).setText(color.s_hex_color); // Устанавливаем HEX значение цвета в надпись
        convertView.findViewById(R.id.color).setBackgroundColor(color.color); // Ставим этот же цвет ImageView рядом
        ((SaveView)convertView.findViewById(R.id.save_color)).setColor(color); // Тут вызываем у SaveView метод setColor() который проверят сохранён ли он
        final View finalConvertView = convertView; // Копия convertView для того чтобы использовать его в OnClick
        convertView.findViewById(R.id.save_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color.swithSave();
                ((SaveView)finalConvertView.findViewById(R.id.save_color)).setColor(color);
                // Убираем если этот цвет есть в сохранённых или добавляем если его нет
                if(color.saved){
                    favorite.add(color);
                } else {
                    favorite.remove(color);
                }
            }
        });
        convertView.findViewById(R.id.copy_hex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Эта функция записывает HEX значение цвета в буфер обмена
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE); // Получаем сервис управления буфером обмена
                ClipData clip = ClipData.newPlainText("color", color.s_hex_color); // Создаём в нём новое значение
                clipboard.setPrimaryClip(clip); // И ставим его главным
            }
        });
        convertView.findViewById(R.id.copy_rgb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Эта функция записывает RGB значение цвета в буфер обмена
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE); // Получаем сервис управления буфером обмена
                ClipData clip = ClipData.newPlainText("color", color.s_rgb_color); // Создаём в нём новое значение
                clipboard.setPrimaryClip(clip); // И ставим его главным
            }
        });
        return convertView; // Возвращаем convertView для того чтобы закончить функцию
    }
}
