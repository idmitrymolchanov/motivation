package com.example.todolist.screens.secondary;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.example.todolist.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class MyDecorator implements DayViewDecorator {

    private final int color;
    private final HashSet<CalendarDay> dates;
    private ColorDrawable drawable;
    private Context context;

    public MyDecorator(Context context, int color, Collection<CalendarDay> dates) {
        this.context = context;
        this.color = color;
        this.dates = new HashSet<>(dates);
        drawable = new ColorDrawable(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)));
        view.setBackgroundDrawable(drawable);//So this code add a background
    }
}
