package com.enigma.ClassNexa.repository;

import com.enigma.ClassNexa.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepositoryRama extends JpaRepository<Classes, String> {

}
