package com.factoria.moments.controller;

import com.factoria.moments.auth.facade.IAuthenticationFacade;
import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.ICommentService;
import com.factoria.moments.services.IMomentService;
import com.factoria.moments.services.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CommentController {

    // ATRIBUTS
    private ICommentService commentService;
    private IMomentService momentService;
    private IUserService userService;

    private IAuthenticationFacade authenticationFacade;

    public CommentController(ICommentService commentService, IMomentService momentService, IUserService userService, IAuthenticationFacade authenticationFacade) {
        this.commentService = commentService;
        this.momentService = momentService;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }


// CONSTRUCTOR


    private User getAuthUser() {
        return userService.getById(1L);
    }

    @GetMapping("/comments")
    List<Comment> getAll(){
        return commentService.findAll();
    }

    @GetMapping("/moments/{id}/comments")
    List<Comment> getAllByMomentsId(@PathVariable Long id){
        return commentService.getAllByMomentsId(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/comments")
    Comment create(@RequestBody CommentRequestDto commentRequestDto){
        var authUser = authenticationFacade.getAuthUser();
        return commentService.create(commentRequestDto, authUser);
    }




}
