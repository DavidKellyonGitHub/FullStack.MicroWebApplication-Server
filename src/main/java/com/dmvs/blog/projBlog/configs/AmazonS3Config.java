package com.dmvs.blog.projBlog.configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@Component
public class AmazonS3Config {
    @Value("${cloud.aws.credentials.accessKey}")
    String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    String accessSecret;
    @Value("${cloud.aws.region.static}")
    String region;

//    @Bean
//    public AmazonS3 generateS3Client() {
//        final AWSCredentials credentials = new BasicAWSCredentials(accessKey,accessSecret);
//        AmazonS3 amazonS3 =  AmazonS3ClientBuilder
//                .standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .build();
//        return amazonS3;
//    }

    @Bean
    public AmazonS3 generateS3Client(){
        AmazonS3 amazonS3 = AmazonS3Client
                .builder()
                .withRegion(region)
                .build();
        return amazonS3;
    }

}
