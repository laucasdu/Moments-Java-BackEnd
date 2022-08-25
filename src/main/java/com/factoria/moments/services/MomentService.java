package com.factoria.moments.services;


import com.factoria.moments.auth.facade.IAuthenticationFacade;
import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.exceptions.NotFoundException;
import com.factoria.moments.mappers.MomentMapper;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentService implements IMomentService {
    private IMomentRepository momentRepository;
    private IAuthenticationFacade authenticationFacade;

    public MomentService(IMomentRepository momentRepository, IAuthenticationFacade authenticationFacade) {
        this.momentRepository = momentRepository;
        this.authenticationFacade = authenticationFacade;
    }

    //funció que agafi totes les caracteristiques del moment sencer.
    public Moment getWholeMoment(Long id){
        return momentRepository.findById(id).get();
    }

    @Override
    public List<MomentResponseDto> getAll() {
        var authUser = authenticationFacade.getAuthUser();
        if(authUser.isEmpty()) return new MomentMapper().mapMultipleMomentToListResponse(momentRepository.findAll());
        return new MomentMapper().mapMultipleMomentToListResponse(momentRepository.findAll(), authUser.get());
    }


    @Override
    public MomentResponseDto getById(Long id) {
        var opMoment = momentRepository.findById(id);
        if(opMoment.isEmpty()) throw new NotFoundException("Moment Not Found", "M-153");
        if(this.authenticationFacade.getAuthUser().isEmpty()) return new MomentMapper().mapMomentToMomentResponseDto(opMoment.get());
        MomentResponseDto responseMoment = new MomentMapper().mapMomentToMomentResponseDto(opMoment.get(), this.authenticationFacade.getAuthUser().get());
        return responseMoment;
    }


    @Override
    public MomentResponseDto create(MomentRequestDto momentRequestDto, User authUser) {
        var newMoment = new Moment();
        newMoment.setTitle(momentRequestDto.getTitle());
        newMoment.setImgUrl(momentRequestDto.getImgUrl());
        newMoment.setDescription(momentRequestDto.getDescription());
        newMoment.setCreator(authUser);
        momentRepository.save(newMoment);
        return new MomentMapper().mapMomentToMomentResponseDto(newMoment,authUser);

    }

    @Override
    public MomentResponseDto update(MomentRequestDto momentRequestDto, Long id, User authUser) {
        var momentEdit = momentRepository.findById(id);
        if (momentEdit.isEmpty()) throw new NotFoundException("Moment doesn't exist", "M-404");
        if (momentEdit.get().getCreator()!=authUser) throw new BadRequestException("Incorrect User", "M-008");
        momentEdit.get().setTitle(momentRequestDto.getTitle());
        momentEdit.get().setImgUrl(momentRequestDto.getImgUrl());
        momentEdit.get().setDescription(momentRequestDto.getDescription());
        momentRepository.save(momentEdit.get());
        return new MomentMapper().mapMomentToMomentResponseDto(momentEdit.get(),authUser);
    }

    @Override
    public Boolean delete(Long id, User authUser) {
        var moment= momentRepository.findById(id);
        if (moment.isEmpty()) throw new NotFoundException("Moment doesn't exist", "M-404");
        if (moment.get().getCreator()!=authUser) throw new BadRequestException("Incorrect User", "M-008");
        momentRepository.delete(moment.get());
        return true;
    }



    @Override
    public List<MomentResponseDto> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(String search, User authUser) {
        var searched = momentRepository.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search);
        return new MomentMapper().mapMultipleMomentToListResponse(searched, authUser);
    }


}
