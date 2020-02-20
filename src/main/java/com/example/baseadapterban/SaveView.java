package com.example.baseadapterban;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SaveView extends View {

    Drawable star, filled_star;
    int Width, Height;
    ColorR color;

    public SaveView(Context context) {
        super(context);
        star = getResources().getDrawable(R.drawable.star, null);
        filled_star = getResources().getDrawable(R.drawable.filled_star, null);
    }

    public SaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        star = getResources().getDrawable(R.drawable.star, null);
        filled_star = getResources().getDrawable(R.drawable.filled_star, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Width = w;
        Height = h;
    }

    public void setColor(ColorR color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(color != null && color.saved){
            filled_star.setBounds(0 , 0 , Width, Height);
            filled_star.draw(canvas);
        } else {
            star.setBounds(0 , 0 , Width, Height);
            star.draw(canvas);
        }
    }
}
