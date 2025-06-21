package com.muhammet.restaurantapplication.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileStorageRequest {

    private String base64Image;
    private String suffix;
    private String fileName;
}
