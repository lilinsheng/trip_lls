package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.service.IFereContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fereContent")
public class FereContentController {

    @Autowired
    private IFereContentService fereContentService;




}
