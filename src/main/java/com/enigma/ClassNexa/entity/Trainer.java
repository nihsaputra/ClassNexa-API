package com.enigma.ClassNexa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "m_trainer")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String gender;

    @OneToMany(mappedBy = "trainer")
    private List<Classes> classes;

    @OneToOne
    @JoinColumn(name = "user_credential_id", referencedColumnName = "id")
    private UserCredential userCredential;


}
