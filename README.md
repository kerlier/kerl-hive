# hive

1. 安装mysql
    yum install mysql-server


2. 下载tar
    https://mirrors.tuna.tsinghua.edu.cn/apache/hive/hive-1.2.2/



1.上传tar包

2.解压
	tar -zxvf hive-1.2.1.tar.gz
3.安装mysql数据库
   推荐yum 在线安装

4.配置hive
	（a）配置HIVE_HOME环境变量  
		vi conf/hive-env.sh 
		配置其中的$hadoop_home

	
	（b）配置元数据库信息  
		vi  hive-site.xml 
		添加如下内容：
		<configuration>
		<property>
		<name>javax.jdo.option.ConnectionURL</name>
		<value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true</value>
		<description>JDBC connect string for a JDBC metastore</description>
		</property>

		<property>
		<name>javax.jdo.option.ConnectionDriverName</name>
		<value>com.mysql.jdbc.Driver</value>
		<description>Driver class name for a JDBC metastore</description>
		</property>

		<property>
		<name>javax.jdo.option.ConnectionUserName</name>
		<value>root</value>
		<description>username to use against metastore database</description>
		</property>

		<property>
		<name>javax.jdo.option.ConnectionPassword</name>
		<value>root</value>
		<description>password to use against metastore database</description>
		</property>
		</configuration>
	
5.安装hive和mysq完成后，将mysql的连接jar包拷贝到$HIVE_HOME/lib目录下
	如果出现没有权限的问题，在mysql授权(在安装mysql的机器上执行)
	mysql -uroot -p
	#(执行下面的语句  *.*:所有库下的所有表   %：任何IP地址或主机都可以连接)
	GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
	FLUSH PRIVILEGES;

6. Jline包版本不一致的问题，需要拷贝hive的lib目录中jline.2.12.jar的jar包替换掉hadoop中的 
/home/hadoop/app/hadoop-2.6.4/share/hadoop/yarn/lib/jline-0.9.94.jar


启动hive
bin/hive
----------------------------------------------------------------------------------------------------
Hive几种使用方式：
	1.Hive交互shell      bin/hive
	
	2.Hive JDBC服务(参考java jdbc连接mysql)
	
	3.hive启动为一个服务器，来对外提供服务
		bin/hiveserver2
		nohup bin/hiveserver2 1>/var/log/hiveserver.log 2>/var/log/hiveserver.err &
		
		启动成功后，可以在别的节点上用beeline去连接
		bin/beeline -u jdbc:hive2://mini1:10000 -n root
		
		或者
		bin/beeline
		! connect jdbc:hive2://mini1:10000
	
	4.Hive命令 
		hive  -e  ‘sql’
		bin/hive -e 'select * from t_test'
