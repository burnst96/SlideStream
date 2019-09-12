package com.slidestream.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDao<T,D> extends JpaRepository<T,D> {

    T findTopByOrderByPkDesc();

}
