package com.enigma.ClassNexa.controller;

import com.enigma.ClassNexa.model.request.UserUpdateRequest;
import com.enigma.ClassNexa.model.response.UserResponse;
import com.enigma.ClassNexa.model.response.WebResponse;
import com.enigma.ClassNexa.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/participant")
public class ParticipanController {

    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<UserResponse> getAll = participantService.getAll();

        WebResponse<List<UserResponse>> response = WebResponse.<List<UserResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get participant")
                .data(getAll)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        UserResponse getById = participantService.getById(id);

        WebResponse<UserResponse> response = WebResponse.<UserResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get participant")
                .data(getById)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserUpdateRequest request){
        UserResponse updateResponse = participantService.update(request);

        WebResponse<UserResponse> response = WebResponse.<UserResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly update participant")
                .data(updateResponse)
                .build();

        return ResponseEntity.ok(response);
    }

}
