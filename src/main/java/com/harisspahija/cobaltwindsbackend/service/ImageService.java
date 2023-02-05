package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.ImageDto;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.Image;
import com.harisspahija.cobaltwindsbackend.repository.ImageRepository;
import com.harisspahija.cobaltwindsbackend.util.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageData(ImageUtil.compressImage(file.getBytes()));

        imageRepository.save(image);

        return "Image uploaded successfully: " + file.getOriginalFilename();
    }

    @Transactional
    public ImageDto getInfoByImageByName(String name) {
        Image dbImage = imageRepository.findByName(name).orElseThrow(() -> new RepositoryNoRecordException(name));

        ImageDto image = new ImageDto();
        image.setName(dbImage.getName());
        image.setType(dbImage.getType());
        image.setImageData(ImageUtil.decompressImage(dbImage.getImageData()));

        return image;
    }

    @Transactional
    public byte[] getImage(String name) {
        Image dbImage = imageRepository.findByName(name).orElseThrow(() -> new RepositoryNoRecordException(name));
        return ImageUtil.decompressImage(dbImage.getImageData());
    }
}