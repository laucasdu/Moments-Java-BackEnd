package com.factoria.moments.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="likes")
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Like(User lover, Moment moment) {
        this.lover = lover;
        this.moment = moment;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lover_id")
    private User lover;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "moment_id")
    private Moment moment;
}
