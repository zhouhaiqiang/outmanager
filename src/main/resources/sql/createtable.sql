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
COMMENT ON COLUMN javaoas.T_OUT_COMPANY.Id IS '��˾id';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.name IS '��˾��';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.con_type IS '��˾���ͣ������͡��淶�ͣ�';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.boss IS '���˴���';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.address IS 'ע���ַ';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zijin IS 'ע���ʽ�';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.area IS '���ڷ�Χ';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zhizhao IS 'ִ�ո���';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zizhidoc IS '�����ļ���������á���������';

COMMENT ON COLUMN javaoas.T_OUT_COMPANY.zizhi IS '����˵��';

COMMENT ON TABLE javaoas.T_OUT_COMPANY IS '�����˾';


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
COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.Id IS '��ͬID';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.name IS '��ͬ��';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.con_type IS '���ͣ������ͣ��淶�ͣ�';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.unit IS 'ǩ��λ����ͨ�ģ�';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.companyid IS '�����˾id';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.jine IS '��ͬ���';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.startdate IS '��ͬ��ʼ����';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.enddate IS '��ͬ��������';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.qixian IS '��ͬ���ޣ��꣩';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.yewu IS '��ͬ�漰��ҵ��';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.atts IS '��ͬ�ı�����';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.buchong IS '�����ļ�';

COMMENT ON COLUMN javaoas.T_OUT_CONTRACT.con_code IS '��ͬ���';

COMMENT ON TABLE javaoas.T_OUT_CONTRACT IS '��ͬ';






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
COMMENT ON COLUMN javaoas.T_OUT_DICT.lx IS '����id';
COMMENT ON COLUMN javaoas.T_OUT_DICT.code IS '���루Ĭ�ϸ���ʾ��һ����';
COMMENT ON COLUMN javaoas.T_OUT_DICT.name IS '��ʾ����';







