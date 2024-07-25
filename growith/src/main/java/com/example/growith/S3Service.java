package com.example.growith;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

//현재 미사용
@Service
public class S3Service {

    @Value("growith")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public void uploadfile(MultipartFile multipartFile, String filename) throws IOException{

        //현재 서버에 임시 저장
        File file = new File(multipartFile.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }

        //AWS 전송-UUID 적용버전, UUID로 적용된 이름을 가져와서 사용한다.
        //String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, filename, file));

        //임시 저장된 사진 삭제
        file.delete();
    }

    public void deletefile(String filename) throws IOException{
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, filename));
    }
}

