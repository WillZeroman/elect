#建表语句
#测试表
CREATE TABLE Elec_Text(
	textID varchar(50) not null,
	textName varchar(50),
	textDate datetime,
	textRemark varchar(500)
);

#代办事宜
CREATE TABLE Elec_CommonMsg(
	ComID VARCHAR(50)  NOT NULL primary key,    #主键ID
	StationRun VARCHAR(1000)  NULL, #站点运行情况
	DevRun VARCHAR(1000)  NULL,    #设备运行情况
	CreateDate DATETIME NULL        #创建日期
	#CreateEmpCode VARCHAR(50)  NULL,#创建人
);

#数据字典
CREATE TABLE Elec_SystemDDL(
	SeqID INT NOT NULL primary key auto_increment, #主键ID(自增长)
	Keyword VARCHAR(20) NOT NULL,  #查询关键字
	DdlCode INT NOT NULL,           #数据字典的code
	DdlName VARCHAR(50) NOT NULL    #数据字典的value
) ;

#用户表
CREATE TABLE Elec_User(
	UserID VARCHAR(50)  NOT NULL primary key, #主键ID
	JctID VARCHAR(50)   NULL,     #所属单位code
	UserName VARCHAR(50)   NULL,  #用户姓名
	LoginName VARCHAR(50)   NULL, #登录名
	LoginPwd VARCHAR(50)  NULL,   #密码#
	SexID VARCHAR(10)  NULL,      #性别
	Birthday DATETIME NULL,       #出生日期
	Address VARCHAR(100)  NULL,    #联系地址
	ContactTel VARCHAR(50)  NULL, #联系电话 
	Email VARCHAR(50)  NULL,      #电子邮箱
	Mobile VARCHAR(50)  NULL,     #手机
	IsDuty VARCHAR(10)  NULL,     #是否在职
	OnDutyDate DATETIME NULL,     #入职时间
	OffDutyDate DATETIME NULL,    #离职时间
	remark VARCHAR(500)  NULL   #备注
	#IsDelete VARCHAR(10)   NULL,  #是否删除
	#CreateEmpID VARCHAR(50)  NULL,#创建人ID
	#CreateDate DATETIME NULL,     #创建时间
	#LastEmpID VARCHAR(50)  NULL,  #修改人ID
	#LastDate DATETIME NULL        #修改时间
);


#用户角色信息表
CREATE TABLE Elec_User_Role(
	SeqID INT NOT NULL,               #主键ID
	UserID VARCHAR(50)   NULL,        #用户ID
	RoleID VARCHAR(50)   NULL,        #角色ID
	remark VARCHAR(500)  NULL      #备注
	#CreateEmpCode VARCHAR(50)  NULL,  #创建人
	#CreateDate DATETIME NULL          #创建时间
); 
#角色权限信息表
CREATE TABLE Elec_Role_Popedom(
	RoleID VARCHAR(50)  NOT NULL,      #主键ID
	Popedomcode VARCHAR(50)   NULL,    #配置web文件中权限的编码code的字符串连接
	remark VARCHAR(500)  NULL       #备注
	#IsDelete VARCHAR(10)   NULL,       #是否删除
	#CreateEmpCode VARCHAR(50)  NULL,   #创建人
	#CreateDate DATETIME NULL           #创建时间
);
