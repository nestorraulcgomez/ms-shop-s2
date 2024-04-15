package com.example.msshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.msshop.model.User;
import com.example.msshop.service.UserService;



@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        User currentUser = userService.login(user.getUsername(), user.getPassword());
        if (currentUser == null) {
            log.error("Error al iniciar sesión con el usuario {}", user.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Error al iniciar sesión con el usuario " + user.getUsername()));
        }
        return ResponseEntity.ok(user);
    }
    

    @GetMapping("/users")
    public List<User> getAllUsers() {
        log.info("GET /users");
        log.info("Retornando todos los usuarios");
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            log.error("No se encontró el usuario con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + id));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Validated @RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser == null) {
            log.error("Error al crear el usuario {}", user);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el usuario"));
        }
        return ResponseEntity.ok(createdUser);
    }
    
    @PutMapping("users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userService.getUserById(id).get();
        if (currentUser == null) {
            log.error("No se encontró el usuario con ID {}", id);
            return null;
        }
        return userService.updateUser(id, user);
    }

    @DeleteMapping("users/{id}")
    public void deleteStudent(@PathVariable Long id){
        User currentUser = userService.getUserById(id).get();
        if (currentUser == null) {
            log.error("No se encontró el usuario con ID {}", id);
            return;
        }
        userService.deleteUser(id);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }

}
