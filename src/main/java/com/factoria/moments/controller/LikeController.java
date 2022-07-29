package com.factoria.moments.controller;

import com.factoria.moments.services.ILikeService;
import com.factoria.moments.services.IMomentService;
import com.factoria.moments.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class LikeController {

    //ATRIBUTS
    private IUserService userService;
    private ILikeService likeService;


    public LikeController(IUserService userService, ILikeService likeService) {
        this.userService = userService;
        this.likeService = likeService;
    }






}
