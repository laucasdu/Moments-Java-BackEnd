package com.factoria.moments.models;

public class Moment {

    private String title;
    private String imgURL;
    private String description;
    private Long id;


    //Constructor

    public Moment(String title, String description, String imgURL, Long id){
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
        this.id = id;
    }

    //Getters (retorna objectes)

    public String getTitle() {
        return title.toLowerCase();
    }

    public String getDescription() {
        return description.toLowerCase();
    }

    public String getImgURL() {
        return imgURL;
    }

    public Long getId() {
        return id;
    }



}
