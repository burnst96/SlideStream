package com.slidestream.service.implementations;

import com.slidestream.dao.ConfigurationDao;
import com.slidestream.domain.Configuration;
import com.slidestream.service.interfaces.ConfigurationService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConfigurationServiceImpl extends GenericServiceAbstract<Configuration> implements ConfigurationService {

    @Resource
    private ConfigurationDao dao;

    @Override
    public String getAllAsJsonString() {
        return getAsJsonString(dao.findAll());
    }

    @Override
    public Configuration getLatestGrouping() {
        return dao.findTopByOrderByPkDesc();
    }

    @Override
    public List<Configuration> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Configuration> findAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public List<Configuration> findAllById(Iterable<Long> longs) {
        return dao.findAllById(longs);
    }

    @Override
    public <S extends Configuration> List<S> saveAll(Iterable<S> entities) {
        return dao.saveAll(entities);
    }

    @Override
    public void flush() {
        dao.flush();
    }

    @Override
    public <S extends Configuration> S saveAndFlush(S entity) {
        return dao.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<Configuration> entities) {
        dao.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        dao.deleteAllInBatch();
    }

    @Override
    public Configuration getOne(Long aLong) {
        return dao.getOne(aLong);
    }

    @Override
    public <S extends Configuration> List<S> findAll(Example<S> example) {
        return dao.findAll(example);
    }

    @Override
    public <S extends Configuration> List<S> findAll(Example<S> example, Sort sort) {
        return dao.findAll(example, sort);
    }

    @Override
    public Page<Configuration> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public <S extends Configuration> S save(S entity) {
        return dao.save(entity);
    }

    @Override
    public Optional<Configuration> findById(Long aLong) {
        return dao.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return dao.existsById(aLong);
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public void deleteById(Long aLong) {
        dao.deleteById(aLong);
    }

    @Override
    public void delete(Configuration entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Configuration> entities) {
        dao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public <S extends Configuration> Optional<S> findOne(Example<S> example) {
        return dao.findOne(example);
    }

    @Override
    public <S extends Configuration> Page<S> findAll(Example<S> example, Pageable pageable) {
        return dao.findAll(example, pageable);
    }

    @Override
    public <S extends Configuration> long count(Example<S> example) {
        return dao.count(example);
    }

    @Override
    public <S extends Configuration> boolean exists(Example<S> example) {
        return dao.exists(example);
    }
    
}
