package com.factoria.moments.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "moments")
@NoArgsConstructor
@AllArgsConstructor
public class Moment {

    private String title;
    private String imgUrl;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //Constructor

//    public Moment(String title, String description, String imgURL, Long id){
//        this.title = title;
//        this.description = description;
//        this.imgURL = imgURL;
//        this.id = id;
//    }


}
