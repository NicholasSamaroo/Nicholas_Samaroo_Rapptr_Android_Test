package com.datechnologies.androidtest.POJOs;

/**
 * A data model that represents a chat log message fetched from the D & A Technologies Web Server.
 */

public class ChatLogMessageModel {
    private int userId;
    private String name;
    private String avatar_url;
    private String message;

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getMessage() {
        return message;
    }
}
