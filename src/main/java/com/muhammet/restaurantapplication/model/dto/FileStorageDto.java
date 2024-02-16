package com.muhammet.restaurantapplication.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorageDto {

    private Long id;
    private String imagePath;
    private String suffix;
}
