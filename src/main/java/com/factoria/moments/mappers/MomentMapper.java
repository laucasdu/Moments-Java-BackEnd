package com.factoria.moments.mappers;

import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.ArrayList;
import java.util.List;

public class MomentMapper {
    public MomentResponseDto mapMomentToMomentResponseDto(Moment moment, User auth){
        var response = new MomentResponseDto();
        response.setId(moment.getId());
        response.setImgUrl(moment.getImgUrl());
        response.setTitle(moment.getTitle());
        response.setDescription(moment.getDescription());

        response.setCreator(moment.getCreator());
        response.setCommentCount(moment.commentCount());
        response.setLiked(moment.checkIfLiked(auth));
        response.setLikesCount(moment.likesCount());
        return response;

    }

    public List<MomentResponseDto> mapMultipleMomentToListResponse(List<Moment> momentList, User auth){
        List<MomentResponseDto> responseList = new ArrayList<>();
        momentList.forEach(Moment -> responseList.add(this.mapMomentToMomentResponseDto(Moment, auth)));
        return responseList;
    }

    // qualsevol user
    public MomentResponseDto mapMomentToMomentResponseDto(Moment moment){
        var response = new MomentResponseDto();
        response.setId(moment.getId());
        response.setImgUrl(moment.getImgUrl());
        response.setTitle(moment.getTitle());
        response.setDescription(moment.getDescription());

        response.setCreator(moment.getCreator());
        response.setCommentCount(moment.commentCount());
        response.setLikesCount(moment.likesCount());
        return response;

    }

    public List<MomentResponseDto> mapMultipleMomentToListResponse(List<Moment> momentList){
        List<MomentResponseDto> responseList = new ArrayList<>();
        momentList.forEach(Moment -> responseList.add(this.mapMomentToMomentResponseDto(Moment)));
        return responseList;
    }

}
