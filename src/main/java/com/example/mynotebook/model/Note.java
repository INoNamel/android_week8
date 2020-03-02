package com.example.mynotebook.model;

public class Note {
    private String headline;
    private String body;
    private int id;


    public Note(int id, String headline, String body) {
        this.id = id;
        this.headline = headline;
        this.body = body;
    }

    public Note(String headline, String body) {
        this.headline = headline;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
