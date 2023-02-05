package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.dto.ImageDto;
import com.harisspahija.cobaltwindsbackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String response = imageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<Object>  getImageInfoByName(@PathVariable("name") String name){
        ImageDto image = imageService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name){
        byte[] image = imageService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}