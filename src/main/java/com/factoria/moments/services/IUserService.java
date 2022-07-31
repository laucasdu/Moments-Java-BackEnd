package com.factoria.moments.services;

import com.factoria.moments.dtos.UserRequestDto;
import com.factoria.moments.models.User;

import java.util.List;

public interface IUserService {
    User getById(Long id);

    List<User> getAll();

}
