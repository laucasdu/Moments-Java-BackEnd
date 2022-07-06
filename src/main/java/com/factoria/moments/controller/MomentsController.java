package com.factoria.moments.controller;

import com.factoria.moments.models.Moment;
import com.factoria.moments.repositories.IMomentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class MomentsController<newMoment> {

    private IMomentRepository momentsRepository;

    public MomentsController(IMomentRepository momentsRepository) {
        this.momentsRepository = momentsRepository;
    }

    @GetMapping("/moments")
    List<Moment> getAll() {
        var momentList = this.momentsRepository.findAll();
        return momentList;
    }


    @GetMapping("/moments/{id}")
    Moment getById(@PathVariable Long id) {
        Moment moment = this.momentsRepository.findById(id).get();
        return moment;

    }

    @PostMapping("/moments")
    Moment createMoment(@RequestBody Moment newMoment){
        var moment = momentsRepository.save(newMoment);
        return moment;

    }

    @PutMapping("/moments/{id}")
    Moment updateMoment(@PathVariable Long id, @RequestBody Moment updateMoment) {
        var moment = momentsRepository.findById(id).get();
        moment.setImgUrl(updateMoment.getImgUrl());
        moment.setTitle(updateMoment.getTitle());
        moment.setDescription(updateMoment.getTitle());
        var dbMoment= momentsRepository.save(moment);
        return dbMoment;
    }
    @DeleteMapping("/moments/{id}")
    boolean deleteMoment(@PathVariable Long id){
        Moment moment = this.momentsRepository.findById(id).get();
        this.momentsRepository.delete(moment);
        return true;
    }


    @GetMapping(value="/moments", params="search")
    List<Moment> getMomentSearch(@RequestParam String search) {
        return momentsRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(search, search);

    }



}







