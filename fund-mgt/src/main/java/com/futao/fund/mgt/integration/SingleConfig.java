// package com.futao.fund.mgt.integration;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.integration.dsl.IntegrationFlow;
// import org.springframework.integration.dsl.IntegrationFlows;
// import org.springframework.integration.dsl.MessageChannels;
// import org.springframework.integration.file.dsl.Files;
// import org.springframework.integration.file.support.FileExistsMode;
// import org.springframework.integration.router.AbstractMessageRouter;
// import org.springframework.messaging.Message;
// import org.springframework.messaging.MessageChannel;
//
// import java.io.File;
// import java.util.Collection;
// import java.util.Collections;
//
// /**
//  * @author futaosmile@gmail.com
//  * @date 2022/5/25
//  */
// @Configuration
// public class SingleConfig {
//
//     @Bean
//     public IntegrationFlow fileWriterFlow() {
//         return IntegrationFlows
//                 // 消息来源channel
//                 .from(FileWriterGateway.class)
//                 // 消息过滤，哪些消息需要投递，哪些消息不需要
//                 .<String>filter(x -> x.length() <= 10)
//                 // 消息格式转换
//                 .<String, String>transform(String::toUpperCase)
//                 // 目标channel
//                 .channel(MessageChannels.publishSubscribe(FileCons.IN_CHANNEL_NAME))
//                 // 消息拆分，如将订单拆分为账单和物流信息，随后route可根据当前消息是账单还是物流信息，将消息路由到不同的channel
//                 // .split()
//                 // 消息路由到不同的目标channel
//                 .route(new AbstractMessageRouter() {
//                     @Override
//                     protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
//                         String payload = (String) message.getPayload();
//                         if (payload.length() <= 5) {
//                             return Collections.singleton(MessageChannels.direct(FileCons.OUT_CHANNEL_NAME_SHORT).get());
//                         } else {
//                             return Collections.singleton(MessageChannels.direct(FileCons.OUT_CHANNEL_NAME_LONG).get());
//                         }
//                     }
//                 })
//
//                 // 激活服务器。目标channel对message的处理。可以将处理结果返回给目标channel?
//                 .handle(Files.outboundAdapter(new File("tmp/files2/t.txt"))
//                         .fileExistsMode(FileExistsMode.REPLACE)
//                         .appendNewLine(true)
//                 )
//                 .get();
//     }
// }
