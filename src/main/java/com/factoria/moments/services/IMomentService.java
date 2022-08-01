package com.factoria.moments.services;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;

import java.util.List;

public interface IMomentService {

    List<Moment> getAll(User authUser);

//    Moment findById(Long id);

    Moment create(MomentRequestDto newMoment, User auth);

    boolean delete(Long id, User authUser);

    List<Moment> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(String search);

    Moment update(MomentRequestDto newMoment, Long id, User authUser);

    Moment getById(Long id);
}
