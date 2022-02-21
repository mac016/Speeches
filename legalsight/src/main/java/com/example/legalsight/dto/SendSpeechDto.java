package com.example.legalsight.dto;

public class SendSpeechDto {

    private Long speechId;
    private String email;

    public Long getSpeechId() {
        return speechId;
    }

    public void setSpeechId(Long speechId) {
        this.speechId = speechId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
