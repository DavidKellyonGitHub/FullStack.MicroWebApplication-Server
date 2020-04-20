package com.dmvs.blog.projBlog.Controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dmvs.blog.projBlog.Services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/storage/")
public class AmazonS3Controller {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return amazonS3Service.uploadFile(file);
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestBody String fileURL) {
        return amazonS3Service.deleteFileFromBucket(fileURL);
    }
}
