package com.muhammet.restaurantapplication.delegate.minio.service;

import com.muhammet.restaurantapplication.model.response.UploadResponse;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioDelegateService {

    private final MinioClient minioClient;

    private static final String SLASH_CHARACTER = "/";

    @Value("${minio.url}")
    private String minioUrl;

    public UploadResponse uploadFile(File file, String bucketName, String fileName) {
        try {

            UploadObjectArgs.Builder builder = UploadObjectArgs.builder();
            builder.bucket(bucketName);
            builder.object(fileName);
            builder.filename(file.getPath());

            UploadObjectArgs uploadObjectArgs = builder.build();

            minioClient.uploadObject(uploadObjectArgs);
        }
        catch (Exception e) {
            log.error("errorMessage={}, fileName={}",e.getMessage());
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        }
        return generateUrl(bucketName, fileName);
    }

    private UploadResponse generateUrl(String bucketName, String fileName) {

        String url = minioUrl + SLASH_CHARACTER + bucketName + SLASH_CHARACTER + fileName;
        return new UploadResponse(url);
    }
}
