package com.example.lists;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Lecture {

    private static final int LECTURES_PER_WEEK = 3;

    private final String date;
    private final String theme;
    private final String lector;
    private final int number;
    private final int weekIndex;

    public Lecture(int number, String data, String theme, String lector) {
        this.date = data;
        this.theme = theme;
        this.lector = lector;
        this.number = number;
        weekIndex = (this.number-1)/LECTURES_PER_WEEK;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Lecture lecture = (Lecture) obj;
        return lecture.getData().equals(this.date) &&
                lecture.getLector().equals(this.lector) &&
                lecture.getNumber() == this.number &&
                lecture.getTheme().equals(this.theme) &&
                lecture.getWeekIndex() == this.weekIndex;
    }

    @NonNull
    public String getData() {
        return date;
    }

    @NonNull
    public String getTheme() {
        return theme;
    }

    @NonNull
    public String getLector() {
        return lector;
    }

    @NonNull
    public int getNumber() {
        return number;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

}
