package com.pc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
    	return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
    	return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/products")
    public ResponseEntity<?> getUserProducts(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserProducts(id), HttpStatus.OK);
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable Long id, @RequestBody UserDto userRequest) {
        return new ResponseEntity<>(userService.editUser(id, userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
