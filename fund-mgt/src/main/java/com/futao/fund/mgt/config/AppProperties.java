package com.futao.fund.mgt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@ConfigurationProperties(prefix = "app")
public class AppProperties {


    @NestedConfigurationProperty
    private Dubbo dubbo = new Dubbo();

    public Dubbo getDubbo() {
        return dubbo;
    }

    public void setDubbo(Dubbo dubbo) {
        this.dubbo = dubbo;
    }

    public static class Dubbo {
        private String serviceVersion;

        public String getServiceVersion() {
            return serviceVersion;
        }

        public void setServiceVersion(String serviceVersion) {
            this.serviceVersion = serviceVersion;
        }
    }
}
