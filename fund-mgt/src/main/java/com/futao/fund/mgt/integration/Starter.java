// package com.futao.fund.mgt.integration;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.support.GenericMessage;
//
// /**
//  * @author futaosmile@gmail.com
//  * @date 2022/5/26
//  */
// @Slf4j
// @Configuration
// @RequiredArgsConstructor
// public class Starter {
//
//     private final FileWriterGateway fileWriterGateway;
//
//     @Bean
//     public ApplicationRunner start() {
//         return args -> {
//             log.info("writeToFile///");
//             fileWriterGateway.writeToFile(new GenericMessage<>("内容asd"));
//         };
//     }
// }
//
