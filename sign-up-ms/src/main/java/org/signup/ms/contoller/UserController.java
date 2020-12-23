package org.signup.ms.contoller;

import org.signup.ms.entities.ERole;
import org.signup.ms.entities.Roles;
import org.signup.ms.entities.Users;
import org.signup.ms.payload.request.LoginRequest;
import org.signup.ms.payload.request.SignupRequest;
import org.signup.ms.payload.response.ApiResponse;
import org.signup.ms.payload.response.JwtResponse;
import org.signup.ms.payload.response.MessageResponse;
import org.signup.ms.repository.RoleRepository;
import org.signup.ms.repository.UserRepository;
import org.signup.ms.security.UserDetailsImpl;
import org.signup.ms.security.jwt.JwtUtils;
import org.signup.ms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles= userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getEmail(),
                userDetails.getId(),
                userDetails.getPassword(),
                roles));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody SignupRequest requestUser){
        if (userRepo.existsByEmail(requestUser.getEmail())){
            return ResponseEntity.badRequest().
                    body(new MessageResponse("User with such email exists"));
        }

        Users user= new Users(
                requestUser.getMerchant_type(),
                requestUser.getMerchant_name(),
                requestUser.getName(),
                requestUser.getEmail(),
                requestUser.getPhone_number(),
                requestUser.getAddress(),
                passwordEncoder.encode(requestUser.getPassword()),
                passwordEncoder.encode(requestUser.getConfirm_password()));

        Set<String> rolesNames= requestUser.getRoles();
        Set<Roles> roles= new HashSet<>();

        if (rolesNames== null){
            Roles userRole= roleRepository.findByName(ERole.USER).orElseThrow(()-> new RuntimeException("Role couldn't found")) ;
            roles.add(userRole);
        }
        else{
            rolesNames.forEach((role->{
                switch (role){
                    case "admin":
                        Roles adminRole= roleRepository.findByName(ERole.ADMIN).orElseThrow(()-> new RuntimeException("Role couldn't found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Roles userRole= roleRepository.findByName(ERole.USER).orElseThrow(()-> new RuntimeException("Role couldn't found"));
                        roles.add(userRole);
                }
            }));
        }
        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok(new MessageResponse("User have been registered successfully"));
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















