package com.slidestream.service;

import com.slidestream.domain.Grouping;
import com.slidestream.service.interfaces.GroupingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ImageSocketOutputService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String STREAM_CHANNEL = "/socket/images/";
    private Map<Long, LocalDateTime> groupsLastModifiedDateTimes = new HashMap<>();
    private Map<Long, CompletableFuture<Long>> currentlyRunningAsynchronousJobs = new HashMap<>();

    @Resource
    private GroupingService groupingService;

    @Scheduled(cron = "*/1 * * * * *")
    protected void groupWebSocketLoop() {
        LOGGER.info("MASTER WEB SOCKET LOOP START");

        List<Grouping> groups = groupingService.getAllFetchImages();
        for(Grouping group : groups) {
            if(!groupsLastModifiedDateTimes.containsKey(group.getPk()) && currentlyRunningAsynchronousJobs.containsKey(group.getPk())) {
                groupsLastModifiedDateTimes.put(group.getPk(), group.getLastModified());
                currentlyRunningAsynchronousJobs.put(group.getPk(), groupingService.sendImageDetailsAsynchronously(group, STREAM_CHANNEL));
            } else {
                CompletableFuture<Long> asyncSendImagesInstance = currentlyRunningAsynchronousJobs.get(group.getPk());
                if(asyncSendImagesInstance == null || asyncSendImagesInstance.isDone()) {
                    currentlyRunningAsynchronousJobs.put(group.getPk(), groupingService.sendImageDetailsAsynchronously(group, STREAM_CHANNEL));
                }
            }
        }

        if(!groups.isEmpty()) {
            currentlyRunningAsynchronousJobs = currentlyRunningAsynchronousJobs.entrySet().stream()
                                                                                            .filter(entry -> groups.stream()
                                                                                            .map(Grouping::getPk).anyMatch(entry.getKey()::equals))
                                                                                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        LOGGER.info("MASTER WEB SOCKET LOOP END");
    }

}
