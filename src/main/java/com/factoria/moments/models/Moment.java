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

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "moment")
    private List<Comment> commentsList = new ArrayList<>();

    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }
    @JsonSerialize
    public int commentCount(){
        return this.commentsList.size();
    }

}
