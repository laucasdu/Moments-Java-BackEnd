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

        //THEN
        assertThat(sut,equalTo(1));
        //WHEN
    }

    @Test
    void shouldHavenALikesCounter(){
        //GIVEN
        var moment = new Moment();
        var user = new User();
        moment.setId(1L);
        user.setId(1L);
        var like = new Like(user,moment);
        moment.addLike(like);
        int sut = moment.likesCount();

        assertThat(sut, equalTo(1));
        //assertThat(sut, equalTo(0)); // falla

    }


    @Test
    void momentShouldLetAddLikeIfMomentDoesNotMatch(){
        var moment1 = new Moment();
        var moment2 = new Moment();
        var user = new User();
        moment1.setId(1L);
        moment2.setId(2L);
        user.setId(1L);
        var like = new Like(user,moment1);
        moment2.addLike(like);

        var sut = moment2.likesCount();

        assertThat(sut, equalTo(0));
        //assertThat(sut, equalTo(1)); // falla


    }


    @Test
    void momentShouldKnowIfUserLikesIt(){
        var moment = new Moment();
        var lover = new User();
        var like = new Like(lover, moment);
        moment.addLike(like);

        var sut = moment.isLiked(lover);

        assertThat(sut, equalTo(true));
        //assertThat(sut, equalTo(false)); falla


    }

    @Test
    void momentShouldKnowIfUserDoesNotLikesIt(){
        var moment = new Moment();
        var lover = new User();
        var notLover = new User();
        var like = new Like(lover, moment);
        moment.addLike(like);

        var sut = moment.isLiked(notLover);

        assertThat(sut, equalTo(false));
        //assertThat(sut, equalTo(true)); falla

    }



}