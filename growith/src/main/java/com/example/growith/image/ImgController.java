package com.example.growith.image;

import com.example.growith.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


@RequestMapping("/admin")
@Controller
public class ImgController {
    @Autowired
    private ImgService imgService;
    @Value("${cloud.aws.s3.endpoint}")
    private String downpath;
    @Autowired
    private S3Service s3Service;

    @GetMapping("/image")
    public String create(Model model){
    	model.addAttribute("images", imgService.readlist());
        model.addAttribute("downpath","https://" +  downpath);
        return "admin_image";
    }

    @PostMapping("/image")
    public String create(@ModelAttribute Image image, @RequestParam("file") MultipartFile file)throws Exception{
        try{
            imgService.create(image, file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/image";
    }

//    @GetMapping("readlist")
//    public String readlist(Model model){
//        model.addAttribute("images", imgService.readlist());
//        model.addAttribute("downpath","https://" +  downpath);
//        return "admin_image";
//    }

    @GetMapping("/image/{id}")
    public String update(@PathVariable Integer id, Model model){
        Image image = imgService.findById(id);
        model.addAttribute("image", image);
        return "admin_image";
    }

    @PostMapping("/image/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Image image, @RequestParam("file") MultipartFile file) throws Exception {
        imgService.update(id, image, file);
        return "redirect:/admin/image";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws IOException {
        s3Service.deletefile(imgService.findById(id).getFileName());
        imgService.delete(id);
        return "redirect:/admin/image";
    }

    @GetMapping("/select/{id}")
    public String select(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        imgService.selectImage(id);
        redirectAttributes.addFlashAttribute("message", "Image selected successfully");
        return "redirect:/admin/image";
    }

    @GetMapping("/unselect/{id}")
    public String unselect(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        imgService.unselectImage(id);
        redirectAttributes.addFlashAttribute("message", "Image unselected successfully");
        return "redirect:/admin/image";
    }
}
