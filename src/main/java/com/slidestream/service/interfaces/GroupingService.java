package com.slidestream.service.interfaces;

import com.slidestream.domain.Grouping;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GroupingService extends GenericService<Grouping> {

    List<Grouping> getAllFetchImages();

    List<String> getAllNames();

    Grouping createGroup();

    void addImageToGroup(Long groupId, Long imageId);

    void removeImageFromGroup(Long groupId, Long imageId);

    CompletableFuture<Long> sendImageDetailsAsynchronously(Grouping group, String streamChannel);

}
