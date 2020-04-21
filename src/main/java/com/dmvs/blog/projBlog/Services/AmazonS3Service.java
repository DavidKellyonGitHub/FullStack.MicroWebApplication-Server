package com.dmvs.blog.projBlog.Services;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Service
public class AmazonS3Service {

    @Autowired
    public AmazonS3 amazonS3;

    @Value("${app.awsServices.bucketName}")
    String bucketName;

    @Value("${endpointURL}")
    String endpointURL;

    public String uploadFile(MultipartFile multipartFile) {
        String fileURL = "";
        try {
            File file = convertMultipartFileToFile(multipartFile);
            String fileName = multipartFile.getOriginalFilename();
            fileURL = endpointURL + "/" + bucketName + "/" + fileName;
            uploadFileToBucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileURL;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }


    private void uploadFileToBucket(String bucketName, File file) {
        String uniqueFileName = LocalDate.now() + "_" + file.getName();
        amazonS3.putObject(new PutObjectRequest(this.bucketName, uniqueFileName, file)
                .withCannedAcl(CannedAccessControlList.PublicReadWrite));
    }

    public String deleteFileFromBucket(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Deletion Successful";
    }


}
