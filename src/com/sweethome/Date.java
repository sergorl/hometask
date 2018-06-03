package com.sweethome;

import java.util.Calendar;
import java.text.SimpleDateFormat;

class Date {

    private Calendar date;
    final private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Date(String str) {

        String[] data = str.split("[\\.\\s\\:]");

        int day   = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]) - 1;
        int year  = Integer.parseInt(data[2]);

        int hour  = Integer.parseInt(data[3]);
        int min   = Integer.parseInt(data[4]);
        int sec   = Integer.parseInt(data[5]);

        date = Calendar.getInstance();
        date.set(year, month, day, hour, min, sec);
        date.set(Calendar.MILLISECOND, 0);
    }

    public Date(Date other) {
        date = (Calendar) other.date.clone();
    }

    public void add(int amount) {
        date.add(Calendar.SECOND, amount);
    }

    public String toString() { return format.format(date.getTime()); }

    public int compareTo(Date other) { return date.compareTo(other.date); }
}
