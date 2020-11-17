package org.signup.ms.contoller;

import org.signup.ms.entities.Users;
import org.signup.ms.entities.dto.UsersDto;
import org.signup.ms.payload.MessageResponse;
import org.signup.ms.repository.UserRepo;
import org.signup.ms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@Valid UsersDto user, HttpServletRequest httpRequest) {
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User have been successfully created"));
    }
}


//package com.devglan.controller;
//
//        import com.devglan.model.ApiResponse;
//        import com.devglan.model.User;
//        import com.devglan.model.UserDto;
//        import com.devglan.service.UserService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public ApiResponse<User> saveUser(@RequestBody UserDto user){
//        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
//    }
//
//    @GetMapping
//    public ApiResponse<List<User>> listUser(){
//        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ApiResponse<User> getOne(@PathVariable int id){
//        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
//        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResponse<Void> delete(@PathVariable int id) {
//        userService.delete(id);
//        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
//    }
//
//
//
//}












