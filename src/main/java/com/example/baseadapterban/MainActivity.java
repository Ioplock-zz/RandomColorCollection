package com.example.baseadapterban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button butt;
    ImageButton nextA;
    ListView ls;
    Random r = new Random();


    static public ArrayList<ColorR> favorite = new ArrayList<>(); // Список сохранённых цветов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация переменных View
        butt = findViewById(R.id.generate);
        nextA = findViewById(R.id.open_save_activity);
        ls = findViewById(R.id.list);

        // Первая генерация цветов
        RandomColorAdapter my = new RandomColorAdapter(getApplicationContext(), generateColors());
        ls.setAdapter(my);


        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Генерация по нажитию на кнопку
                RandomColorAdapter my = new RandomColorAdapter(getApplicationContext(), generateColors());
                ls.setAdapter(my);
            }
        });
        nextA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Кнопка перехода в меню сохранённых цветов
                Intent menu = new Intent(getApplicationContext(), Favorites.class);
                startActivity(menu);
            }
        });

    }

    public ArrayList<ColorR> generateColors() { // Функция для генерации массива из 100 рандомных цветов
        ArrayList<ColorR> colors = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int rand_c = r.nextInt(0xffffff + 1);
            colors.add(new ColorR(String.format("#%06x", rand_c)));
        }
        return colors;
    }
}
