package com.futao.fund.mgt;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.futao.fund.mgt.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Slf4j
@ComponentScan(value = {"com.futao.fund.mgt", "com.futao.fund.core"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = FundMgtApplication.MyTypeFilter.class)
})
@EnableScheduling
@EnableDubbo
@EnableConfigurationProperties({AppProperties.class})
@SpringBootApplication
@RequestMapping
public class FundMgtApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundMgtApplication.class, args);
    }

    public static class MyTypeFilter implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            String className = classMetadata.getClassName();
            return className.startsWith("com.futao.fund.mgt.integration");
        }
    }

    @ResponseBody
    @PostMapping("/change-log-level")
    public String changeLogLevel(@RequestParam("level") String level) {
        LoggerContext loggerContext = new LoggerContext();
        Logger logger = loggerContext.getLogger(FundMgtApplication.class);
        Level newLevel = Level.valueOf(level);
        logger.setLevel(newLevel);
        return "set log:" + logger.getName() + " level:" + newLevel + ", success";
    }

    // @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void logTask() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        System.out.println("sout");
    }
}
