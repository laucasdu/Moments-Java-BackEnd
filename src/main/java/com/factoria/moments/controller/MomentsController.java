package com.factoria.moments.controller;

import com.factoria.moments.auth.facade.IAuthenticationFacade;
import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.dtos.MomentResponseDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.IMomentService;
import com.factoria.moments.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class MomentsController {

    private IMomentService momentService;
    private IUserService userService;
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    public MomentsController(IMomentService momentService, IUserService userService, IAuthenticationFacade authenticationFacade) {
        this.momentService = momentService;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    private User getAutUser() {
        return userService.getById(1L);
    }

    @GetMapping("/moments")
    ResponseEntity <List<MomentResponseDto>> getAll() {
        var moments = momentService.getAll();
        return new ResponseEntity<>(moments, HttpStatus.OK);
    }

    @GetMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> getById(@PathVariable Long id) {
        MomentResponseDto moment = momentService.getById(id);
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
   @PostMapping("/moments")
   ResponseEntity<MomentResponseDto> create(@RequestBody MomentRequestDto newMoment){
        var authUser = authenticationFacade.getAuthUser(); // canviar por facade
        MomentResponseDto moment = momentService.create(newMoment,authUser.get());
        return new ResponseEntity<>(moment,HttpStatus.OK);

   }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/moments/{id}")
    ResponseEntity<MomentResponseDto> update(@RequestBody MomentRequestDto update, @PathVariable Long id){
        var authUser = authenticationFacade.getAuthUser();
        MomentResponseDto moment = momentService.update(update, id, authUser.get());
        return new ResponseEntity<>(moment,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/moments/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id){
        var authUser = authenticationFacade.getAuthUser();
        var moment = momentService.delete(id,authUser.get());
        return new ResponseEntity<>(moment, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value="/moments", params="search")
    ResponseEntity<List<MomentResponseDto>> getBySearch(@RequestParam String search){
        var authUser = authenticationFacade.getAuthUser();
        var searched = momentService.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(search, authUser.get());
        return new ResponseEntity<>(searched, HttpStatus.OK);
    }


}







