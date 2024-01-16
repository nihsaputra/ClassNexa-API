package com.enigma.ClassNexa.service.impl;

import com.enigma.ClassNexa.entity.Documentation;
import com.enigma.ClassNexa.entity.Schedule;
import com.enigma.ClassNexa.entity.Trainer;
import com.enigma.ClassNexa.model.DocumentationClaim;
import com.enigma.ClassNexa.model.request.SearchDocumentationRequest;
import com.enigma.ClassNexa.model.response.DocumentationResponse;
import com.enigma.ClassNexa.repository.DocumetationRepository;
import com.enigma.ClassNexa.service.DocumetationService;
import com.enigma.ClassNexa.service.ScheduleService;
import com.enigma.ClassNexa.service.TrainerService;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumetationServiceImpl implements DocumetationService {
    private final TrainerService trainerService;
    private final ScheduleService scheduleService;
    private final DocumetationRepository documetationRepository;

    private DocumentationResponse toDocumentationResponse(Documentation documentation){
        return DocumentationResponse.builder()
                .id(documentation.getId())
                .filename(documentation.getFileName())
                .triner_id(documentation.getTrainer().getId())
                .trainer(documentation.getTrainer().getName())
                .schedule_id(documentation.getSchedule().getId())
                .date(String.valueOf(documentation.getSchedule().getStart_class()))
                .build();
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocumentationResponse create(MultipartFile multipartFile, SearchDocumentationRequest request) throws IOException {
        Trainer byId = trainerService.getByTrainerId(request.getTrainer());
        Schedule byIdSchedule = scheduleService.getByIdSchedule(request.getSchedule());

        UUID uuid = UUID.nameUUIDFromBytes(multipartFile.getOriginalFilename().getBytes());

        Documentation documentation = Documentation.builder()
                .fileName(String.valueOf(uuid)+".png")
                .trainer(byId)
                .schedule(byIdSchedule)
                .imageData(multipartFile.getBytes())
                .build();
        Documentation save = documetationRepository.save(documentation);

        return toDocumentationResponse(save);
    }

    @Override
    public byte[] download(String filename) throws IOException {
        return documetationRepository.findByFileName(filename).getImageData();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Documentation> getAll() {
        List<Documentation> all = documetationRepository.findAll();
        return all;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocumentationResponse getById(String id) {
        Optional<Documentation> byId = documetationRepository.findById(id);
        Documentation documentation = Documentation.builder()
                .id(id)
                .fileName(byId.get().getFileName())
                .trainer(byId.get().getTrainer())
                .schedule(byId.get().getSchedule())
                .build();
        return toDocumentationResponse(documentation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocumentationResponse update(MultipartFile multipartFile, SearchDocumentationRequest documentation) throws IOException {
        Trainer byId = trainerService.getByTrainerId(documentation.getTrainer());
        Schedule byIdSchedule = scheduleService.getByIdSchedule(documentation.getSchedule());

        UUID uuid = UUID.nameUUIDFromBytes(multipartFile.getOriginalFilename().getBytes());

        Documentation documentation1 = Documentation.builder()
                .id(documentation.getId())
                .fileName(String.valueOf(uuid)+".png")
                .trainer(byId)
                .schedule(byIdSchedule)
                .imageData(multipartFile.getBytes())
                .build();
        Documentation save = documetationRepository.save(documentation1);

        return toDocumentationResponse(save);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(String id) {
        documetationRepository.deleteById(id);
        return "ok";
    }
}
