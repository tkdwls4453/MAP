package com.sparta.finalprojectback.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.finalprojectback.s3.model.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AwsS3ServiceImpl implements AwsS3Service {

    private final AmazonS3 amazonS3;
    private final S3Component component;


    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3.putObject(new PutObjectRequest(component.getBucket(), fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void deleteFile(String fileName) {
        String target = "amazonaws.com/";
        int targetIdx = fileName.indexOf(target) + target.length();
        System.out.println(fileName.substring(targetIdx));
        amazonS3.deleteObject(component.getBucket(), fileName.substring(targetIdx));
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(component.getBucket(), fileName).toString();
    }



}
