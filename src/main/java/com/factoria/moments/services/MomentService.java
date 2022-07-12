package com.factoria.moments.services;


import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentService implements IMomentService {
    private IMomentRepository momentRepository;

    public MomentService(IMomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    @Override
    public List<Moment> getAll() {
        return momentRepository.findAll();
    }

    @Override
    public Moment findById(Long id) {
        return momentRepository.findById(id).get();
    }


    @Override
    public Moment create(MomentRequestDto momentRequestDto, User auth) {
        var newMoment = new Moment();
        newMoment.setTitle(momentRequestDto.getTitle());
        newMoment.setImgUrl(momentRequestDto.getImgUrl());
        newMoment.setDescription(momentRequestDto.getDescription());
        newMoment.setCreator(auth);
        System.out.println(auth);
        return momentRepository.save(newMoment);

    }

    @Override
    public Moment update(Long id, MomentRequestDto updateMoment, User authUser) {
        var moment = momentRepository.findById(id).get();
        moment.setTitle(updateMoment.getTitle());
        moment.setImgUrl(updateMoment.getTitle());
        moment.setDescription(updateMoment.getTitle());
        return momentRepository.save(moment);
    }

    @Override
    public Moment delete(Long id) {
        var moment= momentRepository.findById(id).get();
        this.momentRepository.delete(moment);
        return moment;
    }

    @Override
    public List<Moment> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(String search) {
        return momentRepository.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search);
    }


}
