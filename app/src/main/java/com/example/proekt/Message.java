package com.example.proekt;

public class Message {
    private String id_username, message, nameUser, userPhoto;

    public Message () {}

    public Message(String id_username, String nameUser, String message, String userPhoto) {
        this.message = message;
        this.id_username = id_username;
        this.nameUser = nameUser;
        this.userPhoto = userPhoto;
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
