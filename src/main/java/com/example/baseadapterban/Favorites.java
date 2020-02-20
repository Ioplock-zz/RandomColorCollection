package com.example.baseadapterban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import static com.example.baseadapterban.MainActivity.favorite;

public class Favorites extends AppCompatActivity {

    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ls = findViewById(R.id.fav);
        ls.setAdapter(new RandomColorAdapter(getApplicationContext(), favorite));
    }
}
