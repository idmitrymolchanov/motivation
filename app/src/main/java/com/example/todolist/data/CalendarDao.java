package com.example.todolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.model.MyCalendar;

import java.util.List;

@Dao
public interface CalendarDao {
    @Query("SELECT * FROM MyCalendar")
    List<MyCalendar> getAll();

    @Query("SELECT * FROM MyCalendar")
    LiveData<List<MyCalendar>> getAllLiveData();

    @Query("SELECT selected_date FROM MyCalendar LEFT JOIN Note ON MyCalendar.text=Note.text WHERE MyCalendar.text=:myCalendar")
    List<String> getAllDate(String myCalendar);

    @Query("SELECT * FROM MyCalendar WHERE u_id IN (:myCalendarIds)")
    List<MyCalendar> loadAllByIds(int[] myCalendarIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MyCalendar myCalendar);

    @Update
    void update(MyCalendar myCalendar);

    @Delete
    void delete(MyCalendar myCalendar);
}
