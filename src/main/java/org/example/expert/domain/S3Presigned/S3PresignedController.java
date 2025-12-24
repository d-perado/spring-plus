package org.example.expert.domain.S3Presigned;

import org.example.expert.config.S3Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@RestController
public class S3PresignedController {

    private final String bucketName = "dperado-spring-plus-bucket";

    private final S3Presigner presigner;

    public S3PresignedController(S3Config s3Config) {
        this.presigner = S3Presigner.builder()
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(s3Config.getAccessKey(), s3Config.getSecretKey())
                        )
                )
                .region(Region.AP_NORTHEAST_2)
                .build();
    }

    @GetMapping("/presigned-url")
    public String generatePresignedUrl(@RequestParam String key) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(Duration.ofMinutes(60))
                .build();

        URL presignedUrl = presigner.presignGetObject(presignRequest).url();

        return presignedUrl.toString();
    }
}
