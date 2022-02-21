package com.example.legalsight.service;

import com.example.legalsight.dto.SearchCriteriaDto;
import com.example.legalsight.dto.SendSpeechDto;
import com.example.legalsight.dto.SpeechDto;
import com.example.legalsight.model.Speech;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpeechService {

    Speech save(SpeechDto speech);

    Page<Speech> findAll(Pageable pageable);

    Speech updateSpeech(Long id, SpeechDto dto);

    ResponseEntity<?> deleteSpeech(Long speechId);

    ResponseEntity<?> sendEmail(SendSpeechDto dto);

    List<Speech> searchSpeech(SearchCriteriaDto dto);

}
