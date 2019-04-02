-----------------------------------------------------------
--�����ͺ��̽� ��ü

/*
1. �����ͺ��̽��� ����Ǵ� �͵��� ���̺� �ܿ� ��Ÿ ���� ���� �͵��� ����Ǵµ� �̰��� �����ͺ��̽� ��ü(Database Objects)��� �θ���.
2. TABLE
�ϳ� �Ǵ� ���� �÷�(Column)���� �� �ϳ��� ���ڵ带 �̷��, �̷��� ���ڵ���� �� ���̺��̵ȴ�. ���� ��� "���" ���̺��� ���, �̸�, �μ� �� ���� �÷��� ���� �ǰ� �������ŭ�� ���ڵ带 ���� �ȴ�.
3.
CREATE ��
�����ͺ��̽� ���� ��� ��ü�� ������ �� ����ϴ� ����
ALTER ��
�̹� ������ ��ü�� ������ ����
DROP ��
������ ��ü�� ����
=>����) �����ڿ� ���� RESOURCE ������ �ο� ���� ����ڸ� �۾� ����
*/

    
---------------------------------------------
--���Ἲ

/*
1. ���Ἲ���� ��ü ���Ἲ(Entity Integrity), ���� ���Ἲ(Relational Integrity), ������ ���Ἲ(Domain Integrity)�� �ִ�.
2. ��ü ���Ἲ
��ü ���Ἲ�� �����̼ǿ� ����Ǵ� Ʃ��(tuple)�� ���ϼ��� �����ϱ� ���� ���������̴�.
3.���� ���Ἲ
���� ���Ἲ�� �����̼� ���� �������� �ϰ����� �����ϱ� ���� ���������̴�.
4. ������ ���Ἲ
������ ���Ἲ�� �Ӽ����� ��� ������ ���� ������ �����ϱ� ���� ���������̴�.
���� ���, �л� ���� ����� ���̺��� ����ٸ�
--���̺� ����
CREATE TABLE member (     --���̺� �̸� member
	sid NUMBER        --������ȣ ����� �÷�
	,name VARCHAR2(10) --�̸�, 10���ڸ� ���
	,kor NUMBER(3)     --����, ���� 3�ڸ��� ���(0~999). 0~100 ����.
	,eng NUMBER(3)     --����, ���� 3�ڸ��� ���
	,mat NUMBER(3)     --����, ���� 3�ڸ��� ���
);
5. �������� ����
- PRIMARY KEY(PK) : �ش� �÷� ���� �ݵ�� �����ؾ� �ϸ�, �����ؾ� ��(NOT NULL�� UNIQUE ���������� ������ ����)
- FOREIGN KEY(FK) : �ش� �÷� ���� �����Ǵ� ���̺��� �÷� �� ���� �ϳ��� ��ġ�ϰų� NULL�� ����
- UNIQUE(UK) : ���̺����� �ش� �÷� ���� �׻� �����ؾ� ��
- NOT NULL : �÷��� NULL ���� ������ �� ����.
- CHECK(CK) : �ش� �÷��� ���� ������ ������ ���� ������ ���� ����
*/




-------------------------------
--NOT NULL


---------------------
CREATE TABLE table0 (
    col1 VARCHAR2(10) NOT NULL
    ,col2 VARCHAR2(10)
);

INSERT INTO table0 (col1, col2)
    VALUES ('TEST', 'HELLO');
INSERT INTO table0 (col1)
    VALUES ('TEST');
INSERT INTO table0 (col2)
    VALUES ('HELLO');  --ORA-01400: cannot insert NULL into ("MINJONG"."TABLE0"."COL1")
COMMIT;

SELECT col1, col2
    FROM table0;
    
DESC table0;    
    
SELECT * FROM user_constraints 
	WHERE table_name='INSA';    
    
    
 
-------------------------------
--UNIQUE


----------------------
CREATE TABLE table1 (
    col1 VARCHAR2(10) 
    ,col2 VARCHAR2(10)
);

ALTER TABLE table1
    ADD CONSTRAINT table1_col1_pk PRIMARY KEY(col1);
ALTER TABLE table1
    ADD CONSTRAINT table1_col2_uk UNIQUE(col2);

INSERT INTO table1 (col1, col2)
    VALUES ('TEST', 'HELLO');
INSERT INTO table1 (col1)
    VALUES ('ABC');
INSERT INTO table1 (col1)
    VALUES ('DEF');
INSERT INTO table1 (col1, col2)
    VALUES ('1234', 'HELLO');  --ORA-00001: unique constraint (MINJONG.TABLE1_COL2_UK) violated
COMMIT;

SELECT col1, col2
    FROM table1;


    
 
-------------------------------
--CHECK

----------------------
CREATE TABLE table2 (
    col1 VARCHAR2(10) 
    ,col2 VARCHAR2(10)
);

ALTER TABLE table2
    ADD CONSTRAINT table2_col1_pk PRIMARY KEY(col1);
ALTER TABLE table2
    ADD CONSTRAINT table2_col2_ck CHECK(col2 IN ('JAVA', 'ORACLE', 'HTML', 'CSS'));

INSERT INTO table2 (col1, col2)
    VALUES ('TEST', 'JAVA');
INSERT INTO table2 (col1)
    VALUES ('ABC');
INSERT INTO table2 (col1, col2)
    VALUES ('1234', 'HELLO');  --ORA-02290: check constraint (MINJONG.TABLE2_COL2_CK) violated
COMMIT;

SELECT col1, col2
    FROM table2;
    
    
    
    
-----------------------------------------
--DEFAULT Ű����
--����ڰ� ������� ���� �Է����� �ʴ� ���
--�ڵ����� ������ ���� �Է��ϴ� ����
--NOT NULL ���� ������ �÷��� �ڵ����� �� �Է½� ����.

CREATE TABLE table3 (
    col1 VARCHAR2(10) 
    ,col2 VARCHAR2(10) DEFAULT 'NOTHING'
);

ALTER TABLE table3
    ADD CONSTRAINT table3_col1_pk PRIMARY KEY(col1);

INSERT INTO table3 (col1, col2)
    VALUES ('TEST', 'JAVA');
INSERT INTO table3 (col1)
    VALUES ('ABC');
COMMIT;

SELECT col1, col2
    FROM table3;
    
    
    

  
----------------------------------
--���� �м� ����

/*
1. SELECT *
  FROM user_constraints;
--CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME, SEARCH_CONDITION, R_CONSTRAINT_NAME
2.SELECT *
  FROM user_cons_columns;
--CONSTRAINT_NAME, TABLE_NAME, COLUMN_NAME  
3. SELECT *
  FROM user_constraints UC, user_cons_columns UCC
  WHERE UC.CONSTRAINT_NAME = UC.CONSTRAINT_NAME;
--CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME, SEARCH_CONDITION, R_CONSTRAINT_NAME, COLUMN_NAME
4. 
SELECT uc.TABLE_NAME AS TABLE_NAME
	, COLUMN_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
	, CONSTRAINT_TYPE
	, SEARCH_CONDITION
    , R_CONSTRAINT_NAME
    ,(SELECT R_TABLE_NAME
  FROM (SELECT uc.TABLE_NAME AS R_TABLE_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME)
    WHERE CONSTRAINT_NAME=uc.R_CONSTRAINT_NAME) AS R_TABLE_NAME
   ,(SELECT R_COLUMN_NAME
  FROM (SELECT COLUMN_NAME AS R_COLUMN_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME)
    WHERE CONSTRAINT_NAME=uc.R_CONSTRAINT_NAME) AS R_COLUMN_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME
  AND uc.table_name='EMPLOYEES';
  
5. 
SELECT utc.table_name AS table_name --������ ������ ���̺��
	, utc.column_name AS column_name --������ ������ �÷���
	, data_type   --������ ������ �÷��� �ڷ���
	, data_length   --������ ������ �÷��� ũ��(length)
	, data_precision   --������ ������ �÷��� ũ��(precision)
	, nullable    --������ ������ �÷��� NULL ��� ����
	, data_default    --������ ������ �÷��� DEFAULT ��
	, constraint_name    --�����
	, constraint_type     --��������
	, search_condition  --CHECK ������ ��� ���ǽ�
	, delete_rule    --FOREIGN KEY ������ ��� ON DELETE CASCADE ���� ����
	, r_table_name   --FOREIGN KEY ������ ��� ���� ���̺��
	, r_conlumn_name   --FOREIGN KEY ������ ��� ���� �÷���
	FROM 
	(SELECT  uc.table_name AS table_name
	, ucc.column_name AS column_name
	, uc.constraint_name AS constraint_name
	, uc.constraint_type AS constraint_type
	, uc.search_condition AS search_condition
	, uc.delete_rule AS delete_rule
	, (SELECT table_name 
		FROM user_cons_columns 
		WHERE constraint_name=uc.r_constraint_name) 
		AS r_table_name
	, (SELECT column_name 
		FROM user_cons_columns 
		WHERE constraint_name=uc.r_constraint_name) 
		AS r_conlumn_name
	FROM user_constraints uc, user_cons_columns ucc
	WHERE uc.constraint_name = ucc.constraint_name) ucc
		, user_tab_columns utc
	WHERE ucc.table_name(+)=utc.table_name
		AND ucc.column_name(+)=utc.column_name;
*/


SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME, SEARCH_CONDITION, R_CONSTRAINT_NAME
  FROM user_constraints
  WHERE table_name='JOBS';
  
SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME, SEARCH_CONDITION, R_CONSTRAINT_NAME
  FROM user_constraints
  WHERE table_name='EMPLOYEES';  
  
  
SELECT CONSTRAINT_NAME, TABLE_NAME, COLUMN_NAME  
  FROM user_cons_columns
  WHERE table_name='EMPLOYEES';  
  
  
SELECT uc1.CONSTRAINT_NAME AS CONSTRAINT_NAME
	, CONSTRAINT_TYPE
	, uc1.TABLE_NAME AS TABLE_NAME
    , SEARCH_CONDITION
    , R_CONSTRAINT_NAME
    , COLUMN_NAME
  FROM user_constraints uc1, user_cons_columns ucc
  WHERE uc1.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME
    AND uc1.table_name='EMPLOYEES';  
  
  
--Ư�� ���̺� �߰��� ������ ���� Ȯ���ϴ� ����  
SELECT uc.TABLE_NAME AS TABLE_NAME
	, COLUMN_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
	, CONSTRAINT_TYPE
	, SEARCH_CONDITION
    , R_CONSTRAINT_NAME
    ,(SELECT R_TABLE_NAME
  FROM (SELECT uc.TABLE_NAME AS R_TABLE_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME)
    WHERE CONSTRAINT_NAME=uc.R_CONSTRAINT_NAME) AS R_TABLE_NAME
   ,(SELECT R_COLUMN_NAME
  FROM (SELECT COLUMN_NAME AS R_COLUMN_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME)
    WHERE CONSTRAINT_NAME=uc.R_CONSTRAINT_NAME) AS R_COLUMN_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME
  AND uc.table_name='EMPLOYEES';
  
  

--Ư�� ���̺� �߰��� ������ ���� Ȯ���ϴ� ����  -> ��� ��� (�� ���� ���� �ʿ�)
CREATE OR REPLACE VIEW constraintsCheckView
AS
SELECT uc.TABLE_NAME AS TABLE_NAME
	, COLUMN_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
	, CONSTRAINT_TYPE
	, SEARCH_CONDITION
    , R_CONSTRAINT_NAME
    ,(SELECT R_TABLE_NAME
  FROM (SELECT uc.TABLE_NAME AS R_TABLE_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME)
    WHERE CONSTRAINT_NAME=uc.R_CONSTRAINT_NAME) AS R_TABLE_NAME
   ,(SELECT R_COLUMN_NAME
  FROM (SELECT COLUMN_NAME AS R_COLUMN_NAME
	, uc.CONSTRAINT_NAME AS CONSTRAINT_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME)
    WHERE CONSTRAINT_NAME=uc.R_CONSTRAINT_NAME) AS R_COLUMN_NAME
  FROM user_constraints uc, user_cons_columns ucc
  WHERE uc.CONSTRAINT_NAME=ucc.CONSTRAINT_NAME;
  

--�� ����� ���� ���� Ȯ�� ���� ����
SELECT *  
    FROM constraintsCheckView
  WHERE table_name='EMPLOYEES';
  
  
  
  
  
-------------------------------------------------------
--ON DELETE CASCADE �ɼ�
-- FK ���� ������ �θ� ���̺��� row(PK)�� ������ �� �ڽ� ���̺��� row(FK)�� ���� ���� ����




---------------------------
--ON DELETE CASCADE �ɼ� ���� ��
CREATE TABLE jobs (
	jikwi_id NUMBER
	,jikwi_name VARCHAR2(10)
	,CONSTRAINT JOBS_JIKWI_ID_PK PRIMARY KEY(jikwi_id)
);

INSERT INTO jobs (jikwi_id, jikwi_name) VALUES (1, '����');
INSERT INTO jobs (jikwi_id, jikwi_name) VALUES (2, '�븮');
INSERT INTO jobs (jikwi_id, jikwi_name) VALUES (3, '���');
COMMIT;


--FK ���� ����
CREATE TABLE employees (
	sid NUMBER PRIMARY KEY
	,name VARCHAR2(10) 
	,jikwi_id NUMBER REFERENCES jobs(jikwi_id)
);

INSERT INTO employees (sid, name, jikwi_id)
	VALUES (1, 'ȫ�浿', 1);
INSERT INTO employees (sid, name, jikwi_id)
	VALUES (2, '��浿', 2);
COMMIT;

--employees ���̺� �ڷ� Ȯ��
SELECT * FROM employees;

--employees ���̺��� '��浿' �ڷ� ���� �õ�. --O
DELETE FROM employees WHERE sid=2;

--employees ���̺� �ڷ� Ȯ��
SELECT * FROM employees;

--jobs ���̺� �ڷ� Ȯ��
SELECT * FROM jobs;

--jobs ���̺��� '����' �ڷ� ���� �õ�. --X
DELETE FROM jobs WHERE jikwi_id=1;

--jobs ���̺��� '���' �ڷ� ���� �õ�. --O
DELETE FROM jobs WHERE jikwi_id=3;

--jobs ���̺� �ڷ� Ȯ��
SELECT * FROM jobs;

--> ON DELETE CASCADE �ɼ� ������ �ʿ��մϴ�.
--> employees ���̺��� FK ���� ���� �� �ٽ� FK ���� ����
SELECT * FROM user_constraints WHERE table_name='EMPLOYEES';
--> DELETE_RULE �÷��� �� Ȯ�� -> NO_ACTION -> ON DELETE CASCADE �ɼ� ���� X -> �θ� row ������, �ڽ� row�� ���� �������� �ʴ´�. �⺻��.


--���� FK ���� ����
ALTER TABLE employees
    DROP CONSTRAINT SYS_C007031;
    
----------------------------
--ON DELETE CASCADE �ɼ� ���� ��
ALTER TABLE employees
	ADD CONSTRAINT EMPLOYEES_JIKWI_ID_FK
		FOREIGN KEY (jikwi_id)
		REFERENCES jobs(jikwi_id)
		ON DELETE CASCADE;


SELECT * FROM user_constraints WHERE table_name='EMPLOYEES';
--> DELETE_RULE �÷��� �� Ȯ�� -> CASCADE -> ON DELETE CASCADE �ɼ� ���� O -> �θ� row ������, �ڽ� row�� ���� ������ �� �ִ�.

SELECT * FROM jobs;
SELECT * FROM employees;
        
--jobs ���̺��� '����' �ڷ� ���� �õ�. --O
DELETE FROM jobs WHERE jikwi_id=1;
--> employees ���̺��� �ڷᰡ ���� �����ȴ�.

SELECT * FROM jobs;
SELECT * FROM employees;



--DELETE_RULE Ȯ���� ���� ����� �� ����
CREATE OR REPLACE VIEW constraintsCheckView
AS
SELECT utc.table_name AS table_name --������ ������ ���̺��
	, utc.column_name AS column_name --������ ������ �÷���
	, data_type   --������ ������ �÷��� �ڷ���
	, data_length   --������ ������ �÷��� ũ��(length)
	, data_precision   --������ ������ �÷��� ũ��(precision)
	, nullable    --������ ������ �÷��� NULL ��� ����
	, data_default    --������ ������ �÷��� DEFAULT ��
	, constraint_name    --�����
	, constraint_type     --��������
	, search_condition  --CHECK ������ ��� ���ǽ�
	, delete_rule    --FOREIGN KEY ������ ��� ON DELETE CASCADE ���� ����
	, r_table_name   --FOREIGN KEY ������ ��� ���� ���̺��
	, r_conlumn_name   --FOREIGN KEY ������ ��� ���� �÷���
	FROM 
	(SELECT  uc.table_name AS table_name
	, ucc.column_name AS column_name
	, uc.constraint_name AS constraint_name
	, uc.constraint_type AS constraint_type
	, uc.search_condition AS search_condition
	, uc.delete_rule AS delete_rule
	, (SELECT table_name 
		FROM user_cons_columns 
		WHERE constraint_name=uc.r_constraint_name) 
		AS r_table_name
	, (SELECT column_name 
		FROM user_cons_columns 
		WHERE constraint_name=uc.r_constraint_name) 
		AS r_conlumn_name
	FROM user_constraints uc, user_cons_columns ucc
	WHERE uc.constraint_name = ucc.constraint_name) ucc
		, user_tab_columns utc
	WHERE ucc.table_name(+)=utc.table_name
		AND ucc.column_name(+)=utc.column_name;

--�� ����� ���� ���� Ȯ�� ���� ����
SELECT *  
    FROM constraintsCheckView
  WHERE table_name='EMPLOYEES';