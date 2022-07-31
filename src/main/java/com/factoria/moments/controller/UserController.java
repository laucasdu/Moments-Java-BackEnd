package com.factoria.moments.controller;

import com.factoria.moments.dtos.UserRequestDto;
import com.factoria.moments.models.User;
import com.factoria.moments.services.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    List<User>getAll(){
        return userService.getAll();
    }


    @GetMapping("/users/{id}")
    User findById(@PathVariable Long id){
        var user = userService.getById(id);
        return user;
    }














}












