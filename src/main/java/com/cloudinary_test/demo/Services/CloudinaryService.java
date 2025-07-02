package com.cloudinary_test.demo.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary_test.demo.Exceptions.CloudinaryUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    public Map uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult;
        }catch (IOException e){
            throw new CloudinaryUploadException("Falló la subida a cloudinary", e);
        }

    }

    public Map updateImage(String publicId, MultipartFile file){
        try{
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

            return uploadImage(file);
        }catch (IOException e){
            throw new CloudinaryUploadException("Falló Actualizar imagen", e);
        }


    }
}
