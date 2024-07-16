package com.example.back_end.controller;

import com.example.back_end.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/addImage")
    public String addImagesToS3(@RequestParam("file") MultipartFile file) {
        try {
            String folderName = "testimages";
            String imageUrl = imageService.uploadImage(file, folderName);
            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    public String deleteImage(@RequestParam("imageUrl") String imageUrl) {
        return imageService.deleteImageFromS3(imageUrl);
        //return "Image deleted successfully!";
    }
}
