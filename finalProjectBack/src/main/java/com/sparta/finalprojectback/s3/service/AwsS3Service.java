package com.sparta.finalprojectback.s3.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface AwsS3Service {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    void deleteFile(String fileName);
    String getFileUrl(String fileName);

}
