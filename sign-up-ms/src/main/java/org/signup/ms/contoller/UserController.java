package org.signup.ms.contoller;

import org.signup.ms.entities.Users;
import org.signup.ms.entities.dto.UsersDto;
import org.signup.ms.payload.response.ApiResponse;
import org.signup.ms.repository.UserRepo;
import org.signup.ms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponse<Users> saveUser(@RequestBody UsersDto usersDto){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully", userService.save(usersDto));
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public ApiResponse<List<Users>> userList(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched", userService.findAll());
    }
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ApiResponse<Users> getUser(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully", userService.findById(id));
    }
}















