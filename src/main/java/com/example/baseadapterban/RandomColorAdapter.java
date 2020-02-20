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
    public RandomColorAdapter(@NonNull Context context, ArrayList<ColorR> resource) {
        super(context, R.layout.item_list , resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ColorR color = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, null);
        }

        ((TextView) convertView.findViewById(R.id.string_color)).setText(color.s_hex_color);
        convertView.findViewById(R.id.color).setBackgroundColor(color.color);
        ((SaveView)convertView.findViewById(R.id.save_color)).setColor(color);
        final View finalConvertView = convertView;
        convertView.findViewById(R.id.save_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color.swithSave();
                ((SaveView)finalConvertView.findViewById(R.id.save_color)).setColor(color);
                finalConvertView.findViewById(R.id.save_color).invalidate();
                if(color.saved){
                    favorite.add(color);
                } else {
                    favorite.remove(color);
                }
            }
        });
        convertView.findViewById(R.id.copy_hex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("color", color.s_hex_color);
                clipboard.setPrimaryClip(clip);
            }
        });
        convertView.findViewById(R.id.copy_rgb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("color", color.s_rgb_color);
                clipboard.setPrimaryClip(clip);
            }
        });
        return convertView;
    }
}
