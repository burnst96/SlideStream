package com.slidestream.controller;

import com.slidestream.service.interfaces.GroupingService;
import com.slidestream.service.interfaces.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping
@EnableScheduling
public class SlideController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private ImageService imageService;

    @Resource
    private GroupingService groupingService;

    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView("slide");
        modelAndView.addObject("pageTitle", "SlideStream");
        modelAndView.addObject("images", imageService.findAll());
        modelAndView.addObject("groupNames", groupingService.getAllNames());
        return modelAndView;
    }

//    @Scheduled(fixedRate = 1000)
//    public void sendGroupImagesToListeners() {
//        logger.info(LocalDateTime.now().toString() + " - RUNNING sendGroupImagesToListeners()");
//
//        List<Grouping> allGroups = groupingService.findAll();
//        for(Grouping group : allGroups) {
//            simpMessagingTemplate.convertAndSend("/socket/group" + group.getPk(), group.getImages());
//        }
//    }

}
