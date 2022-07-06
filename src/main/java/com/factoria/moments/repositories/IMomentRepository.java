package com.factoria.moments.repositories;

import com.factoria.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IMomentRepository extends JpaRepository<Moment,Long> {
    @Query("select m from Moment m " +
            "where upper(m.title) like upper(concat('%', ?1, '%')) or upper(m.description) like upper(concat('%', ?2, '%'))")
    List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);
//
//    List<Moment> findAll();
//
    Optional<Moment> findById(Long id);


}
