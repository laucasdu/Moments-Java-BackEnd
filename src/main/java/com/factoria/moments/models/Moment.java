package com.factoria.moments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Comment> commentsList = new ArrayList<>();

    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }
    @JsonSerialize
    public int commentCount(){
        return this.commentsList.size();
    }

    //LIKE
    @OneToMany(mappedBy = "moment" )
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Like> likes = new ArrayList<>();


    public void addLike(Like like){
        if(!like.getMoment().equals(this)) return; // CLAUSULA DE SALVAGUARDA
        likes.add(like);
    }
    @JsonSerialize
    public int likesCount() {
        return likes.size();
    }

    // Utilitzar equals compara només l'atribut (User), en canvi si posem == compara la memòria real.
    @JsonSerialize
    public boolean checkIfLiked(User lover) {
        var likeLover = likes.stream().filter(Like -> Like.getLover() == (lover)).findFirst();
        if (likeLover.isEmpty()) return false; // si està buit false
        return true;
    }


}
