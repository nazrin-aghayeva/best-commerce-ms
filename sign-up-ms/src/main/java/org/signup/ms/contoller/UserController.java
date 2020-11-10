package org.signup.ms.contoller;



import org.signup.ms.model.Users;
import org.signup.ms.payload.MessageResponse;
import org.signup.ms.repository.UserRepo;
import org.signup.ms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@Valid Users user, HttpServletRequest httpRequest) {
        userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User have been successfully created"));
    }
}















