package com.futao.fund.provider.service;

import com.futao.fund.api.DubboGroupService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/27
 */
@DubboService(group = "g2")
public class DubboGroup2ServiceImpl implements DubboGroupService {
    @Override
    public String groupName() {
        return "g2";
    }
}
