package com.futao.fund.mgt.controller;

import com.futao.fund.mgt.ao.FundAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * @date 2022/5/19
 */
@RequestMapping("/fund")
@RestController
public class FundController {

    @Autowired
    private FundAo fundAo;

    @GetMapping
    public String applicationName() {
        return fundAo.applicationName();
    }

}
