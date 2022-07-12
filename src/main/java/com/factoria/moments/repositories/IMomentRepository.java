package com.factoria.moments.repositories;

import com.factoria.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMomentRepository extends JpaRepository<Moment,Long> {

    @Query("select m from Moment m " +
            "where upper(m.description) like upper(concat('%', :search, '%')) or upper(m.title) like upper(concat('%', :search, '%'))")
    List<Moment> findByDescriptionContainsIgnoreCaseOrTitleContainsIgnoreCase(@Param("search") String search);

    Optional<Moment> findById(Long id);


}
