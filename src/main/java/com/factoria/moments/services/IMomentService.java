package com.factoria.moments.services;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.List;

public interface IMomentService {


    Moment getWholeMoment (Long id);
    List<MomentResponseDto> getAll(User authUser);

    MomentResponseDto getById(Long id, User authUser);

    MomentResponseDto create(MomentRequestDto newMoment, User authUser);

    MomentResponseDto update(MomentRequestDto newMoment, Long id, User authUser);

    MomentResponseDto delete(Long id, User authUser);

    List<MomentResponseDto> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(String search, User authUser);


}
