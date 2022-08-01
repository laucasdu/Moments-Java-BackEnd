package com.factoria.moments.services;


import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.exceptions.NotFoundException;
import com.factoria.moments.mappers.MomentMapper;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentService implements IMomentService {
    private IMomentRepository momentRepository;

    public MomentService(IMomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    //funció que agafi totes les caracteristiques del moment sencer.
    public Moment getWholeMoment(Long id){
        return momentRepository.findById(id).get();
    }

    @Override
    public List<MomentResponseDto> getAll(User authUser) {
        return new MomentMapper().mapMultipleMomentToListResponse(momentRepository.findAll(),authUser);
    }

//    @Override
//    public Moment findById(Long id) {
//        return momentRepository.findById(id).get();
//    }

    @Override
    public Moment create(MomentRequestDto momentRequestDto, User auth) {
        var newMoment = new Moment();
        newMoment.setTitle(momentRequestDto.getTitle());
        newMoment.setImgUrl(momentRequestDto.getImgUrl());
        newMoment.setDescription(momentRequestDto.getDescription());
        newMoment.setCreator(auth);
        //System.out.println(auth);
        return momentRepository.save(newMoment);

    }


    @Override
    public boolean delete(Long id, User authUser) {
        var moment= momentRepository.findById(id);
        if (moment.isEmpty()) throw new NotFoundException("Moment doesn't exist", "M-404");
        if (moment.get().getCreator()!=authUser) throw new BadRequestException("Only the creator can update his moment", "M-008");
        this.momentRepository.delete(moment.get());
        return true;
    }

    @Override
    public List<Moment> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(String search) {
        return momentRepository.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search);
    }

    @Override
    public Moment update(MomentRequestDto momentRequestDto, Long id, User authUser) {
        var momentEdit = momentRepository.findById(id);
        if (momentEdit.isEmpty()) throw new NotFoundException("Moment doesn't exist", "M-404");
        if (momentEdit.get().getCreator()!=authUser) throw new BadRequestException("Only the creator can update his moment", "M-008");
        momentEdit.get().setTitle(momentRequestDto.getTitle());
        momentEdit.get().setImgUrl(momentRequestDto.getImgUrl());
        momentEdit.get().setDescription(momentRequestDto.getDescription());
        return momentRepository.save(momentEdit.get());

    }

    @Override
    public MomentResponseDto getById(Long id, User authUser) {
        var opMoment = momentRepository.findById(id);
        if(opMoment.isEmpty()) throw new NotFoundException("Moment Not Found", "M-153");
        MomentResponseDto responseMoment = new MomentMapper().mapMomentToMomentResponseDto(opMoment.get(),authUser);
        return responseMoment;
    }


}
