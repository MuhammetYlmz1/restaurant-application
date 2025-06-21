package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.dto.FileStorageDto;
import com.muhammet.restaurantapplication.model.request.UploadFileStorageRequest;
import com.muhammet.restaurantapplication.service.FileService;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileService fileService;

    @GetMapping(path = "/object/{objectName}")
    public ResponseEntity<Resource> getBucketsImages(@PathVariable String objectName, @RequestParam String bucketName) {
        var data = fileService.getFile(objectName,bucketName);
        return ResponseEntity.ok()
                .contentLength(data.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+objectName+"\"")
                .body(new ByteArrayResource(data));
    }

    @PostMapping(path = "/upload/{bucketName}", consumes = {"multipart/form-data"})
    public ResponseEntity<Resource> savePostPhoto(@RequestParam("objectName") String objectName,@PathVariable("bucketName")String bucketName, @RequestPart("file") MultipartFile file) throws IOException {
        var image = fileService.uploadFile(file,bucketName,objectName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(Objects.requireNonNull(image.getContentType())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getOriginalFilename() + "\"")
                .body(new ByteArrayResource(image.getBytes()));
    }

    @GetMapping(path = "/buckets")
    public List<Bucket> getAllBuckets() throws Exception {
        return fileService.getAllBuckets();
    }

    @GetMapping(path = "/create-bucket/{bucketName}")
    public String createBucket(@PathVariable String bucketName){
        return fileService.createBucket(bucketName);
    }

    @PostMapping()
    public FileStorageDto uploadImage(@RequestBody UploadFileStorageRequest request) {
        return fileService.saveImage(request);
    }

}
