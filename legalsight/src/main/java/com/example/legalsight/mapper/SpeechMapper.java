package com.example.legalsight.mapper;

import com.example.legalsight.dto.SpeechDto;
import com.example.legalsight.model.Speech;
import com.example.legalsight.util.StringUtil;

public class SpeechMapper {

    public static Speech mapToSpeechEntity(SpeechDto dto) {
        Speech speech = new Speech();
        if (dto != null) {
            speech.setId(dto.getId());
            speech.setAuthor(dto.getAuthor());
            speech.setSpeech(dto.getSpeech());
            speech.setKeyword(dto.getKeyword());
            speech.setSpeech_date(StringUtil.stringToDate(dto.getSpeech_date()));
        }
        return speech;
    }

}
