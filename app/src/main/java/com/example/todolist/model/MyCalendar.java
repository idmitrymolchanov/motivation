package com.example.todolist.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "MyCalendar")
public class MyCalendar implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int u_id;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "selected_date")
    public String selected_date;

    @ColumnInfo(name = "color")
    public String color;

    public MyCalendar() {
    }

    protected MyCalendar(Parcel in) {
        u_id = in.readInt();
        text = in.readString();
        selected_date = in.readString();
        color = in.readString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCalendar that = (MyCalendar) o;
        return u_id == that.u_id &&
                Objects.equals(text, that.text) &&
                Objects.equals(selected_date, that.selected_date) &&
                Objects.equals(color, that.color);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(u_id, text, selected_date, color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(u_id);
        dest.writeString(text);
        dest.writeString(selected_date);
        dest.writeString(color);
    }

    public static final Creator<MyCalendar> CREATOR = new Creator<MyCalendar>() {
        @Override
        public MyCalendar createFromParcel(Parcel in) {
            return new MyCalendar(in);
        }

        @Override
        public MyCalendar[] newArray(int size) {
            return new MyCalendar[size];
        }
    };
}
