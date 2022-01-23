# hive的元数据存在mysql里面

# http://dblab.xmu.edu.cn/blog/2810-2/

# aliyun
sudo yum install mysql-server

# 目录说明
#/var/lib/mysql                          mysql数据文件存放路径
#/etc/my.cnf                              mysql配置文件路径
#/usr/lib64/mysql                   mysql库文件路径
#/usr/bin/mysql*                       mysql二进制可执行文件路径
#/etc/rc.d/init.d/mysqld             mysql服务管理脚本地址
#/var/log/mysqld.log                 mysql日志文件路径

service mysqld start

alter user 'root'@'localhost' identified by '23****';

service mysqld stop

CREATE USER 'hive'@'localhost' IDENTIFIED BY 'hive';

flush privileges;

grant all on *.* to hive@localhost;

flush privileges;


