package com.example.proekt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Message {
    private String id_username, message, nameUser, userPhoto, moscowTime;

    public Message () {}

    public Message(String id_username, String nameUser, String message, String userPhoto) {
        this.message = message;
        this.id_username = id_username;
        this.nameUser = nameUser;
        this.userPhoto = userPhoto;
        TimeZone timeZone = TimeZone.getTimeZone("GMT+3");
        Calendar calendar = Calendar.getInstance(timeZone);
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.moscowTime = dataFormat.format(calendar.getTime());
    }

    public String getMoscowTime() {
        return moscowTime;
    }

    public String getId_username() {
        return id_username;
    }

    public void setId_username(String id_username) {
        this.id_username = id_username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String toString() {
        return nameUser + ":" + message;
    }
}
