# Fund

<p align="center">
<a href="https://github.com/futaosmile"><img src="https://img.shields.io/badge/Author-futao-orange.svg"></a>
<a href="https://gitee.com/futaosmile/fund"><img src="https://gitee.com/futaosmile/fund/badge/star.svg"></a>
<a href="https://gitee.com/futaosmile/fund"><img src="https://gitee.com/futaosmile/fund/badge/fork.svg"></a>
<img src="https://img.shields.io/github/followers/futaosmile?style=social">
<img src="https://img.shields.io/github/commit-activity/w/futaosmile/fund">
<img src="https://img.shields.io/github/languages/top/futaosmile/fund">
</p>

- 项目仓库:
  - github: https://github.com/FutaoSmile/fund
  - gitee: https://gitee.com/FutaoSmile/fund

## 项目介绍

1. 爬取基金数据
2. 写入ES
3. 通过VUE前端页面展示

- 目的
  - 对相关技术做个实践

## 项目构成：

- [fund-core](./fund-core)
  - 框架相关的
- [fund-api](./fund-api)
  - api定义
- [fund-provider](./fund-provider)
  - dubbo接口实现
  - 数据库访问
- [fund-spider](./fund-spider)
  - 数据爬取
- [fund-mgt](./fund-mgt)
  - 管理后台
  - HTTP接口
  - 前端页面
- [fund-webapp](./fund-webapp)
  - 提供给C端的HTTP接口

## 涉及技术

- JDK 1.8
- maven
- htmlunit
- springboot
- dubbo
- mybatis-plus
- VUE

## 时间线

- 2022/5/19, 项目开始日期

## 常用链接

- how to use ES in
  springboot: [https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#reference](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#reference)

## 已实现案例：

### ES:

- 审计
  - 自动设置createBy、createDateTime、UpdateBy、UpdateDateTime
- 批量插入
  - Repository
  - Bulk API
- 搜索
