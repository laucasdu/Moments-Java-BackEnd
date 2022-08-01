package com.factoria.moments.services;

import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ILikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements ILikeService {

    private ILikeRepository likeRepository;
    private IMomentService momentService;
    private IUserService userService;

    public LikeService(ILikeRepository likeRepository, IMomentService momentService, IUserService userService) {
        this.likeRepository = likeRepository;
        this.momentService = momentService;
        this.userService = userService;
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();

    }

    @Override
    public  List<Like> getAllByMomentId(Long id) {
        return likeRepository.findByMomentId(id);
    }

    @Override
    public Like createLike(LikeRequestDto requestDto, User authUser) {
        var moment = momentService.getById(requestDto.getMomentId());
        if (moment.getCreator()==authUser) throw new BadRequestException("el creator no pot donar like al seu propi moment", "L-120" );
        var like = new Like();
        like.setLover(authUser);
        like.setMoment(moment);
        return likeRepository.save(like);
    }

    @Override
    public Like getById(Long id) {
        return likeRepository.findById(id).get();
    }

}
