// package com.futao.fund.mgt.integration;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.integration.annotation.ServiceActivator;
// import org.springframework.integration.annotation.Transformer;
// import org.springframework.integration.channel.DirectChannel;
// import org.springframework.integration.file.FileWritingMessageHandler;
// import org.springframework.integration.file.support.FileExistsMode;
// import org.springframework.integration.transformer.GenericTransformer;
// import org.springframework.messaging.MessageChannel;
//
// import java.io.File;
//
// /**
//  * @author futaosmile@gmail.com
//  * @date 2022/5/25
//  */
// @Configuration
// public class Config {
//
//     @Bean
//     @Transformer(inputChannel = FileCons.IN_CHANNEL_NAME, outputChannel = FileCons.OUT_CHANNEL_NAME)
//     public GenericTransformer<String, String> upperCaseTransform() {
//         return String::toUpperCase;
//     }
//
//     @Bean
//     @ServiceActivator(inputChannel = FileCons.OUT_CHANNEL_NAME)
//     public FileWritingMessageHandler fileWriter() {
//         FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File("tmp/files"));
//         fileWritingMessageHandler.setExpectReply(false);
//         fileWritingMessageHandler.setFileExistsMode(FileExistsMode.REPLACE);
//         fileWritingMessageHandler.setAppendNewLine(true);
//         return fileWritingMessageHandler;
//     }
//
//
//     @Bean(FileCons.IN_CHANNEL_NAME)
//     public MessageChannel textInChannel() {
//         return new DirectChannel();
//     }
//
//     @Bean(FileCons.OUT_CHANNEL_NAME)
//     public MessageChannel fileWriterChannel() {
//         return new DirectChannel();
//     }
// }
