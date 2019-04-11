�������� v2.0 (�ܼ� + �����ͺ��̽� ����)



----------------------
1. �䱸�м�
- ������������ ���������� ���� �Է�, ���, �˻�, ���� ��� ����.
- ���������������� ��������, �μ�����, �������� ���� �.
- ������������������ �н����庯�� ��� ����.
- Ư�� ���� �Է½� �׸��ȣ ������ �ڵ� �����ǵ��� �Ѵ�.
- �������� �Է�, ��� �׸��� ������ȣ, ������ �̴�.
- �μ����� �Է�, ��� �׸��� �μ���ȣ, �μ��� �̴�.
- �������� �Է�, ��� �׸��� ������ȣ, ������, �ּұ⺻�� �̴�.
- �������� �Է� �׸��� �̸�, �ֹι�ȣ, �Ի���, ��ȭ��ȣ, ������ȣ, �μ���ȣ, ������ȣ, �⺻��, ���� �̴�.
- �������� ��� �׸��� ���, �̸�, �ֹι�ȣ, �Ի���, ��ȭ��ȣ, ������, �μ���, ������, �⺻��, ����, �޿��� �����ȴ�.
- �������� ��ü��½� ���� ����(���, �̸�, ������, �μ���, ������)�� ��µǵ��� �Ѵ�.
- �������� �˻��� �˻� ����(���, �̸�, ������, �μ���, ������)�� ��µǵ��� �Ѵ�.
- ����Ŭ �����ͺ��̽��� �̿��ؼ� �ڷ� ����
- PreparedStatement �������̽� ���





----------------------
2. �����ͺ��̽� �غ�

CREATE TABLE login (
	id_ VARCHAR2(20) --PK
	, pw_ VARCHAR2(20)
);

ALTER TABLE login
 ADD CONSTRAINT login_id_pk PRIMARY KEY(id_);

INSERT INTO login (id_, pw_)
    VALUES ('admin', '1234');
COMMIT;

SELECT * from login
CREATE TABLE regions (
	regId VARCHAR2(5)   --PK. REG01, REG02, ...
	,reg_name  NVARCHAR2(20)
);

ALTER TABLE regions
 ADD CONSTRAINT regions_regId_pk PRIMARY KEY(regId);


INSERT INTO regions (regId, reg_name)
    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) 
	AS newId FROM regions), '����');
    INSERT INTO regions (regId, reg_name)
    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) 
	AS newId FROM regions), '��õ');
    INSERT INTO regions (regId, reg_name)
    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) 
	AS newId FROM regions), '�λ�');
COMMIT;


CREATE TABLE departments (
	deptId VARCHAR2(67)   --PK. DEPT01, DEPT02, ...
	,dept_name  NVARCHAR2(20) NOT NULL
);

ALTER TABLE departments
 ADD CONSTRAINT departments_deptId_pk PRIMARY KEY(deptId);


INSERT INTO departments (deptId, dept_name)
    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) 
	AS newId FROM departments), '���ߺ�');
INSERT INTO departments (deptId, dept_name)
    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) 
	AS newId FROM departments), '������');
INSERT INTO departments (deptId, dept_name)
    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) 
	AS newId FROM departments), 'ȸ���');
COMMIT;




CREATE TABLE jobs (
	jobId VARCHAR2(5)   --PK. JOB01, JOB02, ...
	,job_title  NVARCHAR2(20) NOT NULL
	,min_basicPay NUMBER
);

ALTER TABLE jobs
 ADD CONSTRAINT jobs_jobId_pk PRIMARY KEY(jobId);


INSERT INTO jobs (jobId, job_title, min_basicPay)
    VALUES ((SELECT CONCAT('JOB', LPAD(NVL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0)) 
        AS newId FROM jobs), '���', 2000000);
INSERT INTO jobs (jobId, job_title, min_basicPay)
    VALUES ((SELECT CONCAT('JOB', LPAD(NVL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0)) 
        AS newId FROM jobs), '����', 3000000);
INSERT INTO jobs (jobId, job_title, min_basicPay)
    VALUES ((SELECT CONCAT('JOB', LPAD(NVL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0)) 
        AS newId FROM jobs), '����', 4000000);
COMMIT;



CREATE TABLE employees (
        empId VARCHAR2(6) --PK. EMP001, EMP002, ...
       ,name_ NVARCHAR2(20) NOT NULL
       ,ssn  VARCHAR2(14) NOT NULL
       ,hiredate DATE     NOT NULL
       ,phone   VARCHAR2(15)
       ,regId VARCHAR2(5)
       ,deptId VARCHAR2(6)
       ,jobId VARCHAR2(5)
       ,basicpay NUMBER NOT NULL
       ,extrapay NUMBER NOT NULL
);

ALTER TABLE employees
    ADD (CONSTRAINTS employees_empid_pk PRIMARY KEY(empId)
        , CONSTRAINTS employees_regId_fk FOREIGN KEY(regId)
            REFERENCES regions(regId)
        , CONSTRAINTS employees_deptId_fk FOREIGN KEY(deptId)
            REFERENCES departments(deptId)
        , CONSTRAINTS employees_jobId_fk FOREIGN KEY(jobId)
            REFERENCES jobs(jobId));


INSERT INTO employees  (empId, name_, ssn, hiredate, phone
    , regId, deptId, jobId, basicpay, extrapay)
    VALUES ((SELECT CONCAT('EMP', LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) 
        AS newId FROM employees), 'ȫ�浿', '901010-1234567', '2010-10-10'
        , '010-1234-1234', 'REG01', 'DEPT01', 'JOB02', 3000000, 1000000);
INSERT INTO employees  (empId, name_, ssn, hiredate, phone
    , regId, deptId, jobId, basicpay, extrapay)
    VALUES ((SELECT CONCAT('EMP', LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) 
        AS newId FROM employees), '����õ', '991110-1234569', '2013-10-10'
        , '010-1234-1234', 'REG01', 'DEPT03', 'JOB01', 2000000, 1000000);
INSERT INTO employees  (empId, name_, ssn, hiredate, phone
    , regId, deptId, jobId, basicpay, extrapay)
    VALUES ((SELECT CONCAT('EMP', LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) 
        AS newId FROM employees), '���̸�', '701010-1234567', '2000-12-10'
        , '010-1234-1234', 'REG02', 'DEPT01', 'JOB03', 4000000, 1000000);
COMMIT;

select * from employees
----------------------
3. ȭ�� ����
>>�������� v2.0
1.�α���
����(0/1)>1
���̵�>admin
�н�����>1111
������ 'admin' �α��εǾ����ϴ�.


>>�������� v2.0
1.��������  2.������������  3.��������������
����>2


>>���� ���� v2.0 > ���� ���� ����
1.��������  2.�μ�����  3.��������
����>1


>>���� ���� v2.0 > ���� ���� ���� > ���� ����
1.�����Է�  2.�������  3.��������
����>1


>>���� ���� v2.0 > ���� ���� ���� > ���� ���� > ���� �Է�
--------------
������ȣ / ������
REG01 / ����
REG02 / ���
REG03 / �泲
REG04 / ���
--------------
�ű� ���� �̸�>����
�ű� ���� ���� �Է� �Ϸ�!


>>���� ���� v2.0 > ���� ���� ���� > ���� ����
1.�����Է�  2.�������  3.��������
����>2
--------------
������ȣ / ������
REG01 / ����
REG02 / ���
REG03 / �泲
REG04 / ���
REG05 / ����
--------------


>>���� ���� v2.0 > ���� ���� ���� > ���� ����
1.�����Է�  2.�������  3.��������
����>3
--------------
������ȣ / ������ / �������ɿ���
REG01 / ���� / X
REG02 / ��� / X
REG03 / �泲 / X
REG04 / ��� / O
REG05 / ���� / X
--------------
������ȣ>REG04
REG04 ������ �����Ǿ����ϴ�.


>>���� ���� v2.0 > ���� ���� ���� > ���� ����
1.�����Է�  2.�������  3.��������
����>0


>>���� ���� v2.0 > ���� ���� ����
1.��������  2.�μ�����  3.��������
����>0


>>���� ���� v2.0
1.��������  2.������������  3.��������������
����>1


>>���� ���� v2.0 > ���� ����
1.�����Է�  2.������ü���  3.�����˻�  4.��������
����>1


>>���� ���� v2.0 > ���� ���� > ���� �Է�
�̸�>�ڱ浿
�ֹι�ȣ(YYMMDD-NNNNNNN)>800110-1234567
�Ի���(YYYY-MM-DD)>2010-11-31
��¥ ������ Ʋ�Ƚ��ϴ�.
�Ի���(YYYY-MM-DD)>2010-11-30
��ȭ��ȣ(010-XXXX-XXXX)>010-3456-6789
--------------
������ȣ / ������
REG01 / ����
REG02 / ���
REG03 / �泲
REG04 / ���
REG05 / ����
--------------
������ȣ>REG06
������ȣ�� ������ �ƴմϴ�.
������ȣ>REG02
--------------
�μ���ȣ / �μ���
DEPT01 / ���ߺ�
DEPT02 / ��ȹ��
DEPT03 / ������
--------------
�μ���ȣ>DEPT03
--------------
������ȣ / ������ / �ּұ⺻��
JOB01 / ���� / 2000000
JOB02 / �븮 / 1500000
JOB03 / ��� / 1000000
--------------
������ȣ>JOB01
�⺻��>2000000
����>100000
���� ���� �Է� �Ϸ�!


>>���� ���� v2.0 > ���� ����
1.�����Է�  2.������ü���  3.�����˻�  4.��������
����>2


>>���� ���� v2.0 > ���� ���� > ���� ��ü ���
1.�������  2.�̸�����  3.����������  4.�μ�������  5.����������
����>1


>>���� ���� v2.0 > ���� ���� > ���� ��ü ��� > ��� ����
��ü �ο�: 62��
--------------
��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�
EMP001 / ȫ�浿 / 771212-1022432 / 1998-10-11 / 011-2356-4528 / ���� / ��ȹ�� / ���� / 2,610,000 / 200,000 / 2,810,000
EMP002 / �̼��� / 801007-1544236 / 2000-11-29 / 010-4758-6532 / ��� / �ѹ��� / ��� / 1,320,000 / 200,000 / 1,520,000
...


>>���� ���� v2.0 > ���� ���� > ���� ��ü ���
1.�������  2.�̸�����  3.����������  4.�μ�������  5.����������
����>0


>>���� ���� v2.0 > ���� ����
1.�����Է�  2.������ü���  3.�����˻�  4.��������
����>0


>>���� ���� v2.0
1.��������  2.������������  3.��������������
����>3


>>���� ���� v2.0 > ��������������
1.�н����庯��
����>1
�����н�����>1111
�ű��н�����>1234
�н����带 �����ұ��(0/1)>1
������ 'admin'�� �н����尡 ����Ǿ����ϴ�.

>>���� ���� v2.0 > ��������������
1.�н����庯��
����>0


>>���� ���� v2.0
1.��������  2.������������  3.��������������
����>0
������ 'admin' �α׾ƿ��Ǿ����ϴ�.


>>�������� v2.0
1.�α���
����(0/1)>0

���α׷��� ����Ǿ����ϴ�.