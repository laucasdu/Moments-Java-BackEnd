package com.factoria.moments.services;

import com.factoria.moments.models.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> findAll();

    Comment getById(Long id);
}
