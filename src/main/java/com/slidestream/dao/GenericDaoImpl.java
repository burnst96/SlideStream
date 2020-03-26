//package com.slidestream.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class GenericDaoImpl<T, D> implements GenericDao<T, D> {
//
//	@Autowired
//	private GenericDao<T, D> dao;
//
//	@Autowired
//	private EntityManager entityManager;
//
//	@Override
//	public T findTopByOrderByPkDesc() {
//		return dao.findTopByOrderByPkDesc();
//	}
//
//	@Override
//	public List<T> findAll() {
//		return dao.findAll();
//	}
//
//	@Override
//	public List<T> findAll(Sort sort) {
//		return dao.findAll(sort);
//	}
//
//	@Override
//	public Page<T> findAll(Pageable pageable) {
//		return dao.findAll(pageable);
//	}
//
//	@Override
//	public List<T> findAllById(Iterable<D> ds) {
//		return dao.findAllById(ds);
//	}
//
//	@Override
//	public long count() {
//		return dao.count();
//	}
//
//	@Override
//	public void deleteById(D d) {
//		dao.deleteById(d);
//	}
//
//	@Override
//	public void delete(T entity) {
//		dao.delete(entity);
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends T> entities) {
//		dao.deleteAll(entities);
//	}
//
//	@Override
//	public void deleteAll() {
//		dao.deleteAll();
//	}
//
//	@Override
//	public <S extends T> S save(S entity) {
//		return dao.save(entity);
//	}
//
//	@Override
//	public <S extends T> List<S> saveAll(Iterable<S> entities) {
//		return dao.saveAll(entities);
//	}
//
//	@Override
//	public Optional<T> findById(D d) {
//		return dao.findById(d);
//	}
//
//	@Override
//	public boolean existsById(D d) {
//		return dao.existsById(d);
//	}
//
//	@Override
//	public void flush() {
//		dao.flush();
//	}
//
//	@Override
//	public <S extends T> S saveAndFlush(S entity) {
//		return dao.saveAndFlush(entity);
//	}
//
//	@Override
//	public void deleteInBatch(Iterable<T> entities) {
//		dao.deleteInBatch(entities);
//	}
//
//	@Override
//	public void deleteAllInBatch() {
//		dao.deleteAllInBatch();
//	}
//
//	@Override
//	public T getOne(D d) {
//		return dao.getOne(d);
//	}
//
//	@Override
//	public <S extends T> Optional<S> findOne(Example<S> example) {
//		return dao.findOne(example);
//	}
//
//	@Override
//	public <S extends T> List<S> findAll(Example<S> example) {
//		return dao.findAll(example);
//	}
//
//	@Override
//	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
//		return dao.findAll(example, sort);
//	}
//
//	@Override
//	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
//		return dao.findAll(example, pageable);
//	}
//
//	@Override
//	public <S extends T> long count(Example<S> example) {
//		return dao.count(example);
//	}
//
//	@Override
//	public <S extends T> boolean exists(Example<S> example) {
//		return dao.exists(example);
//	}
//}
