package com.factoria.moments.services;

import com.factoria.moments.dtos.MomentRequestDto;
import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentServiceTest {
    @BeforeEach
    // crea el momentService aqui perque no peti

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
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(momentRepository.findAll()).thenReturn(momentList);

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        var sut = momentService.getAll(authUser);
        assertThat(sut.size(), equalTo(2));
    }

    @Test
    void findByIdShouldReturnAMomentWithSameParamId() {
        //GIVEN
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment();
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        // sut és el nom del test abans del should
        var sut = momentService.getById(1L, authUser);

        //THEN
        assertThat(sut.getTitle(), equalTo(moment.getTitle()));
    }

    @Test
    void createShouldSaveAMomentFromRequestDTO() {
        var momentService = new MomentService(momentRepository);
        var momentRequest = new MomentRequestDto("title", "imgUrl","description", 1L);
        var moment = this.createMoment();

        Mockito.when(momentRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.create(momentRequest, moment.getCreator());

        assertThat(sut.getCreator(), equalTo(moment.getCreator()));
    }




    @Test
    void updateMomentShouldModifyAMomentFromRequestDTO() {
        var momentService = new MomentService(momentRepository);
        var momentRequest = new MomentRequestDto("title", "description", "image", 1L);
        var moment = this.createMoment();

        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        Mockito.when(momentRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.update(momentRequest, 1L, moment.getCreator()); // sut és el nom del test abans del should

        assertThat(sut.getTitle(), equalTo(momentRequest.getTitle()));
        /*assertThat(sut.getTitle(), equalTo("HelloWorld")); FAILED TEST */

    }



    @Test
    void deleteShouldReturnDeleteMoment() {

        //GIVEN
        var momentService = new MomentService(momentRepository);
        var moment = this.createMoment(); // si no tens això cal que cada vegada creis el moment i el user
        Long deletedId = 1L;

        // SYSTEM UNDER TEST(Es igual que un RESULT).
        Mockito.when(momentRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));

        var sut = momentService.delete(deletedId,moment.getCreator());
        assertThat(sut.getDescription(), equalTo(moment.getDescription()));

        //THEN
//        assertThat(sut, equalTo(true));
        //assertThat(sut, equalTo(false));

    }

    //test no funciona
    @Test
    void findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase() {
        //GIVEN
        var momentService = new MomentService(momentRepository);
        Moment moment = this.createMoment();
        var authUser = new User();
        authUser.setId(1L);
        var momentList = List.of(new Moment());
        // SYSTEM UNDER TEST(Es igual que un RESULT).
        Mockito.when(momentRepository.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase("search")).thenReturn(momentList);

        var sut = momentService.findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase("search", authUser);

        //THEN
        assertThat(sut, equalTo(momentList));
    }


}