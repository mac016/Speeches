package com.example.legalsight.controller;

import com.example.legalsight.dto.SearchCriteriaDto;
import com.example.legalsight.dto.SendSpeechDto;
import com.example.legalsight.dto.SpeechDto;
import com.example.legalsight.model.Speech;
import com.example.legalsight.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    @PostMapping("/add-speech")
    public Speech addSpeech(@RequestBody SpeechDto speech) {
        return speechService.save(speech);
    }

    @GetMapping("/all-speech")
    public Page<Speech> getSpeeches(Pageable pageable) {
        return speechService.findAll(pageable);
    }

    @PutMapping("/update-speech/{speechId}")
    public Speech updateQuestion(@PathVariable Long speechId, @RequestBody SpeechDto dto) {
        return speechService.updateSpeech(speechId, dto);
    }

    @DeleteMapping("/delete-speech/{speechId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long speechId) {
        return speechService.deleteSpeech(speechId);
    }

    @PostMapping("/send-speech")
    public ResponseEntity<?> sendSpeech(@RequestBody SendSpeechDto dto) {
        return speechService.sendEmail(dto);
    }

    @PostMapping("/search-speech")
    public List<Speech> searchSpeech(@RequestBody SearchCriteriaDto dto) {
        return speechService.searchSpeech(dto);
    }
}
