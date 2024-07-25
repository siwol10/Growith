package com.example.growith.image;

import com.example.growith.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImgService {

    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private S3Service s3Service;

    public void create(Image image, MultipartFile file1) throws Exception{
        UUID uuid = UUID.randomUUID();
        String fileName1 = uuid + "_" + file1.getOriginalFilename();
        s3Service.uploadfile(file1, fileName1);
        image.setFileName(fileName1);
        this.imgRepository.save(image);
    }

    public List<Image> readlist(){
        return this.imgRepository.findAll();
    }

    public Image findById(Integer id){
        Optional<Image> o = imgRepository.findById(id);
        return o.get();
    }

    public void update(Integer id, Image image, MultipartFile file1) throws Exception{
        Optional<Image> o = imgRepository.findById(id);
        Image image1 = o.get();
        UUID uuid = UUID.randomUUID();
        String fileName1 = uuid + "_" + file1.getOriginalFilename();
        s3Service.uploadfile(file1, fileName1);
        image1.setFileName(fileName1);
        this.imgRepository.save(image1);
    }

    public void delete(Integer id){
        this.imgRepository.deleteById(id);
    }

    public void selectImage(Integer id) {
//        List<Image> images = imgRepository.findAll();
//        for (Image img : images) {
//            img.setSelected(false);
//        }
        Image selectedImage = findById(id);
        selectedImage.setSelected(true);
        imgRepository.save(selectedImage);
//        imgRepository.saveAll(images);
    }

    public void unselectImage(Integer id) {
        Image selectedImage = findById(id);
        selectedImage.setSelected(false);
        imgRepository.save(selectedImage);
    }

    public List<Image> getSelectedImage(){
        return imgRepository.findBySelected(true);
    }
}
