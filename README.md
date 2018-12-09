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

###update:
1.替换jar包：ibatis-sqlmap，能拉到依赖

2.DDL

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `enabled` varchar(45) NOT NULL,
  `pid` varchar(45) DEFAULT NULL,
  `ptype` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `partion_kay` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PID_PTYPE_SOURCE` (`name`,`ptype`,`source`,`pid`),
  KEY `IDX_UPT` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

###ibatis,sqlmap,namespace
如何启用namespace？
配置方式:
在sqlmap-config.xml文件中增加如下配置项
<setting useStatementNamespaces="true"/>
在xxxxx-sqlmap.xml文件中增加如下配置即可
<sqlMap namespace="xxxxxx">

遇到的问题？
使用namespace后：
<sqlMap namespace="hivestore">
<select id="insert" ........
<select id="column.insert".
方法一：.getSqlMapClientTemplate().insert("hivestore.insert", hivestore);   运行成功
方法二：this.getSqlMapClientTemplate().insert("hivestore.column.insert", hivestoreColumn);  运行出错
为什么我启用了namespce后，第二个方法却报名了名为hivestore.column.insert的sqlstatement找不到？

细节点：关于方法二为什么报错的原因
首先ibatis启动加载配置文件：与报错相关部分代码如下
1.SqlMapParse.java                                                                                                                                                                                                                                                                                            
parser.addNodelet("/sqlMap/select", new Nodelet() {
      public void process(Node node) throws Exception {
        statementParser.parseGeneralStatement(node, new SelectStatement());
      }
    });

2.SqlMapStatementParser.java
相关方法：
public void parseGeneralStatement(Node node, MappedStatement statement){}
关键行：
if (state.isUseStatementNamespaces()) {
   id = state.applyNamespace(id);
}
默认值：
private boolean useStatementNamespaces = false;

3.细节点：
  public String applyNamespace(String id) {
    String newId = id;
    if (namespace != null && namespace.length() > 0 && id != null && id.indexOf('.') < 0) {
      newId = namespace + "." + id;
    }
    return newId;
  }
indexOf导致了方法二出错。