package com.futao.fund.mgt.controller;

import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.api.dto.query.FundQueryDTO;
import com.futao.fund.mgt.ao.FundAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author futao
 * @date 2022/5/19
 */
@RequestMapping("/fund")
@RestController
public class FundController {

    @Autowired
    private FundAo fundAo;

    @PostMapping("/spider-fetch")
    public Boolean spiderFetch() {
        return fundAo.spiderFetch();
    }

    @GetMapping("/search")
    public List<FundDTO> search(FundQueryDTO fundQueryDto) {
        return fundAo.search(fundQueryDto);
    }

    @GetMapping("/search-by-criteria")
    public List<FundDTO> searchByCriteria() {
        return fundAo.searchByCriteria();
    }

    @GetMapping("/search-by-dsl")
    public List<FundDTO> searchByDsl() {
        return fundAo.searchByDsl();
    }

}
