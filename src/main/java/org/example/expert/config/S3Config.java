package org.example.expert.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Config {

    private final String accessKey;
    private final String secretKey;

    public S3Config(
            @Value("${s3.access-key}") String accessKey,
            @Value("${s3.secret-key}") String secretKey) {
        if(accessKey == null || secretKey == null){
            throw new IllegalStateException("S3 access key or secret key not set!");
        }
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAccessKey() { return accessKey; }
    public String getSecretKey() { return secretKey; }
}