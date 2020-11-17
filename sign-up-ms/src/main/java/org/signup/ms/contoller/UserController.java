package org.signup.ms.contoller;

import org.signup.ms.entities.Users;
import org.signup.ms.entities.dto.UsersDto;
import org.signup.ms.entities.response.ApiResponse;
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

    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public ApiResponse<Users> saveUser(@RequestBody UsersDto usersDto){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully", userService.save(usersDto));
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public ApiResponse<List<Users>> userList(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched", userService.findAll());
    }

//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
}



//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
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
//
//        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.",  userService.delete(id));
//    }















