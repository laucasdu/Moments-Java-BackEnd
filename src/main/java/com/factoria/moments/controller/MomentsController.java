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

    private User getAutUser() {
        return userService.getById(1L);
    }


    @GetMapping("/moments")
    ResponseEntity <List<MomentResponseDto>> getAll() {
        var authUser = getAutUser();
        var moments = momentService.getAll(authUser);
        return new ResponseEntity<>(moments, HttpStatus.OK);
    }


    // Get moment id amb control d'errors
    @GetMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> getById(@PathVariable Long id) {
        var authUser = getAutUser();
        MomentResponseDto moment = momentService.getById(id, authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }


   @PostMapping("/moments")
   ResponseEntity<MomentResponseDto> create(@RequestBody MomentRequestDto newMoment){
        var authUser = getAutUser();
        MomentResponseDto moment = momentService.create(newMoment,authUser);
        return new ResponseEntity<>(moment,HttpStatus.OK);
   }

    @PutMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> update(@RequestBody MomentRequestDto update, @PathVariable Long id){
        var authUser = getAutUser();
        MomentResponseDto moment = momentService.update(update, id, authUser);
        return new ResponseEntity<>(moment,HttpStatus.OK);
    }


    @DeleteMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> delete(@PathVariable Long id){
        var authUser = getAutUser();
        var moment = momentService.delete(id,authUser);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }

    @GetMapping(value="/moments", params="search")
    ResponseEntity<List<MomentResponseDto>> getBySearch(@RequestParam String search){
        var authUser = getAutUser();
        var searched = momentService.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search, authUser);
        return new ResponseEntity<>(searched, HttpStatus.OK);
    }


}







