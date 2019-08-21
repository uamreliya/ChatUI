package me.jump360.chatui.Model;

public class User {

    String name;
    String profileURL;

    public User(String name, String profileURL) {
        this.name = name;
        this.profileURL = profileURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}
