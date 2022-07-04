package com.factoria.moments.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "moments")
@NoArgsConstructor
@AllArgsConstructor
public class Moment {

    private String imgUrl;
    private String title;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "moment")
    private List<Comment> comments = new ArrayList<>();

    @JsonSerialize
    public int commentCount(){
        return this.comments.size();
    }

    public Moment(String imgUrl, String title, String description, Long id) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
        this.id = id;
    }
//Constructor

//    public Moment(String title, String description, String imgURL, Long id){
//        this.title = title;
//        this.description = description;
//        this.imgURL = imgURL;
//        this.id = id;
//    }


}
