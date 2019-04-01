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

    
    
-----------------------------------------------
--���̺� ����

/*
***1. �� ���̺� ����
CREATE TABLE ���̺��̸� (
	�÷� �ڷ��� [��Ÿ����]
	,...
);
*/

CREATE TABLE members (
    mid NUMBER
    ,name_ VARCHAR2(20)
    ,phone VARCHAR2(20)
);

INSERT INTO members (mid, name_, phone)
    VALUES (1, 'HONG', '1234');
INSERT INTO members (mid, name_, phone)
    VALUES (2, 'PARK', '4567');
COMMIT;    

SELECT * FROM members;


------------------------------------
/*
2. ���� ���̺� ����(�÷���, �ڷ���) Ȯ��
SELECT * 
  FROM user_tab_columns
  WHERE table_name='���̺��'; --���̺� �̸� �빮�ڷ� ǥ���� ��.
DESC ���̺��̸�;
*/
SELECT * 
  FROM user_tab_columns
  WHERE table_name='INSA';

SELECT * 
  FROM user_tab_columns
  WHERE table_name='MEMBERS';

DESC members;



------------------------------------
/*
3. ���� ���̺� ���� �����ؼ� �� ���̺� ���� (���������� ������� �ʴ´�)
CREATE TABLE �����̺��
AS
SELECT �÷�����Ʈ FROM �������̺�� 
WHERE 1=0;
*/


CREATE TABLE jobs
AS
SELECT * FROM hr.jobs WHERE 1 = 0;



CREATE TABLE members2
AS
SELECT * FROM members WHERE 1=0;  --���� ����� ������ �������� ����

DESC members;  --������ ���� �ܿ� �������ǵ� �ִ�.
DESC members2; --�纻�� ������ ����Ǿ� �ִ�.

SELECT * FROM members2; -- 0 rows




------------------------------------
/*
**4. ���� ���̺� ���� �� ���� �ڷ� �����ؼ� �� ���̺� ���� �� �ڷ� �Է� (���������� ������� �ʴ´�)
CREATE TABLE �����̺��
AS
SELECT �÷�����Ʈ FROM �������̺�� [WHERE ���ǽ�];
*/


CREATE TABLE employees
AS
SELECT * FROM hr.employees;

SELECT * FROM employees;


CREATE TABLE employees2
AS
SELECT employee_id, first_name, last_name
    , email, phone_number
    FROM hr.employees;
    
SELECT * FROM employees2;    
    

CREATE TABLE employees3
AS
SELECT employee_id, first_name, last_name
    , email, phone_number
    FROM hr.employees
    WHERE department_id = 80;
    
SELECT * FROM employees3;       
    
    
    
    
    
CREATE TABLE members3
AS
SELECT * FROM members;

SELECT * FROM members3; --2 rows


CREATE TABLE members4
AS
SELECT * FROM members WHERE mid = 1;

SELECT * FROM members4; --1 rows


CREATE TABLE members5
AS
SELECT mid, name_ FROM members WHERE mid = 1;

SELECT * FROM members5; --1 rows




------------------------------
/*
5. ���� ���̺� �� �� �߰�
ALTER TABLE �������̺��
	ADD (���̸� �ڷ���, ...);
--> ���� ���̺� �����Ͱ� �ִ� ���� ���� ������� �÷��� �����ʹ� NULL�� ä������.
--> NULL�� ä���� �÷��� �ڷḦ ä����� UPDATE ����� �̿��Ѵ�.
--> �÷� ������ �ڵ����� �⺻���� ä����� DEFAULT Ű���带 �̿��Ѵ�.
*/

DESC members;
/*
MID   NOT NULL NUMBER        
NAME_          NVARCHAR2(10) 
PHONE          VARCHAR2(10) 
*/
--> �����(regDate) �÷� �߰�

ALTER TABLE members
	ADD (regDate DATE);
--> �÷��� �߰������� �ڷ�� NULL�� ä������.

SELECT * FROM members;
--> regDate �÷��� NULL�� ä���� �����̴�.
--> �ڷḦ �Է��Ϸ��� UPDATE ���� ���




--------------------------
/*
6. ���� ���̺��� ���� �� �ڷ��� ����
ALTER TABLE �������̺��
	MODIFY (�������̸� ���ο��ڷ���);
--> ���� ���̺� �����Ͱ� �ִ� ���� ���ο� �ڷ����� ���� �ڷῡ �����ؾ� �Ѵ�.
*/

DESC members;
/*
MID     NOT NULL NUMBER        
NAME_            NVARCHAR2(10) 
PHONE            VARCHAR2(20)  
REGDATE          DATE 
*/
--> PHONE �÷��� ũ�� Ȯ��. 10->20

ALTER TABLE members
	MODIFY (phone VARCHAR2(50));
    
DESC members;
--> phone �÷��� ũ�� Ȯ��

SELECT * FROM members;
--> ���� �ڷ� �״�� ���� �ִ�.




---------------------------------------
/*
7. ���� ���̺��� ���� �� �̸� ����
ALTER TABLE �������̺��
	RENAME COLUMN �������̸� TO �����̸�;
--> ���� ���̺� �����Ͱ� �־ �����ϴ�.
*/

DESC members;
/*
MID     NOT NULL NUMBER        
NAME_            NVARCHAR2(10) 
PHONE            VARCHAR2(20)  
REGDATE          DATE   
*/
--> phone �÷����� tel �÷������� ����

ALTER TABLE members
	RENAME COLUMN phone TO tel;
    
DESC members;
--> �÷����� ����Ǿ� �ִ�.




----------------------------------
/*
8. ���� ���̺��� ���� �� ����
ALTER TABLE �������̺��
	DROP (���̸�, ...);
--> ���� ���̺� ���� �����Ͱ� ���� �����ȴ�. ���� �Ұ�.
*/

DESC members;
/*
MID     NOT NULL NUMBER        
NAME_            NVARCHAR2(10) 
PHONE            VARCHAR2(20)  
REGDATE          DATE   
*/
--> regDate �÷��� ����

ALTER TABLE members
	DROP (regDate);
    
DESC members;
--> �÷��� �����Ǿ� �ִ�.




----------------------------------
/*
9. ���� ���̺� �̸� ����
RENAME �������̺�� TO ���ο����̺��;
*/

SELECT * FROM user_tables;
--> INSA ���̺���� EMP ���̺������ ����

SELECT * FROM insa;

RENAME insa TO emp;

SELECT * FROM emp;

RENAME test3 TO test_tbl;



----------------------------
/*
10. ���� ���̺� ���� (������ ��� ����)
DROP TABLE ���̺��̸�;
--> ���̺� ������ ���� ��ü(��������, �ε���, Ʈ���� ��)���� ���� �����ȴ�.
--> ���� ���谡 �ִ� ���̺��� �������ǿ� ���� ���� �ȵǴ� ��찡 �ִ�.
--> �ܵ� ���̺��� �׻� ���� ����.
*/

CREATE TABLE test1 (
    col NUMBER
);

INSERT INTO test1 (col) VALUES (1);
COMMIT;

SELECT * FROM test1;

SELECT * FROM user_tables;
--> test1 ���̺� ���� Ȯ��

DROP TABLE test1;  --������ ���. ���� ����.

SELECT * FROM user_tables;
--> test1 ���̺� ���� Ȯ��


DROP TABLE test_tbl;



------------------------------------------------
/*
--�����뿡 �ִ� ��ü Ȯ��
SELECT *
	FROM recyclebin;
    
--�����뿡 �ִ� ��ü ���� (���̺���� ������ ������ �ο��� �ӽ� ��ü��)
FLASHBACK TABLE ���̺�� TO BEFORE DROP;
--������ ����
PURGE recyclebin;
--���� ����
DROP TABLE ���̺��̸� PURGE;
*/


SELECT *
	FROM recyclebin;
--> ������ ���̺� ��ü Ȯ�� ����

FLASHBACK TABLE "BIN$GN3ZdqRIRwa9C4GaGAWpYg==$0" TO BEFORE DROP;

SELECT * FROM user_tables;
--> test1 ���̺� ���� Ȯ��

SELECT * FROM test1;
--> ���� �ڷ� Ȯ��


--������ ����
PURGE recyclebin;
--> ���� �Ұ�

--������ ����� ��ġ�� �ʰ� ���� ����
DROP TABLE test1 PURGE;
--> ���� �Ұ�


--�ý��ۿ� ��ϵ� ����� ���� Ȯ��
SELECT *
    FROM ALL_USERS;