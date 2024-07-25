package com.example.growith;

import com.example.growith.image.Image;
import com.example.growith.image.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {
    private final ImgService imgService;

    @Value("${cloud.aws.s3.endpoint}")
    private String downpath;

    @ModelAttribute("imgList")
    public List<String> getHeaderBackground() {
        List<Image> selectedImages = imgService.getSelectedImage();
        List<String> fileNames = selectedImages.stream()
                .map(file -> "https://" + downpath + "/" + file.getFileName()).toList();

        return fileNames;
    }
}
