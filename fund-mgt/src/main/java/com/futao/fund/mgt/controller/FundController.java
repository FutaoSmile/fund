package com.futao.fund.mgt.controller;

import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.api.dto.query.FundQueryDTO;
import com.futao.fund.mgt.ao.FundAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/repository-search")
    public FundDTO repositorySearch(@RequestParam("fundCode") String fundCode) {
        return fundAo.repositorySearch(fundCode);
    }

}
