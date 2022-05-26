// package com.futao.fund.mgt.integration;
//
// import org.springframework.integration.annotation.Gateway;
// import org.springframework.integration.annotation.MessagingGateway;
// import org.springframework.messaging.Message;
// import org.springframework.messaging.handler.annotation.Header;
// import org.springframework.stereotype.Component;
//
// /**
//  * @author futaosmile@gmail.com
//  * @date 2022/5/25
//  */
// @Component
// @MessagingGateway(defaultRequestChannel = FileCons.IN_CHANNEL_NAME)
// public interface FileWriterGateway {
//     @Gateway
//     void writeToFile(Message<?> message);
// }
