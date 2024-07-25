package com.example.growith;

import com.example.growith.image.Image;
import com.example.growith.image.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ImgService imgService;
    @GetMapping("/")
    public String index(Model model) {
//        Image selectedImage = imgService.getSelectedImage();
//        model.addAttribute("selectedImage", selectedImage);
        return "index";
    }
}
