package com.futao.fund.mgt;

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

import java.io.IOException;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Slf4j
@ComponentScan(value = {"com.futao.fund.mgt", "com.futao.fund.core"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = FundMgtApplication.MyTypeFilter.class)
})
@EnableDubbo
@EnableConfigurationProperties({AppProperties.class})
@SpringBootApplication
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
}
