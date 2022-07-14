package com.factoria.moments.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MomentTest {

    @Test
    void shouldHasAnCommentsCounter(){
        //GIVEN
        var moment = new Moment();
        var comment = new Comment();
        moment.addComment(comment);

        //SYSTEM UNDER TEST
        var sut = moment.commentCount();

        //WHEN

        //THEN
        assertThat(sut,equalTo(1));
    }

}