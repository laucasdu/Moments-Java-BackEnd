package com.factoria.moments.services;

import com.factoria.moments.models.Moment;
import com.factoria.moments.repositories.IMomentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MomentServiceTest {

    @Mock
    IMomentRepository momentRepository;


    @Test
     void getAllReturnsAListOfProducts() {
        var momentService = new MomentService(momentRepository);
        var momentList = List.of(new Moment(), new Moment());
        Mockito.when(momentRepository.findAll()).thenReturn(momentList);
        var sut = momentService.getAll();
        assertThat(sut.size(), equalTo(2));
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase() {
    }
}