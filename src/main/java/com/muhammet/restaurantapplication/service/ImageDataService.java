package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.response.ImageUploadResponse;
import com.muhammet.restaurantapplication.model.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {
    ImageUploadResponse uploadImage(MultipartFile file) throws IOException;
    ImageData getInfoByImageByName(String name);
     byte[] getImage(String name);
}
