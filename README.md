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
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(45) NOT NULL,
  `enabled` varchar(45) NOT NULL,
  `pid` varchar(45) NOT NULL,
  `ptype` varchar(45) NOT NULL,
  `source` varchar(45) NOT NULL,
  `partion_key` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PID_PTYPE_SOURCE` (`name`,`ptype`,`source`,`pid`),
  KEY `IDX_UPT` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

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

关于TIMESTAMP和DATETIME的比较
一个完整的日期格式如下：YYYY-MM-DD HH:MM:SS[.fraction]，它可分为两部分：date部分和time部分，其中，date部分对应格式中的“YYYY-MM-DD”，time部分对应格式中的“HH:MM:SS[.fraction]”。对于date字段来说，它只支持date部分，如果插入了time部分的内容，它会丢弃掉该部分的内容，并提示一个warning。
TIMESTAMP和DATETIME的相同点：

1> 两者都可用来表示YYYY-MM-DD HH:MM:SS[.fraction]类型的日期。

TIMESTAMP和DATETIME的不同点：

1> 两者的存储方式不一样

对于TIMESTAMP，它把客户端插入的时间从当前时区转化为UTC（世界标准时间）进行存储。查询时，将其又转化为客户端当前时区进行返回。

而对于DATETIME，不做任何改变，基本上是原样输入和输出。

2> 两者所能存储的时间范围不一样

timestamp所能存储的时间范围为：'1970-01-01 00:00:01.000000' 到 '2038-01-19 03:14:07.999999'。

datetime所能存储的时间范围为：'1000-01-01 00:00:00.000000' 到 '9999-12-31 23:59:59.999999'。

 

总结：TIMESTAMP和DATETIME除了存储范围和存储方式不一样，没有太大区别。当然，对于跨时区的业务，TIMESTAMP更为合适。
https://www.cnblogs.com/ldwnana/p/8401577.html

##SqlMap的配置总结
核心提示：SqlMap的配置是iBatis中应用的核心。这部分任务占据了iBatis开发的70的工作量。 1、命名空间： sqlMap namespace=Account，在此空间外要引用此空间的元素，则需要加上命名空间名。 2、实体的别名： typeAlias alias=Account type=com.lavasoft.ibatissut.sim 

SqlMap的配置是iBatis中应用的核心。这部分任务占据了iBatis开发的70的工作量。 

1、命名空间： 
  <sqlMap namespace="Account">，在此空间外要引用此空间的元素，则需要加上命名空间名。 

2、实体的别名： 
  <typeAlias alias="Account" type="com.lavasoft.ibatissut.simple.domain.entity.Account"/> 
  如果有用到的全名的地方，可以用别名代替，受命名空间约束。 

3、插入操作 
    对于自增主键的表，插入可以不配置插入的主键列。否则是必须的。 

4、获取主键 
     插入语句之前配置：主要是针对Sequence主键而言，插入前必须指定一个主键值给要插入的记录。Oracle、DB2亦如此，方法是在插入语句标签<insert....>之前配置上： 
    <insert id="insertAccount" parameterClass="Account"> 
        <selectKey resultClass="long" keyProperty="sctId"> 
            SELECT SEQ_TEST.NEXTVAL FROM DUAL 
        </selectKey>  
        insert into .... ........ 
    </insert> 
  
    插入语句之后配置：主要是针对自增主键的表而言，这类表在插入时不需要主键，而是在插入过程自动获取一个自增的主键。比如MySQL 

    <insert id="insertAccount" parameterClass="Account"> 
        <selectKey resultClass="long" keyProperty="sctId"> 
            SELECT LAST_INSERT_ID() 
       </selectKey>  
        insert into .... ........ 
    </insert> 

   当然，是否需要配置<selectKey>根据情况，只要能保证记录有主键即可。一旦配置了<selectKey>，就可以在执行插入操作时获取到新增记录的主键。 

6、SQL入参parameterClass 
  插入语句入参：parameterClass="类别名"  来设定。 
  查询语句入参：可以设定类别名，也可以设定为map，也可以设定为iBatis支持的原生类型（比如string、int、long等），当只有一个原生类型入参时，使用#value#来引用，这个value是不是关键字，可变。比如： 
    <select id="getById"  parameterClass="long" resultMap="result_base"> 
        select * from customer where id = #value# 
    </select> 
    map是最强大的入参方式，任何入参方式都可以转换为这种入参方式，因为iBatis仅接受一个入参，当几个参数分布在不同对象中的时候，将这些对象的属性（或者对象本身put）到map中，然后一次传递给sql语句是非常有效。可以自己写一个将对象或者对象集合转换为map的工具（我已经实现一个了）。 
    另外，map的中的元素（比如pobj）是个复杂对象，则还可以在SQL中以#pobj.protyename#的格式来引用其中内嵌的属性。当然不推荐这么干。 

7、返回值参数类型 
      返回值参数也同样有两种类型，一种是对象类型resultClass="Account"，一种是resultMap="AccountResult"。这两种类型的选择常常会令人迷惑不解，一言明其理： 
当结果集列名和类属性名完全对应的时候，则应该使用resultClass来指定查询结果类型。当然有些列明不对应，可以在sql中使用as重命名达到一致的效果。 

当查询结果列名和类属性名对应不上的时候，应该选择 resultMap指定查询结果集类型。否则，则查询出来填充的对象属性为空（数字的为0，对象的为null）。 

但是实际上 resultMap是对一个Java Bean的映射，需要先定义xml的映射后，才可以引用，例如： 
    <resultMap id="AccountResult" class="Account"> 
        <result property="id" column="ACC_ID"/> 
        <result property="firstName" column="ACC_FIRST_NAME"/> 
        <result property="lastName" column="ACC_LAST_NAME"/> 
        <result property="emailAddress" column="ACC_EMAIL"/> 
    </resultMap> 
    resultMap映射的结果的目的就是要将查询的结果集绑定到映射对象的属性上。 

   不管使用哪种返回值参数类型，其最终目的就是要把每条记录映射到一个类的对象或者对象集合上，如果有某个类属性映射不上，则在得到的这个对象或对象集合中这个属性为空。映射的属性可以是表与实体中的一部分。不要同时使用两种返回值参数类型，这样只会令人迷惑。 

8、查询结果集分组 
    查询结果集排序有两种方式：一是在结果集映射上定义<resultMap id="result" class="bar" groupBy="id">，另一种就是在SQL语句中分组。建议在SQL语句中分组，以获得更大的可控制性。 

9、 SQL中参数的引用 
     SQL中引用parameterClass的参数有三种方式： 
     iBatis内置支持的类型，比如int、string，使用#value#来引用，这个value是不是关键字，可变。 
     map类型的参数，使用#keyName#来引用，keyName为键名。 
     复杂对象的参数，使用#propertyName#来引用，propertyName类属性的名字。 

10、模糊查询中参数的引用 
    模糊查询是针对字符串而言的，如果遇到两个单引号要包含一个参数，则不能再用#来引用变量了，而应该改为$，比如：'%$varName$%'，当然，也可以使用 '%' || #varname# || '%' 来绕过此问题。 

11、SQL片段 
       可以通过<sql id="sql_xxx">...</sql>定义SQL片段，然后<include refid="sql_xxx"/>来在各种语句中引用，达到复用目的。 

12、动态SQL 
      可以通过使用动态SQL来组织灵活性更大的更通用的SQL，这样极大减少了编码量，是iBatis应用的第二大亮点。 
     比如：一个动态的where条件 
                <dynamic prepend="where"> 
                        <isNotEmpty prepend="and" property="$$$$$"> 
                                $name like '%'|| #$name# ||'%' 
                        </isNotEmpty> 
                        <isGreaterThan prepend="and" property="$$$$$" compareValue="$$$number"> 
                                $code like '%'|| #$code# ||'%' 
                        </isGreaterThan> 
                </dynamic> 
     当然，prepend表示链接关键字，可以为任何字符串，当为sql关键字时，iBatis自动判断是否应该添加该关键字。该语法也很简单，关键是要会用心思考组织动态SQL。 
    这里面有一点要注意：区别<isNotEmpty>和<isNotNull>区别，当为空空串时<isNotEmpty>返回true，当为空串时<isNotNull>返回真。哈哈，自己体会吧，说了反而啰嗦。 

13、结果集映射继承 
结果集映射的继承的目的是为了映射定义的复用，比如下面定义了两个映射，AccountResult继承了base： 
    <resultMap id="base" class="Account"> 
        <result property="id" column="ACC_ID"/> 
        <result property="firstName" column="ACC_FIRST_NAME"/> 
        <result property="lastName" column="ACC_LAST_NAME"/> 
    </resultMap> 
    <resultMap id="AccountResult" class="Account" extends="Account.base"> 
        <result property="emailAddress" column="ACC_EMAIL"/> 
    </resultMap> 
这样，就很容易扩展了一个映射策略。 
       
14、查询注入 
查询注入是在一个查询中嵌入另外一个查询，这样做的目的是为了实现实体对象之间的关联关联关系（一对一、一对多、多对多）分单项双向。有关这些内容，是比较复杂的，笔者对此做了深入研究，并分别写了三篇来讲述。 

查询注入的实现就是在实体属性为另外一个实体或者实体集合的时候，引入一个相关的查询来实现，例如，客户和订单的映射关系： 
public class Customer { 
    private Long id; 
    private String name; 
    private String address; 
    private String postcode; 
    private String sex; 
    private List<Orders> orderlist = new ArrayList<Orders>(); 

    <resultMap id="result" class="customer"> 
        <result property="id" column="id"/> 
        <result property="name" column="name"/> 
        <result property="address" column="address"/> 
        <result property="postcode" column="postcode"/> 
        <result property="sex" column="sex"/> 
        <result property="orderlist" column="id" select="orders.findByCustomerId "/> 
    </resultMap> 

在这个映射中，为了查询客户的时候，能查询到相关的订单，可以在映射orderlist 属性的时候，将其指向另外一个查询orders.findByCustomerId ，这个查询是以Customer的id 为参数来查询的。 

select="orders.findByCustomerId " 这个查询定义如下： 
    <select id="findByCustomerId" resultMap="result_base" parameterClass="long"> 
        select * from orders where customerId = #value# 
    </select> 

原理就是这么简单，然后根据实际情况，可以自由实现实体间的关联关系。 

14、iBatis的分页查询 
iBatis 的分页有两种方式，一点都不神秘，不要被网上的流言所迷惑。 
第一种方式：结果集筛选分页。先执行部分页的SQL查询语句，然后得到一个 ResultSet，然后根据分页范围选择有效的记录填充到对象中，最终以集合的形式返回。对于10w条一下的记录的表，不存在性能问题，如果存在，你可以选择第二中方式。 
第二种方式：SQL分页，通过组装分页类型的SQL来实现分页。这个关键在于分页参数的传递和分页SQL的构建。分页SQL构件每种数据库都不一样，不说了。分页参数的传递却可以通用。我主张用map分装入参，连同分页参数一块传递进来，就搞定了。如果原来没有考虑到分页，而用的是对象做参数，则可以通过apache 的 beanutils组件来实现一个object到map之间的转换工具，问题迎刃而解。 

当然，这还不是分页查询应用的最高境界。思考，分页需要计算一个总记录数，记录数执行的sql返回值是count(?)，条件是除了分页以外的条件，因此应该将查询SQL静态分开，以MySQL为例，可以将查询分为查什么，和什么条件两部分，在条件部分对分页参数进行动态判断，如果分页参数就不分页，如果有则分页。这样最后只需要两个组装的sql就可以计算总数和分页查询了。大大简化了问题的难度。 Oracle的解决思路也一样，不一样的地方就是拼装分页SQL改变了。 

15、执行存储过程的配置 
SQL Map 通过<procedure>元素支持存储过程。下面的例子说明如何使用具有输出参数 
的存储过程。 
    <parameterMap id="swapParameters" class="map"> 
        <parameter property="email1" jdbcType="VARCHAR" javaType="java.lang.String" mode="INOUT"/> 
        <parameter property="email2" jdbcType="VARCHAR" javaType="java.lang.String" mode="INOUT"/> 
    </parameterMap> 
    <procedure id="swapEmailAddresses" parameterMap="swapParameters"> 
        {call swap_email_address (?, ?)} 
    </procedure> 
调用上面的存储过程将同时互换两个字段（数据库表）和参数对象（Map）中的两个 email地址。如果参数的 mode 属性设为 INOUT 或 OUT，则参数对象的值被修改。否则保持不变。 
注意！要确保始终只使用 JDBC 标准的存储过程语法。参考 JDBC 的 CallableStatement 
文档以获得更详细的信息。 

16、就是iBatis中各种id的命名了，这个看起来小菜一碟，但是搞砸了会很痛苦。建议如果有DAO层的话，DAO接口的名字和SQL语句 id的名字保持一致。同时，在DAO中将save和update封装为一个方法（从Hibernate中学来的），这是非常好的。也可以直接在SQL层将插入和更新柔和在一块，太复杂，有点影响效率，这见机行事了。 

   另外Spring提供了各种数据操作模板，通过模板，擦做数据也就是“一句话”的问题，写个DAO还有必要么，尤其对iBatis来说，根本没有必要。这样，就需要在领域活动层的设计上下功夫了。 

17 、iBatis的查询也可以配置缓存策略，缓存的配置很复杂，分很多中情况，可以参看附件中的iBATIS-SqlMaps-2_cn.pdf 的39页内容，有详细介绍。 

18、偷懒的最高境界，让程序去干哪里80%的体力活。自己仅仅把把关。任何重复的活动都有规律可循的，一旦发现了其中的规律，你就可以想办法把自己从中解脱出来。 
    iBatis也不例外，每个表都有增删改查、分页等操作。对应在每个DAO方法上亦如此。可以通过数据库生成sqlmap、entity、dao，然后将这些东西改吧改吧就完成大部分的工作量。本人已经实现过了，当然开发这个工具的前提是你对iBatis有深入研究和理解。 

------------------------------------------------- 
下面是iBatis开发指南中内容： 

附录：容易出错的地方 
本附录是译者添加的，列出了初学者容易出错的地方，作为完成快速入门课程后的学习 
笔记，可以让初学者少走些弯路。仅供参考。 
1)  在 parameterMap 和 resultMap 中，字段数据类型是 java.sql.Types 类定义的常量名 
称。常用的数据类型包括 BLOB，CHAR，CLOB，DATE，LONGVARBINARY， 
INTEGER，NULL，NUMERIC，TIME，TIMESTAMP 和 VARCHAR 等。 
2)  对于数据表中 NULLABLE 的字段，必须在 parameterMap 和 resultMap 中指定字段 
的数据类型。 
3)  对于数据类型是 DATE，CLOB 或 BLOB 的字段，最好在 parameterMap 和 resultMap中指定数据类型。 
4)  对于二进制类型的数据，可以将 LONGVARBINARY 映射成 byte[]。 
5)  对于文本类型较大的数据，可以将 CLOB 映射成 String。 
6) Java Bean 必须拥有缺省的构造器（即无参数的构造器）。 
7) Java Bean 最好实现 Serializable 接口，以备应用的进一步扩展。 

本人认为：尽量避免在每个入参后面附加参数的类型。以保持配置简洁，并且本人在长期开发中，没有发现必须要那么做。

https://www.w3cschool.cn/ibatis/ty1d1rlc.html

UK 异常
Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '王五-DL001-后台-p123' for key 'UK_PID_PTYPE_SOURCE'

https://www.cnblogs.com/ThinkVenus/p/8042516.html


##时间处理思路：
数据库 datetime
java bean 时间属性 java.util.Date

get取用的时候得到的都是转换后的String,时间格式 yyyy-MM-dd HH:mm:ss;

Account{id=3,
###java.sql.Date
createTime=2018-12-09

###java.sql.Timestamp;
createTime=2018-12-09 18:32:50.0,   updateTime=2018-12-09T18:32:50.000+08:00,
java.util.Date 转 String
###updateTime=2018-12-09 18:32:50 
name='王五', enabled='0', pid='p123', ptype='DL002', source='后台', partionKey=201812, version=0}
##tostring()
org.apache.commons.lang.builder.ReflectionToStringBuilder
ReflectionToStringBuilder.toString(this)

