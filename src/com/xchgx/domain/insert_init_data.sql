/*
UTF8: CREATE DATABASE `hboucn` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci
1、创建数据库的时候：CREATE DATABASE `test`
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';
查看默认的编码格式:
mysql> show variables like "%char%";
+--------------------------+---------------+
| Variable_name | Value |
+--------------------------+---------------+
| character_set_client | gbk |
| character_set_connection | gbk |
| character_set_database | utf8 |
| character_set_filesystem | binary |
| character_set_results | gbk |
| character_set_server | utf8 |
| character_set_system | utf8 |
+--------------------------+-------------+
//修改/etc/mysql/my.cnf配置文件
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
#skip-character-set-client-handshake

---------------------------
数据库连接串中指定字符集URL=jdbc:mysql://yourIP/college?user=root&password=yourPassword&useUnicode=true&characterEncoding=gbk
 * */

/*插入t_permission
 * 权限名称
 * 备注
 * <property name="name" />
		<property name="description" />
 * */
insert into t_permission(id, level,name,description) values(1,1, '超级管理员','超管可以拥有用户管理+所有部门权限');
insert into t_permission(id, level,name,description) values(2,2, '校级','所有部门权限');
insert into t_permission(id, level,name,description) values(3,3, '部门级','所在部门权限');
insert into t_permission(id, level,name,description) values(4,4, '专业级','部门下的具体专业权限');
insert into t_permission(id, level,name,description) values(5,5, '班级','专业下的具体班');
insert into t_permission(id, level,name,description) values(6,6, '学生级','具体学生登录，查询自己相关信息');

/*插入t_shortcut
[0,"我的电脑","images/icon/computer.png","../manager/building.do",600,400],
[1,"首页管理","images/icon/11indexico.png","../manager/building.do",600,400],
[2,"新闻管理","images/icon/12newsico.png","newsManager/main.do",700,500],
[3,"用户管理","images/icon/13usersico.png","../manager/building.do",600,400],
[14,"初始化","images/icon/chushihuaico.png","../manager/building.do",600,400]
];
*/
insert into t_shortcut(id,icoid,name, ico, url, height, width, description ) values(1,0,'我的电脑','images/icon/computer.png','../manager/building.do',600,400,'');
insert into t_shortcut(id,icoid,name, ico, url, height, width, description ) values(2,1,'首页管理','images/icon/11indexico.png','../manager/building.do',600,400,'');
insert into t_shortcut(id,icoid,name, ico, url, height, width, description ) values(3,2,'新闻管理','images/icon/12newsico.png','newsManager/main.do',600,400,'');
insert into t_shortcut(id,icoid,name, ico, url, height, width, description ) values(4,3,'用户管理','images/icon/13usersico.png','../manager/building.do',600,400,'');
insert into t_shortcut(id,icoid,name, ico, url, height, width, description ) values(5,4,'初始化','images/icon/chushihuaico.png','../manager/building.do',600,400,'');

/**
 * 插入权限对应的图标关系表
 */
insert into PermissionShortcut(permissionId, shortcutId) values(1,1);
insert into PermissionShortcut(permissionId, shortcutId) values(1,2);
insert into PermissionShortcut(permissionId, shortcutId) values(1,3);
insert into PermissionShortcut(permissionId, shortcutId) values(1,4);
insert into PermissionShortcut(permissionId, shortcutId) values(1,5);

/*插入关联表数据 t_user*/
/*插入t_user数据,type=权限级别，1：超级管理员 2：部门 3：实训室 4：个人*/
/*insert into t_user(id, name,xm,password,type,locked,credit) values(1,'admin',  '超级管理员','ADMIN1',1, 0, 100);*/
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(1,'admin',  '超级管理员','ADMIN1',1, 0, 100,1);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(2,'xchgx', '陈刚', '123456',4, 0, 100,1);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(3,'jidian','机电信息工程学院', '123456', 2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(4,'renwen',  '人文学院','123456',2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(5,'caijing',  '财经学院','123456',2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(6,'guanli',  '管理学院','123456',2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(7,'zhaosheng',  '招生办','123456',2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(8,'jiuye',  '就业办','123456',2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(9,'jiaowu','教务处', '123456', 2, 0, 100,3);
insert into t_user(id, name,xm,password,type,locked,credit,permissionId) values(10,'softroom','软件工作室', '123456', 3, 0, 100,4);


/*插入t_department_type*/
insert into t_department_type(id, name, description) values(1,'行政部门','行政管理部门');
insert into t_department_type(id,name, description) values(2,'院系','各二级学院部门');
insert into t_department_type(id,name, description) values(3,'校企合作','校企合作部门，比如海乘，电子商务实训室，软件工作室等');

/*插入t_department*/
insert into t_department(id,name, description) values(1,'机电信息工程学院','各二级学院部门');
insert into t_department(id,name, description) values(2,'人文艺术学院','各二级学院部门');
insert into t_department(id,name, description) values(3,'财经学院','各二级学院部门');
insert into t_department(id,name, description) values(4,'管理学院','各二级学院部门');
insert into t_department(id,name, description) values(5,'校办','校长办公室');
insert into t_department(id,name, description) values(6,'招生办','招生办公室');
insert into t_department(id,name, description) values(7,'就业办','就业办公式');
insert into t_department(id,name, description) values(8,'教务处','教务处');
insert into t_department(id,name, description) values(9,'总务处','总务处');
insert into t_department(id,name, description) values(10,'学工处','学工处');
insert into t_department(id,name, description) values(11,'保卫处','保卫处');
insert into t_department(id,name, description) values(12,'后勤','后勤管理公司');
insert into t_department(id,name, description) values(13,'电子商务实训室','电子商务实训室');
insert into t_department(id,name, description) values(14,'物流实训室','物流实训室');
insert into t_department(id,name, description) values(15,'软件工作室','软件工作室');
insert into t_department(id,name, description) values(16,'海乘培训','海乘培训');

/*插入t_school*/
insert into t_school(id,name, type, description) values(1,'湖北开放职业学院','大学','');


