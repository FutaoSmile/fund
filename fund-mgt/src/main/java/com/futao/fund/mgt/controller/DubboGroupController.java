package com.futao.fund.mgt.controller;

import com.futao.fund.api.DubboGroupService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/27
 */
@RestController
@RequestMapping("/dubbo-group")
public class DubboGroupController {


    @DubboReference(group = "g1")
    private DubboGroupService dubboGroupService1;

    @DubboReference(group = "g2")
    private DubboGroupService dubboGroupService2;

    @GetMapping
    public String dg() {
        String groupName1 = dubboGroupService1.groupName();
        String groupName2 = dubboGroupService2.groupName();
        return groupName1 + ": " + groupName2;
    }
}

