package com.factoria.moments.services;


import com.factoria.moments.dtos.LikeRequestDto;
import com.factoria.moments.models.Like;
import com.factoria.moments.models.User;

import java.util.List;

public interface ILikeService {


    List<Like> getAll();

    List<Like> getAllByMomentId(Long id);

    Boolean toggleLike(LikeRequestDto requestDto);

    Like getById(Long id);
}
