package com.factoria.moments.controller;

import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.factoria.moments.services.ILikeService;
import com.factoria.moments.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class LikeController {

    //ATRIBUTS
    private ILikeService likeService;
    private IUserService userService;

    public LikeController(ILikeService likeService, IUserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    private User getAuthUser(Long id){
        return userService.getById(id);
    }

    @GetMapping("/likes")
    ResponseEntity<List<Like>> getAll() {
        var like = likeService.getAll();
        return new ResponseEntity<>(like,HttpStatus.OK);
    }

    @GetMapping("/moments/{id}/likes")
    ResponseEntity<List<Like>>getMomentLikes(@PathVariable Long id) {
        var like = likeService.getAllByMomentId(id);
        return new ResponseEntity<>(like,HttpStatus.OK);
    }

    @GetMapping("/likes/{id}")
    ResponseEntity<Like> getById(@PathVariable Long id) {
        var likes = likeService.getById(id);
        return new ResponseEntity<>(likes,HttpStatus.OK);
    }


    @PostMapping("/likes")
    ResponseEntity<Boolean> create(@RequestBody LikeRequestDto like){
        User authUser = this.getAuthUser(1L);
        var isLiked = likeService.toggleLike(like, authUser);
        return new ResponseEntity<>(isLiked, HttpStatus.OK);

    }



}
