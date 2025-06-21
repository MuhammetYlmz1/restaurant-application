package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.delegate.minio.service.MinioDelegateService;
import com.muhammet.restaurantapplication.model.dto.FileStorageDto;
import com.muhammet.restaurantapplication.model.request.UploadFileStorageRequest;
import com.muhammet.restaurantapplication.model.response.UploadResponse;
import com.muhammet.restaurantapplication.model.entity.FileStorage;
import com.muhammet.restaurantapplication.repository.FileStorageRepository;
import com.muhammet.restaurantapplication.service.FileService;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;
    private final MinioDelegateService minioDelegateService;
    private final FileStorageRepository fileStorageRepository;
    private final ModelMapper modelMapper;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Override
    public List<Bucket> getAllBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    @Override
    public String createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            return bucketName + " bucked created.";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public byte[] getFile(String objectName, String bucketName) {
        try{
            InputStream file = minioClient.getObject(GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                    .build());
            return file.readAllBytes();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MultipartFile uploadFile(MultipartFile file, String bucketName, String objectName) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                    .build());
        }
        catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        }
        return file;
    }

    @Override
    public void deleteFile(String objectName, String bucketName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            throw new IllegalStateException("The file cannot be delete on the internal storage. Please retry later", e);
        }
    }

    @Override
    public FileStorageDto saveImage(UploadFileStorageRequest request) {
        byte[] imageByte = Base64.getDecoder().decode(request.getBase64Image());

        try {
            File tempFile = File.createTempFile(request.getFileName(), request.getSuffix());

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            outputStream.write(imageByte);

            String fileName = request.getFileName() + "." + request.getSuffix();
            String publicUrl;

            UploadResponse uploadResponse = minioDelegateService.uploadFile(tempFile, bucketName, fileName);

            publicUrl = uploadResponse.getFileUrl();

            FileStorage fileStorage = fileStorageRepository.save(FileStorage.builder().fileByte(imageByte).fileName(request.getFileName()).filePath(publicUrl).suffix(request.getSuffix()).build());
            FileStorageDto fileStorageDto = convertToFileStorageDto(fileStorage);

            tempFile.deleteOnExit();

            return fileStorageDto;
        } catch (IOException e) {
            log.error("Failed to save image errorMessage={} ", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private FileStorageDto convertToFileStorageDto(FileStorage fileStorage) {
        return FileStorageDto.builder()
                .id(fileStorage.getId())
                .imagePath(fileStorage.getFilePath())
                .suffix(fileStorage.getSuffix())
                .build();
    }
}
