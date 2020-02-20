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

    final String SHARED_PREFS = "sharedPrefs";
    final String TEXT = "text";


    static public ArrayList<ColorR> favorite = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ColorR> colors = new ArrayList<>();

        butt = findViewById(R.id.generate);
        nextA = findViewById(R.id.open_save_activity);
        ls = findViewById(R.id.list);

        //Init
        colors.clear();

        for (int i = 0; i < 100; i++) {
            int rand_c = r.nextInt(0xffffff + 1);
            colors.add(new ColorR(String.format("#%06x", rand_c)));
        }
        RandomColorAdapter my = new RandomColorAdapter(getApplicationContext(), colors);
        ls.setAdapter(my);


        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colors.clear();

                for (int i = 0; i < 100; i++) {
                    int rand_c = r.nextInt(0xffffff + 1);
                    colors.add(new ColorR(String.format("#%06x", rand_c)));
                }
                RandomColorAdapter my = new RandomColorAdapter(getApplicationContext(), colors);
                ls.setAdapter(my);
            }
        });
        nextA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(getApplicationContext(), Favorites.class);
                startActivity(menu);
            }
        });

    }
}
