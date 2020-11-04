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

//    @Autowired
//    ConfirmationTokenRepo confirmationTokenRepo;
//
//    @Autowired
//    EmailService emailService;

    @Autowired
    UserRepo userRepo;

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registrationForm")
    public Users registrationForm(){
        return new Users();
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@Valid Users user, HttpServletRequest httpRequest){
            userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User have been successfully created"));


        //            ConfirmationToken confirmationToken= new ConfirmationToken(user);
//            confirmationTokenRepo.save(confirmationToken);

//            Mail mail= new Mail();
//            mail.setFrom("nazrin.agha12@gmail.com");
//            mail.setTo(user.getEmail());
//            mail.setSubject("Complete your registration");
//            mail.setContent("Dear "+ user.getFull_name()+" to complete your registration follow: "+
//                    httpRequest.getScheme()+ "://" + httpRequest.getServerName() + ":" +httpRequest.getServerPort()+
//                    "/confirm-token?token="+confirmationToken.getConfirmToken());
//            emailService.sendEmail(mail);
//        }

    }

//
//    @RequestMapping(value = "/confirm-token", method={RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView confirmMail(ModelAndView model, @RequestParam("token") String confirmToken){
//        ConfirmationToken confirmationToken= confirmationTokenRepo.findByConfirmToken(confirmToken);
//
//        if (confirmationToken!=null){
//            Users user= userRepo.findByEmailIgnoreCase(confirmationToken.getUsers().getEmail());
//            user.setEnabled(true);
//            userService.saveUser(user);
//            model.setViewName("login");
//        }
//        else
//        {
//            model.addObject("message", "Invalid Link");
//            model.setViewName("error");
//        }
//        return model;
//    }
}















