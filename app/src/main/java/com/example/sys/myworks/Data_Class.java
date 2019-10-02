package com.example.sys.myworks;




import java.util.ArrayList;


public class Data_Class {

    public static ArrayList<Data_Class> arrayList=new ArrayList<>();
    String date;
    String time;
    String message;
    String alarmID;


    public Data_Class(String date, String time, String message, String alarmID) {
        this.date = date;
        this.time = time;
        this.message = message;
        this.alarmID = alarmID;

    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlarmID() {
        return alarmID;
    }



    public void setAlarmID(String alarmID) {
        this.alarmID = alarmID;
    }

    public static ArrayList<Data_Class> getArrayList() {
        return arrayList;
    }

    public static void addArrayList(Data_Class sample) {

        arrayList.add(sample);

    }
}
