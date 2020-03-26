package com.slidestream.dao;

import com.slidestream.domain.Grouping;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupingDao extends GenericDao<Grouping, Long> {

    @Query(value = "select g.pk from Grouping g")
    List<Integer> findAllIds();

    @Query(value = "select g.name from Grouping g")
    List<String> findAllNames();

    @EntityGraph(value = "Grouping.images", type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"images"})
    List<Grouping> findAll();

    @Query(value = "select g.id, g.currentImageIndex from Grouping g")
    List<Object[]> getAllIdsAndCurrentImageIndexes();

//    @Query("SELECT g FROM Grouping g LEFT JOIN FETCH g.images")
//    List<Grouping> findAll();

}
