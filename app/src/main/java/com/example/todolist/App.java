package com.example.todolist;

import android.app.Application;

import androidx.room.Room;

import com.example.todolist.data.AppDatabase;
import com.example.todolist.data.CalendarDao;
import com.example.todolist.data.NoteDao;

public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;
    private CalendarDao calendarDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    /*    database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db-name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();*/
        database = Room.databaseBuilder(this, AppDatabase.class, "app-db-name-2")
                .allowMainThreadQueries()
                .build();
   /*     database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();*/

        noteDao = database.noteDao();
        calendarDao = database.calendarDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public CalendarDao getCalendarDao() {
        return calendarDao;
    }

    public void setCalendarDao(CalendarDao calendarDao) {
        this.calendarDao = calendarDao;
    }
}
