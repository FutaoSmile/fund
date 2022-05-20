package com.futao.fund.mgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@Controller
public class IndexController {

    /**
     * 跳转到首页
     *
     * @return
     */
    @GetMapping({"/", "index", "home"})
    public String index() {
        return "index";
    }
}
