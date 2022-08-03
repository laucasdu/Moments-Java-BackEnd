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

    public LikeController(ILikeService likeService) {
        this.likeService = likeService;
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
        var isLiked = likeService.toggleLike(like);
        return new ResponseEntity<>(isLiked, HttpStatus.OK);

    }



}
