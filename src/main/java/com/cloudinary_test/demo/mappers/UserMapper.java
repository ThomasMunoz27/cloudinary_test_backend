package com.cloudinary_test.demo.mappers;

import com.cloudinary_test.demo.DTOs.UserDTOResponse;
import com.cloudinary_test.demo.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTOResponse toUserDTOResponse(User user){
        UserDTOResponse userDto = new UserDTOResponse();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRegisterDate(user.getRegisterDate());
        userDto.setPublicIdProfileImg(user.getPublicIdProfileImage());
        userDto.setLinkProfileImg(user.getLinkProfileImg());
        userDto.setCantImagesPublished(userDto.getCantImagesPublished());

        return userDto;
    }
}
