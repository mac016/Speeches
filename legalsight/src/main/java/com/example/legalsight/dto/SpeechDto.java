package com.example.legalsight.dto;

public class SpeechDto {

    private Long id;
    private String author;
    private String speech;
    private String keyword;
    private String speech_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSpeech_date() {
        return speech_date;
    }

    public void setSpeech_date(String speech_date) {
        this.speech_date = speech_date;
    }
}
