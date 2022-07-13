package com.factoria.moments.controller;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.IMomentService;
import com.factoria.moments.services.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class MomentsController {


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
        var authUser = getAutUser();
        return momentService.create(newMoment, authUser);
   }

    private User getAutUser() {
        return userService.getById(1L);
    }
//
//    @PutMapping("/moments/{id}")
//    Moment update(@PathVariable Long id, @RequestBody MomentRequestDto updateMoment) {
//        var authUser=userService.getById(updateMoment.getUserId());
//        return momentService.update(id, updateMoment, authUser);
//    }
//
//
//    @PutMapping("/instants/{id}")
//    Instant update(@RequestBody InstantRequestDto newInstant, @PathVariable Long id){
//        Instant instant = instantService.update(newInstant, id);
//        return instant;
//    }

    @PutMapping("/moments/{id}")
    Moment update(@RequestBody MomentRequestDto newMoment, @PathVariable Long id){
        Moment moment = momentService.update(newMoment, id);
        return moment;
    }


//    @PutMapping("/moments/{id}")
//    Moment update(@RequestBody MomentRequestDto newMoment, @PathVariable Long id){
//        Moment moment = momentService.update(newMoment, id);
//        return moment;
//    }



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







