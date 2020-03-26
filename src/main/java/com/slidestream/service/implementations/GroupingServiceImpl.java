package com.slidestream.service.implementations;

import com.slidestream.dao.GroupingDao;
import com.slidestream.dao.ImageDao;
import com.slidestream.domain.Configuration;
import com.slidestream.domain.Grouping;
import com.slidestream.domain.dto.GroupingDTO;
import com.slidestream.domain.dto.ImageDTO;
import com.slidestream.service.JsonService;
import com.slidestream.service.interfaces.GroupingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupingServiceImpl extends GenericServiceAbstract<Grouping> implements GroupingService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GroupingDao dao;

    @Resource
    private ImageDao imageDao;

    @Resource
    private JsonService jsonService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public List<Grouping> getAllFetchImages() {
        List<Object[]> idsAndImageIndexes = getAllIdsAndCurrentImageIndexes();
        List<Grouping> results = dao.findAll();

        // TODO: Workaround for dao.findAll() resetting the image_index on all groupings for some reason
        for (Grouping g : results) {
            for (Object[] idAndIndex : idsAndImageIndexes) {
                if (g.getPk() == (long) idAndIndex[0]) {
                    g.setCurrentImageIndex((int) idAndIndex[1]);
                    break;
                }
            }
        }

        return results;
    }

    @Override
    public List<Object[]> getAllIdsAndCurrentImageIndexes() {
        return dao.getAllIdsAndCurrentImageIndexes();
    }

    @Override
    public List<Integer> getAllIds() {
        return dao.findAllIds();
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

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void incrementImageIndex(Long groupId, int imageIndex) {
        findById(groupId).ifPresent(g -> g.setCurrentImageIndexOverride(imageIndex));
    }

    @Override
    public void removeImageFromGroup(Long groupId, Long imageId) {
        findById(groupId).ifPresent(g -> g.removeImageByImagePk(imageId));
    }

    @Async
    @Transactional(propagation = Propagation.NEVER)
    @Override
    public CompletableFuture<Long> sendImageDetailsAsynchronously(Grouping group, String streamChannel) {
        Configuration config = group.getConfiguration();
        int delay = config.getImageCycleDelay();

        simpMessagingTemplate.convertAndSend(streamChannel + group.getPk(), jsonService.convertToJsonString(new ImageDTO(group.getNextImage())));

        try {
            LOGGER.info("SLEEPING GROUP ({}) WEB SOCKET THREAD FOR {}s", group.getName(), delay);
            Thread.sleep(delay * 1000);
        } catch(InterruptedException e) {
            LOGGER.error("Could not sleep group ({}) web socket thread... continuing as normal.", group.getName());
        }

        return CompletableFuture.completedFuture(group.getPk());
    }

    @Override
    public String getAllAsJsonString() {
        return jsonService.convertToJsonString(dao.findAll().stream().map(GroupingDTO::new).collect(Collectors.toList()));
    }

    @Override
    public Grouping getLatestConfiguration() {
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
