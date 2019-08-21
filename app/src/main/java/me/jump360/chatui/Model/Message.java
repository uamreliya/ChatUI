package me.jump360.chatui.Model;

import me.jump360.chatui.Model.User;

public class Message {

    String message;
    User user;
    String time;

    public Message(String message, User user, String time) {
        this.message = message;
        this.user = user;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
