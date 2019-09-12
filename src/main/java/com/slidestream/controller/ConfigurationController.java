package com.slidestream.controller;

import com.slidestream.domain.Image;
import com.slidestream.service.interfaces.ConfigurationService;
import com.slidestream.service.interfaces.GroupingService;
import com.slidestream.service.interfaces.ImageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/config")
public class ConfigurationController {

    @Resource
    private GroupingService groupingService;

    @Resource
    private ImageService imageService;

    @Resource
    private ConfigurationService configurationService;

    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView("config");
        modelAndView.addObject("pageTitle", "Configuration");
        modelAndView.addObject("images", imageService.findAll());
        modelAndView.addObject("groups", groupingService.findAll());
        modelAndView.addObject("groupingsJson", groupingService.getAllAsJsonString());
        modelAndView.addObject("configurationJson", configurationService.getAllAsJsonString());
        return modelAndView;
    }

    @GetMapping("/downloadImage/{imageId}")
    @ResponseBody
    public HttpEntity<byte[]> downloadImage(@PathVariable(name = "imageId") Long imageId) {
        Optional<Image> image = imageService.findById(imageId);

        if(image.isPresent()) {
            byte[] imageValue = image.get().getValue();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(imageValue.length);

            return new HttpEntity<>(imageValue, headers);
        } else {
            return null;
        }
    }

    @PostMapping("/uploadImages")
    @ResponseBody
    public String uploadImages(MultipartHttpServletRequest request) {
        Map<String, MultipartFile> requestFileMap = request.getFileMap();

        for(MultipartFile image : requestFileMap.values()) {
            imageService.saveImage(image);
        }

        return "success";
    }

    @GetMapping("/deleteImage/{imageId}")
    @ResponseBody
    public String deleteImage(@PathVariable(name = "imageId") Long imageId) {
        imageService.deleteById(imageId);
        return "success";
    }

    @GetMapping("/createGroup")
    @ResponseBody
    public String createGroup() {
        groupingService.createGroup();
        return "success";
    }

    @GetMapping("/deleteGroup/{groupId}")
    @ResponseBody
    public String deleteGroup(@PathVariable(name = "groupId") Long groupId) {
        groupingService.deleteById(groupId);
        return "success";
    }

    @GetMapping("/addImageToGroup/{groupId}/{imageId}")
    @ResponseBody
    public String addImageToGroup(@PathVariable(name = "groupId") Long groupId,
                                  @PathVariable(name = "imageId") Long imageId) {
        groupingService.addImageToGroup(groupId, imageId);
        return "success";
    }

    @GetMapping("/removeImageFromGroup/{groupId}/{imageId}")
    @ResponseBody
    public String removeImageFromGroup(@PathVariable(name = "groupId") Long groupId,
                                       @PathVariable(name = "imageId") Long imageId) {
        groupingService.removeImageFromGroup(groupId, imageId);
        return "success";
    }

}
