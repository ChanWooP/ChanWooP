/*
ȸ���������� v1.0

1. �䱸�м�
- �ڷ� ������ Oracle �����ͺ��̽� �̿�
- ȸ�� ������ ���� �Է�, ��� ����
- ȸ�� ������ ȸ����ȣ(PK), �̸�, ��ȭ��ȣ, �̸��Ϸ� �����Ѵ�.
- ȸ����ȣ�� 'M001','M002',... ���·� �����.


2. ȭ�鼳��
--- ȸ�� ���� ���� ---
1.ȸ���������  2.ȸ�������Է�
����>2
ȸ����ȣ>M001
�̸�>hong
��ȭ��ȣ>010-1234-1234
�̸���>hong@naver.com
ȸ�������� �Է��ұ��(0/1)?1
ȸ�������� �ԷµǾ����ϴ�.

--- ȸ�� ���� ���� ---
1.ȸ���������  2.ȸ�������Է�
����>1
1.ȸ���������
----------------------------------------
ȸ����ȣ / �̸� / ��ȭ��ȣ / �̸���
M001 / hong / 010-1234-1234 / hong@naver.com
�� 1��

--- ȸ�� ���� ���� ---
1.ȸ���������  2.ȸ�������Է�
����>0
���α׷� ���� 

*/
--3. �����ͺ��̽� ����
--���̺� ����
CREATE TABLE members(
    mid VARCHAR2(4) -- PK, M001, M002~
    ,name_ NVARCHAR2(50)
    ,phone VARCHAR2(20)
    ,email VARCHAR2(50)
);

--��������
ALTER TABLE members
ADD CONSTRAINT mid_pk PRIMARY KEY(mid);

--���� �ڷ� �Է� ����
INSERT INTO members VALUES('M001','ȫ�浿','010-1111-1111','test@email.com');
INSERT INTO members VALUES('M002','�ڱ浿','010-1111-1111','test2@email.com');
INSERT INTO members VALUES('M003','Createsint','010-1111-1111','test3@email.com');
INSERT INTO members VALUES('M004','Daniel Aevan','010-1111-1111','test4@email.com');
COMMIT;
--��� ����
    --��ü ���
    SELECT mid, name_, phone, email
        FROM members
        ORDER BY mid;
    
    -- �˻� ��� 
    SELECT mid, name_, phone, email
        FROM members
        WHERE INSTR(LOWER(name_),LOWER('ȫ�浿')) > 0
        ORDER BY mid;