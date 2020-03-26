package com.slidestream.service.interfaces;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    String getAsJsonString(List<T> entities);

    String getAsJsonString(T entity);

    String getAllAsJsonString();

    T getLatestConfiguration();

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAllById(Iterable<Long> longs);

    <S extends T> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends T> S saveAndFlush(S entity);
    
    void deleteInBatch(Iterable<T> entities);

    void deleteAllInBatch();

    T getOne(Long aLong);

    <S extends T> List<S> findAll(Example<S> example);
    
    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    Page<T> findAll(Pageable pageable);

    <S extends T> S save(S entity);

    Optional<T> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();
    
    void deleteById(Long aLong);
    
    void delete(T entity);
    
    void deleteAll(Iterable<? extends T> entities);
    
    void deleteAll();

    <S extends T> Optional<S> findOne(Example<S> example);
    
    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);
    
    <S extends T> long count(Example<S> example);
    
    <S extends T> boolean exists(Example<S> example);

}
