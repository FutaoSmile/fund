// package com.futao.fund.mgt.controller;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.boot.autoconfigure.web.ErrorProperties;
// import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
// import org.springframework.boot.web.servlet.error.ErrorAttributes;
// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
//
// /**
//  * @author futaosmile@gmail.com
//  * @date 2022/6/9
//  */
// @Slf4j
// @RequestMapping("${server.error.path:${error.path:/error}}")
// @RestController
// public class NotFoundController implements ErrorController {
//
//     @RequestMapping
//     public String ac(HttpServletResponse response) throws IOException {
//         log.info("xxxxxx");
//         response.sendRedirect("https://baidu.com?q=xxx");
//         return "xxxx";
//     }
// }
