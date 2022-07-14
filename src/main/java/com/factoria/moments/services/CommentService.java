package com.factoria.moments.services;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ICommentRepository;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.repositories.IUserRepository;
import com.factoria.moments.services.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private ICommentRepository commentRepository;
    private IMomentRepository momentRepository;
    private IUserRepository userRepository;

    public CommentService(ICommentRepository commentRepository, IMomentRepository momentRepository, IUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.momentRepository = momentRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllByMomentsId(Long id) {
        return commentRepository.findByMoment_Id(id);
    }

    @Override
    public Comment create(CommentRequestDto commentRequestDto, User authUser) {
        Comment comment = new Comment();
        Moment moment = this.momentRepository.findById(commentRequestDto.getMomentId()).get();
        comment.setComment(commentRequestDto.getComment());
        comment.setCreator(authUser);
        comment.setMoment(moment);
        return commentRepository.save(comment);
    }


}
