package com.slidestream.service.interfaces;

import com.slidestream.domain.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService extends GenericService<Image> {

    List<Image> getAll();

    ResponseEntity saveImage(MultipartFile image);

}
