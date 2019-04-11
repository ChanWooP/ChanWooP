--Account 테이블
CREATE TABLE Account_ (
    accountId VARCHAR2(10) -- 계좌번호(pk)
    ,accountOwnerId VARCHAR2(5) -- 계좌주고유번호(fk)
    ,balance NUMBER -- 현재잔액
    ,accountCreateDate DATE -- 계좌개설일
    ,pw NUMBER -- 비밀번호
    ,lastUpdateDate DATE -- 마지막거래일
);
 
--AccountOwner 테이블
CREATE TABLE AccountOwner_ (
    accountOwnerId VARCHAR2(5) -- 계좌주고유번호(pk)
    ,accountOwnerName VARCHAR2(20) --계좌주이름
    ,accountOwnerPhone VARCHAR2(13) --계좌주전화번호
);
 
--AccountHistory 테이블
CREATE TABLE AccountHistory_ (
     accountId VARCHAR2(10) -- 계좌고유번호(fk)
     ,money NUMBER -- 거래금액
     ,inoutdate DATE -- 거래일
     ,gubun VARCHAR2(10) -- 구분(입/출금)
     ,balance NUMBER -- 잔액
);
 
--제약조건
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
 
--샘플 데이터 입력 쿼리
INSERT INTO AccountOwner_ (accountOwnerId,accountOwnerName,accountOwnerPhone)
VALUES('A001', '홍길동', '010-1111-1111');
INSERT INTO AccountOwner_ (accountOwnerId,accountOwnerName,accountOwnerPhone)
VALUES('A002', '유리야', '010-1111-1112');
 
INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) 
VALUES('111-1111-1', 'A001', 50000, '2019-03-03', '1234', '2019-04-01');
INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) 
VALUES('111-1111-2', 'A001', 40000, '2019-03-03', '1234', '2019-04-01');
INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) 
VALUES('111-1111-3', 'A002', 60000, '2019-03-01', '1234', '2019-03-20');
 
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-1',80000,'2019-03-03','입금',80000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-1',30000,'2019-04-01','출금',50000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-2',30000,'2019-03-03','입금',30000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-2',10000,'2019-03-03','입금',40000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-3',60000,'2019-03-01','입금',60000);
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
VALUES('111-1111-3',30000,'2019-04-01','출금',30000);
 
 
COMMIT;

--사용자
--사용자 / 계좌조회
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    AND a1.accountId = '111-1111-1'
    ORDER BY a1.accountId;

--사용자 / 계좌입금
--1단계
--현재 잔액 + 입금액 -> 신규잔액
--SELECT
SELECT balance 
    FROM Account_ 
    WHERE accountID = '111-1111-1';
--2단계
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
    VALUES('111-1111-1',80000, SYSDATE,'입금', 신규잔액);
--3단계
--계좌정보 테이블에서 잔액, 마지막거래일을 수정하는 쿼리
--UPDATE ...
UPDATE Account_
    SET balance = (현재잔액+입금액)
        ,lastupdatedate = SYSDATE
    WHERE accountID = '111-1111-1';



--사용자 / 계좌출금
--1단계
--계좌정보 테이블에서 비밀번호 확인 -> 현재 잔액 반환
--SELECT
SELECT pw 
    FROM Account_ 
    WHERE accountid = '111-1111-1';
    
SELECT balance 
    FROM Account_ 
    WHERE accountID = '111-1111-1';
--2단계
--현재 잔액 - 출금액 -> 신규잔액
INSERT INTO AccountHistory_(accountId,money,inoutdate,gubun,balance)
    VALUES('111-1111-1',20000, SYSDATE,'출금', 신규잔액);
--3단계
--계좌정보 테이블에서 잔액, 마지막거래일을 수정하는 쿼리
--UPDATE ...
UPDATE Account_
    SET balance = (현재잔액-출금액)
        ,lastupdatedate = SYSDATE
    WHERE accountID = '111-1111-1';


--관리자
--관리자 / 계좌생성
--계좌번호 자동 생성
--샘플 계좌번호 : 111-1111-1
--앞 3자리는 고유번호
--중간 4자리는 일련번호
--뒤 1자리는 일련번호
--예) 111-1111-1, 111-1111-2, ... 111-1111-9, 111-1112-0. ...
--자동생성) 1111-1 => 11111+1 => 11112 => 1111-2 => 111-1111-2
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

--관리자 / 계좌조회
--관리자 / 계좌조회 / 전체계좌
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    ORDER BY a1.accountId;
--관리자 / 계좌조회 / 계좌조회
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    AND a1.accountId = '111-1111-1'
    ORDER BY a1.accountId;
--관리자 / 계좌조회 / 계좌주
SELECT a1.accountId, a1.balance, a1.accountCreateDate, a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone
    FROM Account_ a1, AccountOwner_ a2
    WHERE a1.accountOwnerId = a2.accountOwnerId
    AND a2.accountOwnerName = '홍길동'
    AND a2.accountOwnerPhone = '010-1111-1111'
    ORDER BY a1.accountId;