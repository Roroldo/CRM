# SSM 项目：CRM 管理系统（访问地址：http://8.131.69.234:8080/crm/ )

> 超级管理员（拥有系统的所有权限）：
>
> * 账号：admin 
> * 密码：admin
>
> 普通用户（拥有订单查询、产品增改查权限）：
>
> * 账号：roroldo
> * 密码：123456

## 一、项目介绍

![image-20210917154752168](https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210917154752168.png)

CRM 客户权限管理系统，模拟一个旅游平台后端系统的基本业务实现。实现了对系统的基础数据管理：

* 产品管理和订单管理。

在系统管理中，有

* 用户管理

* 角色管理

* 资源权限管理

* 系统的访问日志管理。

项目的目的主要是练习 SSM 框架的综合使用，对权限控制框架 SpringSecurity 的入门。





## 二、技术选型

前端框架：AdminLTE 

后端：Spring + Spring MVC + Mybatis + SpringSecurity

数据库：MySQL









## 三、项目总结

* 学习 SSM 框架的整合与使用，maven 多模块建立工程
* Spring AOP 技术记录用户访问日志
* 采取 Spring 的事务管理，确保用户操作与数据库表的一致性
* Spring Security 框架自定义登陆页面与配置错误页面
* Spring Security 用户数据加密与权限管理







