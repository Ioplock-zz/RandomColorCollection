package com.example.baseadapterban;

import android.graphics.Color;

import java.math.BigInteger;

public class ColorR {

    int color;
    String s_hex_color;
    String s_rgb_color;
    byte wb_color;
    boolean saved = false;

    public ColorR(String s_color) {
        this.color = Color.parseColor(s_color);
        this.s_hex_color = s_color;
        BigInteger[] colors = new BigInteger[]{new BigInteger(s_hex_color.substring(1,3),16), new BigInteger(s_hex_color.substring(3,5),16), new BigInteger(s_hex_color.substring(5,7),16)};
        this.s_rgb_color = (colors[0].toString(10) + ", " + colors[1].toString(10) + ", " + colors[2].toString(10));
        BigInteger[] colorsban = new BigInteger[]{new BigInteger(colors[0].toString(10),10), new BigInteger(colors[1].toString(10),10), new BigInteger(colors[2].toString(10),10)};
        this.wb_color = (byte) ((colorsban[0].intValue() + colorsban[1].intValue() + colorsban[2].intValue() ) / 3);
    }

    public void swithSave() {
        saved = !saved;
    }

}
