# 使用JSP实现的计算机维修任务管理系统

## 主要架构
- JSP页面实现前端
- Servlet与Java Bean实现后端处理
- 使用MySQL存储用户数据和任务条目

## MySQL数据库表
- user表：
  ```sql
  CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
  ```
- task表：
