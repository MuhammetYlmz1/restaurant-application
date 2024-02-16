package com.muhammet.restaurantapplication.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "minio")
public record MinioProperties(String url,
                              String accessKey,
                              String secretKey,
                              String imageBucket) {

}

