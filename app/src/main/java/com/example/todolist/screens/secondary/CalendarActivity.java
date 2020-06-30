package com.example.todolist.screens.secondary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.App;
import com.example.todolist.R;
import com.example.todolist.model.MyCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.HashSet;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    MyCalendar myCalendar;
    private static String EXTRA_CALENDAR;
    MaterialCalendarView calendarView;

    public static void start(Activity caller, String textValue) {
        Intent intent = new Intent(caller, CalendarActivity.class);
        caller.startActivity(intent);
        EXTRA_CALENDAR = textValue;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView2);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(CalendarActivity.this, " " + date.getDate(), Toast.LENGTH_SHORT).show();
            }
        });

        myCalendar = new MyCalendar();
        selectDatesFromDB();
    }

    public void selectDatesFromDB() {
        try {
            List<String> listS = App.getInstance().getCalendarDao().getAllDate(EXTRA_CALENDAR);
            HashSet<CalendarDay> hashDates = new HashSet<>();
            for (int i = 0; i < listS.size(); i++) {
                String date = listS.get(i).substring(12, listS.get(i).length()-1);
                String[] strings = date.split("-");

                int year = Integer.parseInt(strings[0]);
                int month = Integer.parseInt(strings[1]);
                int day = Integer.parseInt(strings[2]);

                hashDates.add(CalendarDay.from(year, month, day));
            }

            int myColor = R.color.red;
            calendarView.addDecorators(new MyDecorator(CalendarActivity.this, myColor, hashDates));
        } catch (Exception e) {}
    }

    public void addSomeText(View view) {
        changeButton();
    }

    public void removeSomeText(View view) {
        changeButton2();
    }

    private void changeButton() {
        ImageButton button = findViewById(R.id.imageButton_add);
        button.setBackgroundColor(Color.argb(50, 200, 250, 100));
        HashSet<CalendarDay> hashDates = new HashSet<>();
        if(calendarView.getSelectedDate() != null) {
            myCalendar.text = EXTRA_CALENDAR;
            myCalendar.selected_date = calendarView.getSelectedDate().toString();
            myCalendar.color = "red";
            App.getInstance().getCalendarDao().insert(myCalendar);
            hashDates.add(calendarView.getSelectedDate());
            calendarView.addDecorators(new MyDecorator(CalendarActivity.this, R.color.red, hashDates));
        }
    }

    private void changeButton2() {
        ImageButton button = findViewById(R.id.imageButton_remove);
        button.setBackgroundColor(Color.argb(50, 200, 250, 100));
        if(calendarView.getSelectedDate() != null) {
            myCalendar.text = EXTRA_CALENDAR;
            myCalendar.selected_date = calendarView.getSelectedDate().toString();
            myCalendar.color = "red";
            App.getInstance().getCalendarDao().delete(myCalendar);
        }
    }
}
