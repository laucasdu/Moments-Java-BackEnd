package com.factoria.moments.services;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentServiceTest {

    @Mock
    IMomentRepository momentRepository;

    // FUNCIÓ PER CREAR UN MOMENT PER ÚS DELS TESTS
    private Moment createMoment(){
        var creator = new User();
        creator.setId(1L);
        var moment = new Moment();
        moment.setTitle("title");
        moment.setImgUrl("image");
        moment.setDescription("description");
        moment.setCreator(creator);
        return moment;
    }

    @Test
     void getAllReturnsAListOfMoment() {
        //GIVEN
        var momentService = new MomentService(momentRepository);
        var momentList = List.of(new Moment(), new Moment());
        Mockito.when(momentRepository.findAll()).thenReturn(momentList);

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        var sut = momentService.getAll();
        assertThat(sut.size(), equalTo(2));
    }

    @Test
    void findByIdReturnsAnMoment() {
        //GIVEN
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment();

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        // sut és el nom del test abans del should
        var sut = momentService.findById(1L);

        //THEN
        assertThat(sut.getId(), equalTo(moment.getId()));
    }

    @Test
    void createShouldSaveAMomentFromRequestDTO() {

    }


    @Test
    void update() {

    }


    @Test
    void deleteByIdShouldDeleteAMomentById() {
        //GIVEN
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment(); // si no tens això cal que cada vegada creis el moment i el user

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        var sut = momentService.delete(1L);

        //THEN
        assertThat(sut, equalTo(true));
        //assertThat(sut, equalTo(false));

    }


    @Test
    void findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase() {
        //GIVEN
        var momentService = new MomentService(momentRepository);
        var momentList = List.of(new Moment());

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        Mockito.when(momentRepository.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase("search")).thenReturn(momentList);

        var sut = momentService.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase("search");

        //THEN
        assertThat(sut, equalTo(momentList));
    }


}