package com.cloudinary_test.demo.mappers;

import com.cloudinary_test.demo.DTOs.ImageForUserDTO;
import com.cloudinary_test.demo.DTOs.ImagePageDTO;
import com.cloudinary_test.demo.DTOs.UserForImageDTO;
import com.cloudinary_test.demo.Entities.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageForUserDTO toImageForUserDTO(Image image){
        ImageForUserDTO imgDto = new ImageForUserDTO();

        imgDto.setId(image.getId());
        imgDto.setLikes(image.getLikes());
        imgDto.setDislike(image.getDislike());
        imgDto.setDateUpload(image.getDateUploaded());


        return imgDto;
    }

    public ImagePageDTO toImagePageDTO(Image image){
        ImagePageDTO imgDto = new ImagePageDTO();
        imgDto.setId(image.getId());
        imgDto.setPublicId(image.getPublicId());
        imgDto.setLink(image.getLink());
        imgDto.setName(image.getName());
        imgDto.setDescription(image.getDescription());
        imgDto.setLikes(image.getLikes());
        imgDto.setDislike(image.getDislike());
        imgDto.setDateUpload(image.getDateUploaded());


        UserForImageDTO userDto = new UserForImageDTO();
        userDto.setId(image.getUserId().getId());
        userDto.setUsername(image.getUserId().getUsername());
        userDto.setLinkProfileImg(image.getUserId().getLinkProfileImg());

        imgDto.setUserId(userDto);
        imgDto.setCategories(image.getCategories());

        return imgDto;
    }
}
