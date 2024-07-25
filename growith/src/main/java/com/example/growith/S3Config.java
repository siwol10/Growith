package com.example.growith;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

//현재 미사용
@Configuration
public class S3Config {
    @Value("${aws.accessKeyId}")
    private String awsAccesskey;

    @Value("${aws.secretAccessKey}")
    private String awsSecretkey;

    @Value("ap-northeast-2")
    private String region;

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccesskey, awsSecretkey);
        return  AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    }
}
