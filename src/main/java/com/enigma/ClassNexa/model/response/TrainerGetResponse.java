package com.enigma.ClassNexa.model.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerGetResponse {
    private String id;
    private String name;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;
    private List<String> classes;
}
