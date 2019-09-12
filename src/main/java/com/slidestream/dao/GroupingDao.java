package com.slidestream.dao;

import com.slidestream.domain.Grouping;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupingDao extends GenericDao<Grouping, Long> {

    @Query(value = "select g.name from Grouping g")
    List<String> findAllNames();

    @Query(value = "select g from Grouping g LEFT JOIN FETCH g.images")
    List<Grouping> findAllFetchImages();

}
