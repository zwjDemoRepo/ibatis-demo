# ibatis-demo
 Ibatis是开源的持久层框架。
它的核心是SqlMap，将实体Bean跟关系数据库进行映射，将业务代码和SQL语句的书写进行分开，方便管理。
Ibatis是“半自动”的ORM持久层框架。
这里的“半自动化”，是相对Hibernate等提供了全面的数据库封装机制的“全自动化”ORM 实现而言，
“全自动”ORM 实现了 POJO 和数据库表之间的映射，以及 SQL 的自动生成和执行。而iBATIS 的着力点，
则在于POJO 与 SQL之间的映射关系。也就是说，iBATIS并不会为程序员在运行期自动生成 SQL 执行。
具体的 SQL 需要程序员编写，然后通过映射配置文件，将SQL所需的参数，以及返回的结果字段映射到指定 POJO。

1.由于ibatis依赖包在阿里云仓库找不到，我在网上下载了一个，需要安装到本地仓库，安装命令，
mvn install:install-file -DgroupId=com.ibatis -DartifactId=ibatis -Dversion=1.0.0 -Dpackaging=jar -Dfile=ibatis-2.3.0.677.jar
2.数据库配置
添加后缀：allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8，防止中文乱码

3.测试目的:找到一种高并发下，插入每条记录并正确返回插入记录的ID


update:
1.替换jar包：ibatis-sqlmap，能拉到依赖

