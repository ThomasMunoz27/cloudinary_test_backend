package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User>{
    public UserController(UserService userService){
        super(userService);
    }
}
