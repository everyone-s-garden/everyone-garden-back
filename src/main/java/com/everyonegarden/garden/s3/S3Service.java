package com.everyonegarden.garden.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final S3Client s3;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public String putObject(String key, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3.putObject(objectRequest, RequestBody.fromBytes(file));

        String encodedKey = URLEncoder.encode(key, StandardCharsets.UTF_8);
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, encodedKey);
    }

    public byte[] getObject(String bucketName, String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3.getObject(getObjectRequest);

        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
