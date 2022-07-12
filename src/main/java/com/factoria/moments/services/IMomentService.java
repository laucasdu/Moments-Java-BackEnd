package com.factoria.moments.services;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.List;

public interface IMomentService {

    List<Moment> getAll();

    Moment findById(Long id);

    Moment create(MomentRequestDto newMoment, User auth);


    Moment update(Long id, MomentRequestDto updateMoment, User authUser);

    Moment delete(Long id);

    List<Moment> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(String search);
}
