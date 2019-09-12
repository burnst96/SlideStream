package com.slidestream.service.implementations;

import com.slidestream.dao.ImageDao;
import com.slidestream.domain.Image;
import com.slidestream.service.interfaces.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageServiceImpl extends GenericServiceAbstract<Image> implements ImageService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ImageDao dao;

    @Override
    public List<Image> getAll() {
        return dao.findAll();
    }

    @Override
    public ResponseEntity<HttpStatus> saveImage(MultipartFile imageFile) {
        try {
            Image imageForUpload = new Image(imageFile.getOriginalFilename(), imageFile.getBytes());
            dao.save(imageForUpload);
        } catch(IOException e) {
            LOGGER.error("Failed to extract bytes from image file: " + imageFile.getName());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public String getAllAsJsonString() {
        return getAsJsonString(dao.findAll());
    }

    @Override
    public Image getLatestGrouping() {
        return dao.findTopByOrderByPkDesc();
    }

    @Override
    public List<Image> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Image> findAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public List<Image> findAllById(Iterable<Long> longs) {
        return dao.findAllById(longs);
    }

    @Override
    public <S extends Image> List<S> saveAll(Iterable<S> entities) {
        return dao.saveAll(entities);
    }

    @Override
    public void flush() {
        dao.flush();
    }

    @Override
    public <S extends Image> S saveAndFlush(S entity) {
        return dao.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<Image> entities) {
        dao.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        dao.deleteAllInBatch();
    }

    @Override
    public Image getOne(Long aLong) {
        return dao.getOne(aLong);
    }

    @Override
    public <S extends Image> List<S> findAll(Example<S> example) {
        return dao.findAll(example);
    }

    @Override
    public <S extends Image> List<S> findAll(Example<S> example, Sort sort) {
        return dao.findAll(example, sort);
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public <S extends Image> S save(S entity) {
        return dao.save(entity);
    }

    @Override
    public Optional<Image> findById(Long aLong) {
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
    public void delete(Image entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Image> entities) {
        dao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public <S extends Image> Optional<S> findOne(Example<S> example) {
        return dao.findOne(example);
    }

    @Override
    public <S extends Image> Page<S> findAll(Example<S> example, Pageable pageable) {
        return dao.findAll(example, pageable);
    }

    @Override
    public <S extends Image> long count(Example<S> example) {
        return dao.count(example);
    }

    @Override
    public <S extends Image> boolean exists(Example<S> example) {
        return dao.exists(example);
    }
}
