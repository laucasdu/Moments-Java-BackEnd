package com.factoria.moments.repositories;

import com.factoria.moments.models.Moment;
import org.springframework.stereotype.Repository;

import java.util.List;

public class FakeMomentsRepository {

    private List<Moment> getMomentList() {
        Moment moment1 = new Moment("paris", "vacances 2010", "", 1L);
        Moment moment2 = new Moment("berlin", "vacances 2011", "", 2L);
        Moment moment3 = new Moment("londres", "vacances 2012", "", 3L);

        var momentList = List.of(moment1, moment2,moment3);
        return momentList;
    }

    public List<Moment> findAll(){
        return getMomentList();
    }

    public Moment findById(Long id) {
        var momentList = this.getMomentList();
        var moment = momentList.stream()
                .filter(item -> item.getId() == id).findFirst().get();
        return moment;


    }

}
