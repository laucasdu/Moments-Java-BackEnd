package com.factoria.moments.controller;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.IMomentService;
import com.factoria.moments.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity <List<MomentResponseDto>> getAll() {
        var authUser = getAutUser();
        var moments = momentService.getAll(authUser);
        return new ResponseEntity<>(moments, HttpStatus.OK);
    }


//    @GetMapping("/moments/{id}")
//    Moment getById(@PathVariable Long id) {
//        return momentService.findById(id);
//    }

    // Get moment id amb control d'errors
    @GetMapping("/moments/{id}")
    ResponseEntity<Moment> getById(@PathVariable Long id) {
        var moment = momentService.getById(id);
        return new ResponseEntity<>(moment, HttpStatus.NOT_FOUND);
    }




   @PostMapping("/moments")
   Moment create(@RequestBody MomentRequestDto newMoment){
        var authUser = getAutUser();
        return momentService.create(newMoment, authUser);
   }

    private User getAutUser() {
        return userService.getById(1L);
    }


    @PutMapping("/moments/{id}")
    Moment update(@RequestBody MomentRequestDto update, @PathVariable Long id){
        var authUser = getAutUser();
        Moment moment = momentService.update(update, id, authUser);
        return moment;
    }


    @DeleteMapping("/moments/{id}")
    boolean delete(@PathVariable Long id){
        var authUser = getAutUser();
        return momentService.delete(id, authUser);
    }

    @GetMapping(value="/moments", params="search")
        List<Moment> getBySearch(@RequestParam String search){
        return momentService.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search);
    }


}







