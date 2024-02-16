package com.muhammet.restaurantapplication.model.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadResponse {

    private String fileUrl;
}
