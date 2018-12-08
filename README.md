# ibatis-demo
 iBatis 简介：
 
 iBatis 是apache 的一个开源项目，一个O/R Mapping 解决方案，iBatis 最大的特点就是小巧，上手很快。如果不需要太多复杂的功能，iBatis 是能够满足你的要求又足够灵活的最简单的解决方案，现在的iBatis 已经改名为Mybatis 了。
 
 官网为：http://www.mybatis.org/
 
  
 
 搭建iBatis 开发环境：
 
 1 、导入相关的jar 包，ibatis-sqlmap 、mysql-connector-java
 
 2 、编写配置文件：
 
 Jdbc 连接的属性文件
 
 总配置文件， SqlMapConfig.xml
 
 关于每个实体的映射文件（Map 文件）

1.由于ibatis依赖包在阿里云仓库找不到，我在网上下载了一个，需要安装到本地仓库，安装命令，
mvn install:install-file -DgroupId=com.ibatis -DartifactId=ibatis -Dversion=1.0.0 -Dpackaging=jar -Dfile=ibatis-2.3.0.677.jar
2.数据库配置
添加后缀：allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8，防止中文乱码

3.测试目的:找到一种高并发下，插入每条记录并正确返回插入记录的ID

iBatis 的优缺点：

优点：

1、减少代码量，简单；

2、性能增强；

3、Sql 语句与程序代码分离；

4、增强了移植性；

缺点：

1、和Hibernate 相比，sql 需要自己写；

2、参数数量只能有一个，多个参数时不太方便；

update:
1.替换jar包：ibatis-sqlmap，能拉到依赖

2.DDL
CREATE TABLE `tb1_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8

