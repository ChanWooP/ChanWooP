--Account ���̺�
CREATE TABLE Account_ (
    accountId VARCHAR2(10) -- ���¹�ȣ(pk)
    ,accountOwnerId VARCHAR2(5) -- �����ְ�����ȣ(fk)
    ,balance NUMBER -- �����ܾ�
    ,accountCreateDate DATE -- ���°�����
    ,pw NUMBER -- ��й�ȣ
    ,lastUpdateDate DATE -- �������ŷ���
);
 
--AccountOwner ���̺�
CREATE TABLE AccountOwner_ (
    accountOwnerId VARCHAR2(5) -- �����ְ�����ȣ(pk)
    ,accountOwnerName VARCHAR2(20) --�������̸�
    ,accountOwnerPhone VARCHAR2(13) --��������ȭ��ȣ
);
 
--AccountHistory ���̺�
CREATE TABLE AccountHistory_ (
     accountId VARCHAR2(10) -- ���°�����ȣ(fk)
     ,money NUMBER -- �ŷ��ݾ�
     ,inoutdate DATE -- �ŷ���
     ,gubun VARCHAR2(10) -- ����(��/���)
     ,balance NUMBER -- �ܾ�
);
 
--��������
ALTER TABLE Account_
ADD CONSTRAINT accountId_pk PRIMARY KEY(accountId);
 
ALTER TABLE AccountOwner_
ADD CONSTRAINT accountOwnerId_pk PRIMARY KEY(accountOwnerId);
 
ALTER TABLE Account_
ADD CONSTRAINT accountOwnerId_fk FOREIGN KEY(accountOwnerId)
REFERENCES AccountOwner_(accountOwnerId);
 
ALTER TABLE AccountHistory_
ADD CONSTRAINT accountId_fk FOREIGN KEY(accountId)
REFERENCES Account_(accountId);
 
--���� ������ �Է� ����
INSERT INTO AccountOwner_ (accountOwnerId,accountOwnerName,accountOwnerPhone)
VALUES('A001', 'ȫ�浿', '010-1111-1111');
INSERT INTO AccountOwner_ (accountOwnerId,accountOwnerName,accountOwnerPhone)
VALUES('A002', '������', '010-1111-1112');
 
INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) 
VALUES('111-1111-1', 'A001', 50000, '2019-03-03', '1234', '2019-04-01');
INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) 
VALUES('111-1111-2', 'A001', 40000, '2019-03-03', '1234', '2019-04-01');
INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) 
VALUES('111-1111-3', 'A002', 60000, '2019-03-01', '1234', '2019-03-20');
 
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-1',80000,'2019-03-03','�Ա�',80000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-1',30000,'2019-04-01','���',50000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-2',30000,'2019-03-03','�Ա�',30000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-2',10000,'2019-03-03','�Ա�',40000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-3',60000,'2019-03-01','�Ա�',60000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-3',30000,'2019-04-01','���',30000);
 
 
COMMIT;

--�����
--����� / ������ȸ
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    AND a1.accountId = '111-1111-1'
    ORDER BY a1.accountId;

--����� / �����Ա�
--1�ܰ�
--���� �ܾ� + �Աݾ� -> �ű��ܾ�
--SELECT
SELECT balance 
    FROM Account_ 
    WHERE accountID = '111-1111-1';
--2�ܰ�
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
    VALUES('111-1111-1',80000, SYSDATE,'�Ա�', �ű��ܾ�);
--3�ܰ�
--�������� ���̺��� �ܾ�, �������ŷ����� �����ϴ� ����
--UPDATE ...
UPDATE Account_
    SET balance = (�����ܾ�+�Աݾ�)
        ,lastupdatedate = SYSDATE
    WHERE accountID = '111-1111-1';



--����� / �������
--1�ܰ�
--�������� ���̺��� ��й�ȣ Ȯ�� -> ���� �ܾ� ��ȯ
--SELECT
SELECT pw 
    FROM Account_ 
    WHERE accountid = '111-1111-1';
    
SELECT balance 
    FROM Account_ 
    WHERE accountID = '111-1111-1';
--2�ܰ�
--���� �ܾ� - ��ݾ� -> �ű��ܾ�
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
    VALUES('111-1111-1',20000, SYSDATE,'���', �ű��ܾ�);
--3�ܰ�
--�������� ���̺��� �ܾ�, �������ŷ����� �����ϴ� ����
--UPDATE ...
UPDATE Account_
    SET balance = (�����ܾ�-��ݾ�)
        ,lastupdatedate = SYSDATE
    WHERE accountID = '111-1111-1';


--������
--������ / ���»���
--���¹�ȣ �ڵ� ����
--���� ���¹�ȣ : 111-1111-1
--�� 3�ڸ��� ������ȣ
--�߰� 4�ڸ��� �Ϸù�ȣ
--�� 1�ڸ��� �Ϸù�ȣ
--��) 111-1111-1, 111-1111-2, ... 111-1111-9, 111-1112-0. ...
--�ڵ�����) 1111-1 => 11111+1 => 11112 => 1111-2 => 111-1111-2
SELECT MAX(accountId)
    FROM account_
SELECT REPLACE(SUBSTR(MAX(accountId),5), '-')
    FROM account_
SELECT REPLACE(SUBSTR(MAX(accountId),5), '-') + 1
    FROM account_
SELECT SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,1,4)
    FROM account_  
SELECT SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,5)
    FROM account_  
SELECT SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,1,4)
        || '-'
        || SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,5)
    FROM account_
SELECT CONCAT('111-',SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,1,4)
        || '-'
        || SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,5)) "NewAccountId"
    FROM account_

--������ / ������ȸ
--������ / ������ȸ / ��ü����
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    ORDER BY a1.accountId;
--������ / ������ȸ / ������ȸ
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    AND a1.accountId = '111-1111-1'
    ORDER BY a1.accountId;
--������ / ������ȸ / ������
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    AND a2.accountOwnerName = 'ȫ�浿'
    AND a2.accountOwnerPhone = '010-1111-1111'
    ORDER BY a1.accountId;