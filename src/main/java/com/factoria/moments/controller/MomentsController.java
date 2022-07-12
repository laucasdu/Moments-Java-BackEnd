package com.factoria.moments.controller;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.services.IMomentService;
import com.factoria.moments.services.IUserService;
import com.factoria.moments.services.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class MomentsController<newMoment> {


    private IMomentService momentService;
    private IUserService userService;

    public MomentsController(IMomentService momentService, IUserService userService) {
        this.momentService = momentService;
        this.userService = userService;
    }


    @GetMapping("/moments")
    List<Moment> getAll() {
        return momentService.getAll();
    }


    @GetMapping("/moments/{id}")
    Moment getById(@PathVariable Long id) {
        return momentService.findById(id);
    }

    @PostMapping("/moments")
    Moment create(@RequestBody MomentRequestDto newMoment){
        var authUser = userService.getById(newMoment.getUserId());
        return momentService.create(newMoment, authUser);

    }

    @PutMapping("/moments/{id}")
    Moment update(@PathVariable Long id, @RequestBody MomentRequestDto updateMoment) {
        var authUser=userService.getById(updateMoment.getUserId());
        return momentService.update(id, updateMoment, authUser);
    }

    @DeleteMapping("/moments/{id}")
    boolean delete(@PathVariable Long id){
        Moment moment = momentService.delete(id);
        return true;
    }

    @GetMapping(value="/moments", params="search")
        List<Moment> getBySearch(@RequestParam String search){
        return momentService.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search);
    }


//
//    @PutMapping("/moments/{id}")
//    Moment updateMoment(@PathVariable Long id, @RequestBody Moment updateMoment) {
//        var moment = momentService.findById(id).get();
//        moment.setImgUrl(updateMoment.getImgUrl());
//        moment.setTitle(updateMoment.getTitle());
//        moment.setDescription(updateMoment.getDescription());
//        var dbMoment= momentService.save(moment);
//        return dbMoment;
//    }
//    @DeleteMapping("/moments/{id}")
//    boolean deleteMoment(@PathVariable Long id){
//        Moment moment = this.momentService.findById(id).get();
//        this.momentService.delete(moment);
//        return true;
//    }
//
//
//    @GetMapping(value="/moments", params="search")
//    List<Moment> getMomentSearch(@RequestParam String search) {
//        return momentService.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, search);
//
//    }
//


}







