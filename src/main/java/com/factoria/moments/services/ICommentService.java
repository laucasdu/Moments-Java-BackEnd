package com.factoria.moments.services;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.User;

import java.util.List;

public interface ICommentService {


    List<Comment> findAll();

    List<Comment> getAllByMomentsId(Long id);

    Comment create(CommentRequestDto commentRequestDto, User authUser);
}
