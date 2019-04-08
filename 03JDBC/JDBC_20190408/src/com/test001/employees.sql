����1) hr.employees ���̺��� �Ի���(hire_date) ���� �߿��� 1, 2, 3�� �Ի��ڸ� ����ϴ� ���� �ۼ�. IN ������ ���.
SELECT * FROM hr.employees
WHERE TO_CHAR(hire_date,'MM') IN (01,02,03);

����2) hr.employees ���̺��� job_id�� Manager('SA_MAN', 'ST_MAN', 'PU_MAN', 'AC_MGR')�� ��� ����ϴ� ���� �ۼ�. IN ������ ���.
SELECT * FROM hr.employees
WHERE job_id in ('SA_MAN', 'ST_MAN', 'PU_MAN', 'AC_MGR');

����3) hr.employees ���̺��� �޿�(salary)�� 10000 �̻��̸鼭, Ŀ�̼�(commission_pct)�� ����(������ NULL�� ����) ��� ����ϴ� ���� �ۼ�. IS ������ ���.
SELECT * FROM  hr.employees
WHERE salary >= 10000
AND commission_pct IS NULL;

����4) hr.employees ���̺��� �޿�(salary) ���ؿ� ���� 'A', 'B', 'C' ������� ����ϴ� ���� �ۼ�. CASE~END ���� ���.
�޿��� 20000 �̻��� ��� 'A'
�޿��� 10000 �̻��� ��� 'B'
�޿��� 10000 �̸��� ��� 'C'
SELECT salary, CASE WHEN salary < 10000 THEN 'A'
                    WHEN salary >= 10000 AND salary <= 20000 THEN 'B'
                    ELSE 'C' END "rank"
FROM hr.employees;


����5) insa ���̺��� ��ȭ��ȣ(tel)�� ������ '-'�� �����ϰ� ����ϰ�, ������ '��ȭ��ȣ����'�� ����ϴ� ���� �ۼ�. DECODE, REPLACE �Լ� ���.
insa ���̺� ���� ����
SELECT tel, DECODE(tel, NULL, '��ȭ��ȣ����', REPLACE(tel,'-')) "tel_"
FROM insa;

����6) hr.employees ���̺��� �Ի���(hire_date) ���� �߿��� �⵵���� �ο��� ����ϴ� ���� �ۼ�. GROUP BY ����, COUNT �Լ� ���.
SELECT TO_CHAR(hire_date, 'YYYY'), COUNT(*) "total"
FROM hr.employees
GROUP BY TO_CHAR(hire_date, 'YYYY');

����7) insa ���̺��� ��ü�ο���, �����ο���, �����ο����� ���� ����ϴ� ���� �ۼ�. COUNT, DECODE �Լ� ���.
��� ��)
��ü�ο���   �����ο���   �����ο���
   60      31           29
SELECT COUNT(*) "total"
        ,COUNT(DECODE(SUBSTR(ssn, 8, 1), '1', 1, NULL)) "man"
        ,COUNT(DECODE(SUBSTR(ssn, 8, 1), '2', 1, NULL)) "woman"
FROM insa;


����8) hr.employees ���̺��� �޿�(salary)�� ���� �޴� �������� ������� ����ϵ�, ���� �ο��ؼ� ����ϴ� ���� �ۼ�. ROW_NUMBER() �Լ� ���.
SELECT salary, ROW_NUMBER() OVER(ORDER BY salary) "rank"
FROM hr.employees;

����9) hr.employees ���̺��� employee_id�� 150�� ����� ���� �޿��� �޴� ��� ����ϴ� ���� �ۼ�. ���� ���� ���.
SELECT salary
FROM hr.employees
WHERE salary = (SELECT salary FROM hr.employees WHERE employee_id = 150)

����10) hr.employees ���̺��� department_id�� 100�� ��� ���� �߿��� job_id, salary�� ��ġ�ϴ� ��� ����ϴ� ���� �ۼ�. ���� ���� ���.
SELECT *
FROM hr.employees
WHERE department_id = '100'
AND job_

����11) hr.employees ���̺��� ��� ���� ��½�, �μ���(hr.departments ���̺��� department_name)�� ���� ����ϴ� ���� �ۼ�. ���� �������� ���.


����12) hr.employees, hr.jobs ���̺��� ���� �����ϰ� job_title�� 'Stock Manager', 'Stock Clerk'�� ���� ���� ����ϴ� ���� �ۼ�. Oracle JOIN ǥ��� ���.



����13) hr.departments ���̺��� ������ ���� �μ� ���� ����ϴ� ���� �ۼ�. LEFT OUTER JOIN ���.  Oracle JOIN ǥ��� ���.


����14) hr.employees ���̺��� ���� ����(���� ������ ���� ����) ���� ����ϴ� ���� �ۼ�.  SELF JOIN + OUTER JOIN ���.  Oracle JOIN ǥ��� ���.



����15) �������� ���� �� Ư¡ ����.



����16) �Ʒ� �׸�(�÷�)���� ������ insa ���̺� ���� �� NUM �÷��� Primary Key ���� ���� ����, ���� �ڷ�(2�� ���� �ڷ�) �Է� ���� �ۼ�.
NUM      NOT NULL NUMBER(5)      --PK
NAME     NOT NULL NVARCHAR2(20) 
SSN      NOT NULL VARCHAR2(14)  
IBSADATE NOT NULL DATE          
TEL               VARCHAR2(15)  
CITY              NVARCHAR2(10) 
BUSEO    NOT NULL NVARCHAR2(15) 
JIKWI    NOT NULL NVARCHAR2(15) 
BASICPAY NOT NULL NUMBER(10)    
SUDANG   NOT NULL NUMBER(10) 



����17) employees, jobs ���̺��� job_title�� 'Stock Clerk'�� ������ �޿�(salary) 20% �λ�, job_title�� 'Stock Manager'�� ������ �޿�(salary) 10% �λ��ϴ� ���� �ۼ�. InlineView + JOIN + UPDATE ���.


����18) employees, jobs ���̺��� job_title�� 'Stock Clerk'�� ���� ���� �����ϴ� ���� �ۼ�. InlineView + JOIN + DELETE ���.
