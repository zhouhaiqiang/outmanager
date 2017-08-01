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
COMMENT ON COLUMN javaoas.T_OUT_DUTY.name IS 'ְ������';
COMMENT ON COLUMN javaoas.T_OUT_DUTY.remark IS 'ְ������';
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
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.userid IS '�û�id';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.dutyid IS 'ְ��id';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.unitid IS '��λID';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.selforg IS '�Ƿ������λ';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.suborg IS '�Ƿ�����ӵ�λ';
COMMENT ON COLUMN javaoas.T_OUT_DUTYMaping.remark IS '����';




-- Create table
create table T_OUT_ACTION
(
  ID        VARCHAR2(50) not null,
  USERID    VARCHAR2(50) not null,
  YWLINE    VARCHAR2(50) not null,
  YWACTION  VARCHAR2(50) not null,
  STARTDATE DATE default sysdate not null,
  ENDDATE   DATE,
  ISCB      VARCHAR2(50) default '��' not null,
  REMARK    VARCHAR2(200)
)
tablespace DATA1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_OUT_ACTION
  is 'ҵ��';
-- Add comments to the columns 
comment on column T_OUT_ACTION.ID
  is 'ID';
comment on column T_OUT_ACTION.USERID
  is '�û�id';
comment on column T_OUT_ACTION.YWLINE
  is 'ҵ����';
comment on column T_OUT_ACTION.YWACTION
  is 'ҵ��';
comment on column T_OUT_ACTION.STARTDATE
  is '��ʼ����';
comment on column T_OUT_ACTION.ENDDATE
  is '��������';
comment on column T_OUT_ACTION.ISCB
  is '�Ƿ����ɱ�';
comment on column T_OUT_ACTION.REMARK
  is '��ע';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_OUT_ACTION
  add constraint PKT_OUT_ACTION primary key (ID)
  using index 
  tablespace DATA1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table T_OUT_ACTION
  add constraint FKT_OUT_ACTION foreign key (USERID)
  references T_OUT_USER (ID);





  
  
-- Create table
create table T_OUT_REPORT
(
  ID      VARCHAR2(50) not null,
  REPDATE DATE not null,
  NAME    VARCHAR2(50) not null,
  UNIT    VARCHAR2(50),
  REPTYPE VARCHAR2(50) not null,
  REPDATA Clob not null,
  REPCODE VARCHAR2(50),
  INTF    VARCHAR2(50) not null,
  REMARK  VARCHAR2(200)
)
tablespace DATA1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_OUT_REPORT
  is '�����Ա���ݱ���';
-- Add comments to the columns 
comment on column T_OUT_REPORT.ID
  is 'ID';
comment on column T_OUT_REPORT.REPDATE
  is '��������';
comment on column T_OUT_REPORT.NAME
  is '������';
comment on column T_OUT_REPORT.UNIT
  is '��֯��λ';
comment on column T_OUT_REPORT.REPTYPE
  is '��������';
comment on column T_OUT_REPORT.REPDATA
  is '����json����';
comment on column T_OUT_REPORT.REPCODE
  is '������';  
comment on column T_OUT_REPORT.INTF
  is '�ӿ�״̬';    
comment on column T_OUT_REPORT.REMARK
  is '˵��';

-- Create/Recreate primary, unique and foreign key constraints 
alter table T_OUT_REPORT
  add constraint PK_T_OUT_REPORT primary key (ID)
  using index 
  tablespace DATA1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  ); 
  
  
 -- Create table
create table T_OUT_REPORTST
(
  ID         VARCHAR2(50) not null,
  REPDATE    DATE not null,
  UNIT       VARCHAR2(50) not null,
  REPTYPE    VARCHAR2(50) not null,
  INTF       VARCHAR2(50) default '���ɴ���' not null,
  ISRECREATE VARCHAR2(50) default '�������³�ȡ' not null,
  REMARK     VARCHAR2(200),
  CREATEDATE DATE default sysdate
)
tablespace DATA1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_OUT_REPORTST
  is '�����Ա���ݱ���״̬';
-- Add comments to the columns 
comment on column T_OUT_REPORTST.ID
  is 'ID';
comment on column T_OUT_REPORTST.REPDATE
  is '��������';
comment on column T_OUT_REPORTST.UNIT
  is '��֯��λ';
comment on column T_OUT_REPORTST.REPTYPE
  is '�������� ���걨���������±���';
comment on column T_OUT_REPORTST.INTF
  is '�ӿ�״̬ ��û�нӿ� ���ɴ��ͣ�';
comment on column T_OUT_REPORTST.ISRECREATE
  is '�Ƿ�����³�ȡ���������³�ȡ��';
comment on column T_OUT_REPORTST.CREATEDATE
  is '��������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_OUT_REPORTST
  add constraint PK_T_OUT_REPORTST primary key (ID)
  using index 
  tablespace DATA1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

