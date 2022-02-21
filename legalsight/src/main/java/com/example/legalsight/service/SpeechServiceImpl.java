package com.example.legalsight.service;

import com.example.legalsight.dto.SearchCriteriaDto;
import com.example.legalsight.dto.SendSpeechDto;
import com.example.legalsight.enums.SearchOperation;
import com.example.legalsight.util.SpeechSpecification;
import com.example.legalsight.util.StringUtil;
import com.example.legalsight.dto.SpeechDto;
import com.example.legalsight.mapper.SpeechMapper;
import com.example.legalsight.model.Speech;
import com.example.legalsight.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeechServiceImpl implements SpeechService {

    @Autowired
    private SpeechRepository speechRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Speech save(SpeechDto speech) {
        return speechRepository.save(SpeechMapper.mapToSpeechEntity(speech));
    }

    @Override
    public Page<Speech> findAll(Pageable pageable) {
        return speechRepository.findAll(pageable);
    }

    @Override
    public Speech updateSpeech(Long id, SpeechDto dto) {
        return speechRepository.findById(id)
                .map(speech -> {
                    speech.setAuthor(dto.getAuthor());
                    speech.setSpeech(dto.getSpeech());
                    speech.setKeyword(dto.getKeyword());
                    speech.setSpeech_date(StringUtil.stringToDate(dto.getSpeech_date()));
                    return speechRepository.save(speech);
                }).orElseThrow(() -> new ResourceNotFoundException("Speech not found with id " + id));
    }

    @Override
    public ResponseEntity<?> deleteSpeech(Long speechId) {
        return speechRepository.findById(speechId)
                .map(speech -> {
                    speechRepository.delete(speech);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + speechId));
    }

    @Override
    public ResponseEntity<?> sendEmail(SendSpeechDto dto) {
        SimpleMailMessage msg = new SimpleMailMessage();

        return speechRepository.findById(dto.getSpeechId())
                .map(speech -> {
                    msg.setTo(dto.getEmail());
                    msg.setSubject(speech.getAuthor());
                    msg.setText(speech.getSpeech());
                    javaMailSender.send(msg);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + dto.getSpeechId()));
    }

    @Override
    public List<Speech> searchSpeech(SearchCriteriaDto dto) {
        if (dto.getKey().equalsIgnoreCase("speech_date")) {
            return speechRepository.findAllBySpeechDate(StringUtil.stringToDate(dto.getValue().toString()));
        }
        SpeechSpecification spec = new SpeechSpecification(new SearchCriteriaDto(dto.getKey(), dto.getOperation(), dto.getValue()));

        List<Speech> results = speechRepository.findAll(spec);

        return results;
    }
}
