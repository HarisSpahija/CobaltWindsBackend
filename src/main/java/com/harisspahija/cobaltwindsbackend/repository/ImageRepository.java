package com.harisspahija.cobaltwindsbackend.repository;

import com.harisspahija.cobaltwindsbackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
