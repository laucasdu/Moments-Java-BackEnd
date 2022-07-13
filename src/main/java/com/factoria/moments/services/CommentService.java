package com.factoria.moments.services;

import com.factoria.moments.models.Comment;
import com.factoria.moments.repositories.ICommentRepository;
import com.factoria.moments.services.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).get();
    }





}
