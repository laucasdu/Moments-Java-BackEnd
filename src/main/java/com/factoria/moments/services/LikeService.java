package com.factoria.moments.services;

import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ILikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Like getById(Long id) {
        return likeRepository.findById(id).get();
    }

    @Override
    public  List<Like> getAllByMomentId(Long id) {
        return likeRepository.findByMomentId(id);
    }

    @Override
    public Boolean toggleLike(LikeRequestDto requestDto, User authUser) {
        var moment = momentService.getById(requestDto.getMomentId(), authUser);
        if (moment.getCreator()==authUser) throw new BadRequestException("el creator no pot donar like al seu propi moment", "L-120" );
        var like = new Like();
        like.setLover(authUser);
        like.setMoment(momentService.getWholeMoment(moment.getId()));

        var checkedLike = this.checkIfLikeAllreadyExists(like);
        if (checkedLike.isPresent()){
            return this.dislike(checkedLike.get());
        }

        return this.like(like);
    }

    public Optional<Like> checkIfLikeAllreadyExists(Like like){
        List<Like> likes = likeRepository.findByMomentId(like.getMoment().getId());
        return likes.stream().filter(Like -> Like.getLover()==like.getLover()).findFirst();

    }
    private Boolean like(Like like) {
        likeRepository.save(like);
        return true;
    }

    private Boolean dislike(Like like) {
        likeRepository.delete(like);
        return false;
    }





}
