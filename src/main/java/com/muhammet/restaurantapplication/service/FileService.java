package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.FileStorageDto;
import com.muhammet.restaurantapplication.model.requests.UploadFileStorageRequest;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<Bucket> getAllBuckets() throws Exception;

    String createBucket(String bucketName);

    byte[] getFile(String objectName,String bucketName);

    MultipartFile uploadFile(MultipartFile file, String bucketName, String objectName);

    void deleteFile(String objectName, String bucketName);

    FileStorageDto saveImage(UploadFileStorageRequest request);
}
