package com.slidestream.service.implementations;

import com.slidestream.dao.GroupingDao;
import com.slidestream.dao.ImageDao;
import com.slidestream.domain.Configuration;
import com.slidestream.domain.Grouping;
import com.slidestream.service.interfaces.GroupingService;
import com.slidestream.service.interfaces.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class GroupingServiceImpl extends GenericServiceAbstract<Grouping> implements GroupingService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GroupingDao dao;

    @Resource
    private ImageDao imageDao;

    @Resource
    private ImageService imageService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public List<Grouping> getAllFetchImages() {
        return dao.findAllFetchImages();
    }

    @Override
    public List<String> getAllNames() {
        return dao.findAllNames();
    }

    @Override
    public Grouping createGroup() {
        return save(new Grouping(count()));
    }

    @Override
    public void addImageToGroup(Long groupId, Long imageId) {
        findById(groupId).ifPresent(g -> imageDao.findById(imageId).ifPresent(g::addImage));
    }

    @Override
    public void removeImageFromGroup(Long groupId, Long imageId) {
        findById(groupId).ifPresent(g -> g.removeImageByImagePk(imageId));
    }

    @Async
    @Override
    public CompletableFuture<Long> sendImageDetailsAsynchronously(Grouping group, String streamChannel) {
        Configuration config = group.getConfiguration();
        int delay = config.getImageCycleDelay();

        simpMessagingTemplate.convertAndSend(streamChannel + group.getPk(), imageService.getAsJsonString(group.getNextImage()));

        try {
            LOGGER.info("SLEEPING GROUP ({}) WEB SOCKET THREAD FOR {}ms", group.getName(), delay);
            Thread.sleep(delay*1000);
        } catch(InterruptedException e) {
            LOGGER.error("Could not sleep group ({}) web socket thread... continuing as normal.", group.getName());
        }

        return CompletableFuture.completedFuture(group.getPk());
    }

    @Override
    public String getAllAsJsonString() {
        return getAsJsonString(dao.findAll());
    }

    @Override
    public Grouping getLatestGrouping() {
        return dao.findTopByOrderByPkDesc();
    }

    @Override
    public List<Grouping> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Grouping> findAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public List<Grouping> findAllById(Iterable<Long> longs) {
        return dao.findAllById(longs);
    }

    @Override
    public <S extends Grouping> List<S> saveAll(Iterable<S> entities) {
        return dao.saveAll(entities);
    }

    @Override
    public void flush() {
        dao.flush();
    }

    @Override
    public <S extends Grouping> S saveAndFlush(S entity) {
        return dao.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<Grouping> entities) {
        dao.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        dao.deleteAllInBatch();
    }

    @Override
    public Grouping getOne(Long aLong) {
        return dao.getOne(aLong);
    }

    @Override
    public <S extends Grouping> List<S> findAll(Example<S> example) {
        return dao.findAll(example);
    }

    @Override
    public <S extends Grouping> List<S> findAll(Example<S> example, Sort sort) {
        return dao.findAll(example, sort);
    }

    @Override
    public Page<Grouping> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public <S extends Grouping> S save(S entity) {
        return dao.save(entity);
    }

    @Override
    public Optional<Grouping> findById(Long aLong) {
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
    public void delete(Grouping entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Grouping> entities) {
        dao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public <S extends Grouping> Optional<S> findOne(Example<S> example) {
        return dao.findOne(example);
    }

    @Override
    public <S extends Grouping> Page<S> findAll(Example<S> example, Pageable pageable) {
        return dao.findAll(example, pageable);
    }

    @Override
    public <S extends Grouping> long count(Example<S> example) {
        return dao.count(example);
    }

    @Override
    public <S extends Grouping> boolean exists(Example<S> example) {
        return dao.exists(example);
    }
    
}
