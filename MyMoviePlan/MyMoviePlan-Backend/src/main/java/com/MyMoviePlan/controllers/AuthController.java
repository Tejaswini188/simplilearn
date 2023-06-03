package com.MyMoviePlan.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyMoviePlan.model.ERole;
import com.MyMoviePlan.model.Role;
import com.MyMoviePlan.model.User;
import com.MyMoviePlan.model.UserData;
import com.MyMoviePlan.repository.RoleRepository;
import com.MyMoviePlan.repository.UserDataRepository;
import com.MyMoviePlan.repository.UserRepository;
import com.MyMoviePlan.request.LoginRequest;
import com.MyMoviePlan.request.PasswordResetRequest;
import com.MyMoviePlan.request.SignupRequest;
import com.MyMoviePlan.request.UserRequest;
import com.MyMoviePlan.response.MessageResponse;
import com.MyMoviePlan.response.UserInfoResponse;
import com.MyMoviePlan.security.JwtUtils;
import com.MyMoviePlan.services.ChangePasswordService;
import com.MyMoviePlan.services.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;
  
  @Autowired
  UserDataRepository userDataRepository;

  @Autowired
  JwtUtils jwtUtils;
  
 @Autowired
 ChangePasswordService changePasswordService;


  @PostMapping("/signin")
  //@PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

   /* return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(userDetails.getId(),
                                   userDetails.getUsername(),
                                 roles));*/
   /* return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new MessageResponse("Signin Successful"));*/
    
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new UserInfoResponse(userDetails.getId(),
                     userDetails.getUsername(),
                     roles,
                     jwtCookie.getValue()));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
    		              signUpRequest.getEmail(),
    		              encoder.encode(signUpRequest.getPassword())                                                
                         );
    
    //encoder.encode(signUpRequest.getPassword())
    
  
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    user=userRepository.save(user);
    signUpRequest.setUser(user);
    
    UserData userData= new UserData(signUpRequest.getFirstname(),
    		signUpRequest.getLastname(),
    		signUpRequest.getAddress(),
    		signUpRequest.getCity(),
    		signUpRequest.getCountry(),
    		signUpRequest.getUser());
    
    userDataRepository.save(userData);

    //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    return ResponseEntity.ok(userData);
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
  
  @PutMapping("/resetPass")
  //@PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<?> restPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
	  
      return ResponseEntity.ok(new MessageResponse(changePasswordService.changePassword(passwordResetRequest.getUsername(), passwordResetRequest.getPassword())));

	}  
  
  @GetMapping("/userDetails/{id}")
	private ResponseEntity<?> getUserData(@PathVariable("id")Long id) {
		UserData userData=userDataRepository.findByUser_Id(id);
		UserRequest userRequest =new UserRequest();
		userRequest.setFirstname(userData.getFirstname());
		userRequest.setLastname(userData.getFirstname());
		userRequest.setEmail(userData.getUser().getEmail());
		userRequest.setAddress(userData.getAddress());
		userRequest.setCity(userData.getCity());
		userRequest.setCountry(userData.getCountry());
		userRequest.setRoles(userData.getUser().getRoles());
				
		return ResponseEntity.ok(userRequest);
	}
  
  @GetMapping("/allUsers")
  private ResponseEntity<?> getAllUsers() {
	  
	  List<UserData> useData=userDataRepository.findAll();
	  return ResponseEntity.ok(useData);
  }
}
