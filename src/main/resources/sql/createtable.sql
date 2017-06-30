/******************** Add Table: javaoas.T_OUT_COMPANY ************************/

/* Build Table Structure */
CREATE TABLE javaoas.T_OUT_COMPANY
(
	Id VARCHAR2(50) NOT NULL,
	name VARCHAR2(50) NOT NULL,
	con_type VARCHAR2(50) NOT NULL,
	boss VARCHAR2(50) NOT NULL,
	address VARCHAR2(200) NOT NULL,
	zijin VARCHAR2(100) NULL,
	area VARCHAR2(800) NOT NULL,
	zhizhao VARCHAR2(800) NULL,
	zizhidoc VARCHAR2(800) NULL,
	zizhi VARCHAR2(400) NULL
);

/* Add Primary Key */
ALTER TABLE javaoas.T_OUT_COMPANY ADD CONSTRAINT pkT_OUT_COMPANY
	PRIMARY KEY (Id);

/* Add Comments */
COMMENT ON COLUMN javaoas.T_OUT_COMPANY.Id IS '公司id';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.name IS '公司名';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.con_type IS '公司类型（紧密型、规范型）';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.boss IS '法人代表';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.address IS '注册地址';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zijin IS '注册资金';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.area IS '金融范围';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zhizhao IS '执照附件';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zizhidoc IS '资质文件（多个）用【；】连接';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zizhi IS '资质说明';

COMMENT ON TABLE javaoas.T_OUT_COMPANY IS '外包公司';


/******************** Add Table: javaoas.T_OUT_CONTRACT ************************/

/* Build Table Structure */
CREATE TABLE javaoas.T_OUT_CONTRACT
(
	Id VARCHAR2(50) NOT NULL,
	name VARCHAR2(100) NOT NULL,
	con_type VARCHAR2(100) NOT NULL,
	unit VARCHAR2(100) NOT NULL,
	companyid VARCHAR2(50) NOT NULL,
	jine VARCHAR2(50) NULL,
	startdate DATE NOT NULL,
	enddate DATE NOT NULL,
	qixian VARCHAR2(10) NOT NULL,
	yewu VARCHAR2(50) NOT NULL,
	atts VARCHAR2(500) NULL,
	buchong VARCHAR2(500) NULL,
	con_code VARCHAR2(50) NOT NULL
);

/* Add Primary Key */
ALTER TABLE javaoas.T_OUT_CONTRACT ADD CONSTRAINT pkT_OUT_CONTRACT
	PRIMARY KEY (Id);

/* Add Comments */
COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.Id IS '合同ID';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.name IS '合同名';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.con_type IS '类型（紧密型，规范型）';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.unit IS '签署单位（联通的）';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.companyid IS '外包公司id';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.jine IS '合同金额';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.startdate IS '合同开始日期';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.enddate IS '合同结束日期';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.qixian IS '合同期限（年）';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.yewu IS '合同涉及的业务';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.atts IS '合同文本附件';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.buchong IS '补充文件';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.con_code IS '合同编号';

COMMENT ON TABLE javaoas.T_OUT_CONTRACT IS '合同';






CREATE TABLE javaoas.T_OUT_DICT
(
	id VARCHAR2(50) NOT NULL,
	lx VARCHAR2(50) NOT NULL,	
	code VARCHAR2(50) NOT NULL,
	name VARCHAR2(50) NOT NULL	
);

/* Add Primary Key */
ALTER TABLE javaoas.T_OUT_DICT ADD CONSTRAINT pkT_OUT_DICT
	PRIMARY KEY (Id);

/* Add Comments */
COMMENT ON COLUMN javaoas.T_OUT_DICT.Id IS 'ID';
COMMENT ON COLUMN javaoas.T_OUT_DICT.lx IS '类型id';
COMMENT ON COLUMN javaoas.T_OUT_DICT.code IS '编码（默认跟显示名一样）';
COMMENT ON COLUMN javaoas.T_OUT_DICT.name IS '显示名称';





/* Build Table Structure */
CREATE TABLE javaoas.T_OUT_DUTY
(
	Id VARCHAR2(50) NOT NULL,
	name VARCHAR2(50) NOT NULL,
	remark VARCHAR2(200) NOT NULL,
	bak VARCHAR2(50)
);
/* Add Primary Key */
ALTER TABLE javaoas.T_OUT_DUTY ADD CONSTRAINT pkT_OUT_DUTY
	PRIMARY KEY (Id);

/* Add Comments */
COMMENT ON COLUMN javaoas.T_OUT_DUTY.Id IS 'ID';
COMMENT ON COLUMN javaoas.T_OUT_DUTY.name IS '职务名称';
COMMENT ON COLUMN javaoas.T_OUT_DUTY.remark IS '职务描述';
COMMENT ON COLUMN javaoas.T_OUT_DUTY.bak IS 'bak';



/* Build Table Structure */
CREATE TABLE javaoas.T_OUT_DUTYMaping
(
	Id VARCHAR2(50) NOT NULL,
	userid VARCHAR2(50) NOT NULL,
	dutyid VARCHAR2(50) NOT NULL,
	unitid VARCHAR2(50) NOT NULL,
	selforg VARCHAR2(50) NOT NULL,
	suborg VARCHAR2(50) NOT NULL,	
	remark VARCHAR2(200) NOT NULL
);
/* Add Primary Key */
ALTER TABLE javaoas.T_OUT_DUTYMaping ADD CONSTRAINT pkT_OUT_DUTYMaping
	PRIMARY KEY (Id);

/* Add Comments */
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.Id IS 'ID';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.userid IS '用户id';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.dutyid IS '职务id';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.unitid IS '单位ID';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.selforg IS '是否管理本单位';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.suborg IS '是否管理子单位';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.remark IS '描述';






