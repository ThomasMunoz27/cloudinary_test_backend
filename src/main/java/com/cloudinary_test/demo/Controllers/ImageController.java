package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins ="*")
public class ImageController extends BaseController<Image>{
    public ImageController(ImageService imageService){
        super(imageService);
    }


}
