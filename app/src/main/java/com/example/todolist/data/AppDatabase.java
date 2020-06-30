package com.example.todolist.data;

import android.os.Build;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist.model.MyCalendar;
import com.example.todolist.model.Note;

@Database(entities = {
        Note.class,
        MyCalendar.class
    }, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    public abstract CalendarDao calendarDao();
}
