# ibatis-demo
1.由于ibatis依赖包在阿里云仓库找不到，我在网上下载了一个，需要安装到本地仓库，安装命令，
mvn install:install-file -DgroupId=com.ibatis -DartifactId=ibatis -Dversion=1.0.0 -Dpackaging=jar -Dfile=ibatis-2.3.0.677.jar
2.数据库配置
添加后缀：allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8，防止中文乱码

3.测试目的:找到一种高并发下，插入每条记录并正确返回插入记录的ID
