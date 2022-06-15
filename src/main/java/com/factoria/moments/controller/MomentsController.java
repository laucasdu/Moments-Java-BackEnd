package com.factoria.moments.controller;

import com.factoria.moments.models.Moment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MomentsController {

    private List<Moment> getMomentList() {
        Moment moment1 = new Moment("paris", "vacances 2010", "", 1L);
        Moment moment2 = new Moment("berlin", "vacances 2011", "", 2L);
        var momentList = List.of(moment1, moment2);
        return momentList;
    }


    @GetMapping("/moments")
    List<Moment> getMoments() {

        List<Moment> moments = getMomentList();
        return moments;

    }


    @GetMapping("/moments/{id}")
    Moment getById(@PathVariable Long id) {
        var moments = this.getMomentList();
        Moment moment = moments.stream()
                .filter(Moment ->Moment.getId()==id)
                .findFirst().get();
        return moment;

    }


    @GetMapping(value="/moments", params="search")
    List<Moment> getMomentSearch(@RequestParam String search){ // definir la funció getMomentSearch amb l'endpoint moments?search={search}
        var moments = this.getMomentList();
        List<Moment> momentSearch = moments.stream()
                .filter(item ->item.getTitle().contains(search) || item.getDescription().contains(search))
                .collect(Collectors.toList()); //volem introduir l'item en funció el search introduit
                return momentSearch;

    }






}






