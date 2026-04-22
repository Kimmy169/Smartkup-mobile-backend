package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.dto.UserResponseDTO;
import org.smartkup.smartkup.entity.User;
import org.smartkup.smartkup.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // POST /api/users/register
    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody User user) {
        return service.registerUser(user);
    }

    // GET /api/users/1
    @GetMapping("/{userId}")
    public UserResponseDTO getProfile(@PathVariable Long userId) {
        return service.getUserProfile(userId);
    }
}