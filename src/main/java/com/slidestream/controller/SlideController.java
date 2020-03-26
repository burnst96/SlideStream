package com.slidestream.controller;

import com.slidestream.service.interfaces.ConfigurationService;
import com.slidestream.service.interfaces.GroupingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping
public class SlideController extends GenericController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GroupingService groupingService;

    @Resource
    private ConfigurationService configurationService;

    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView("slide");
        modelAndView.addObject("pageTitle", "SlideStream");
        modelAndView.addObject("groups", groupingService.findAll());
        modelAndView.addObject("groupsJson", groupingService.getAllAsJsonString());
        return modelAndView;
    }

    @PostMapping
    public String updateGroupDelay(long groupPk, int delayInSeconds) {
        configurationService.updateDelay(groupPk, delayInSeconds);
        return SUCCESS;
    }

}
