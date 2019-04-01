-----------------------------------------------------------
--데이터베이스 객체

/*
1. 데이터베이스에 저장되는 것들은 테이블 외에 기타 여러 가지 것들이 저장되는데 이것을 데이터베이스 객체(Database Objects)라고 부른다.
2. TABLE
하나 또는 여러 컬럼(Column)들이 모여 하나의 레코드를 이루며, 이러한 레코드들이 모여 테이블이된다. 예를 들어 "사원" 테이블은 사번, 이름, 부서 등 여러 컬럼을 갖게 되고 사원수만큼의 레코드를 갖게 된다.
3.
CREATE 문
데이터베이스 내의 모든 객체를 생성할 때 사용하는 문장
ALTER 문
이미 생성된 객체의 구조를 변경
DROP 문
생성된 객체를 삭제
=>주의) 관리자에 의해 RESOURCE 권한을 부여 받은 사용자만 작업 가능
*/

    
    
-----------------------------------------------
--테이블 관리

/*
***1. 새 테이블 생성
CREATE TABLE 테이블이름 (
	컬럼 자료형 [기타제약]
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
2. 기존 테이블 구조(컬럼명, 자료형) 확인
SELECT * 
  FROM user_tab_columns
  WHERE table_name='테이블명'; --테이블 이름 대문자로 표기할 것.
DESC 테이블이름;
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
3. 기존 테이블 구조 복사해서 새 테이블 생성 (제약조건은 복사되지 않는다)
CREATE TABLE 새테이블명
AS
SELECT 컬럼리스트 FROM 기존테이블명 
WHERE 1=0;
*/


CREATE TABLE jobs
AS
SELECT * FROM hr.jobs WHERE 1 = 0;



CREATE TABLE members2
AS
SELECT * FROM members WHERE 1=0;  --조건 결과가 거짓이 나오도록 설정

DESC members;  --원본은 구조 외에 제약조건도 있다.
DESC members2; --사본은 구조만 복사되어 있다.

SELECT * FROM members2; -- 0 rows




------------------------------------
/*
**4. 기존 테이블 구조 및 기존 자료 복사해서 새 테이블 생성 및 자료 입력 (제약조건은 복사되지 않는다)
CREATE TABLE 새테이블명
AS
SELECT 컬럼리스트 FROM 기존테이블명 [WHERE 조건식];
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
5. 기존 테이블에 새 열 추가
ALTER TABLE 기존테이블명
	ADD (열이름 자료형, ...);
--> 기존 테이블에 데이터가 있는 경우는 새로 만들어진 컬럼의 데이터는 NULL만 채워진다.
--> NULL이 채워진 컬럼에 자료를 채우려면 UPDATE 명령을 이용한다.
--> 컬럼 생성시 자동으로 기본값을 채우려면 DEFAULT 키워드를 이용한다.
*/

DESC members;
/*
MID   NOT NULL NUMBER        
NAME_          NVARCHAR2(10) 
PHONE          VARCHAR2(10) 
*/
--> 등록일(regDate) 컬럼 추가

ALTER TABLE members
	ADD (regDate DATE);
--> 컬럼은 추가되지만 자료는 NULL로 채워진다.

SELECT * FROM members;
--> regDate 컬럼은 NULL로 채워진 상태이다.
--> 자료를 입력하려면 UPDATE 쿼리 사용




--------------------------
/*
6. 기존 테이블에서 기존 열 자료형 변경
ALTER TABLE 기존테이블명
	MODIFY (기존열이름 새로운자료형);
--> 기존 테이블에 데이터가 있는 경우는 새로운 자료형이 기존 자료에 적합해야 한다.
*/

DESC members;
/*
MID     NOT NULL NUMBER        
NAME_            NVARCHAR2(10) 
PHONE            VARCHAR2(20)  
REGDATE          DATE 
*/
--> PHONE 컬럼의 크기 확장. 10->20

ALTER TABLE members
	MODIFY (phone VARCHAR2(50));
    
DESC members;
--> phone 컬럼의 크기 확인

SELECT * FROM members;
--> 기존 자료 그대로 남아 있다.




---------------------------------------
/*
7. 기존 테이블에서 기존 열 이름 변경
ALTER TABLE 기존테이블명
	RENAME COLUMN 기존열이름 TO 새열이름;
--> 기존 테이블에 데이터가 있어도 가능하다.
*/

DESC members;
/*
MID     NOT NULL NUMBER        
NAME_            NVARCHAR2(10) 
PHONE            VARCHAR2(20)  
REGDATE          DATE   
*/
--> phone 컬럼명은 tel 컬럼명으로 변경

ALTER TABLE members
	RENAME COLUMN phone TO tel;
    
DESC members;
--> 컬럼명이 변경되어 있다.




----------------------------------
/*
8. 기존 테이블에서 기존 열 삭제
ALTER TABLE 기존테이블명
	DROP (열이름, ...);
--> 기존 테이블에 기존 데이터가 같이 삭제된다. 복구 불가.
*/

DESC members;
/*
MID     NOT NULL NUMBER        
NAME_            NVARCHAR2(10) 
PHONE            VARCHAR2(20)  
REGDATE          DATE   
*/
--> regDate 컬럼을 삭제

ALTER TABLE members
	DROP (regDate);
    
DESC members;
--> 컬럼이 삭제되어 있다.




----------------------------------
/*
9. 기존 테이블 이름 변경
RENAME 기존테이블명 TO 새로운테이블명;
*/

SELECT * FROM user_tables;
--> INSA 테이블명을 EMP 테이블명으로 변경

SELECT * FROM insa;

RENAME insa TO emp;

SELECT * FROM emp;

RENAME test3 TO test_tbl;



----------------------------
/*
10. 기존 테이블 삭제 (휴지통 기능 있음)
DROP TABLE 테이블이름;
--> 테이블 삭제시 관련 객체(제약조건, 인덱스, 트리거 등)들이 같이 삭제된다.
--> 참조 관계가 있는 테이블은 제약조건에 따라서 삭제 안되는 경우가 있다.
--> 단독 테이블은 항상 삭제 가능.
*/

CREATE TABLE test1 (
    col NUMBER
);

INSERT INTO test1 (col) VALUES (1);
COMMIT;

SELECT * FROM test1;

SELECT * FROM user_tables;
--> test1 테이블 존재 확인

DROP TABLE test1;  --휴지통 사용. 복구 가능.

SELECT * FROM user_tables;
--> test1 테이블 삭제 확인


DROP TABLE test_tbl;



------------------------------------------------
/*
--휴지통에 있는 객체 확인
SELECT *
	FROM recyclebin;
    
--휴지통에 있는 객체 복원 (테이블명은 휴지통 내에서 부여된 임시 객체명)
FLASHBACK TABLE 테이블명 TO BEFORE DROP;
--휴지통 비우기
PURGE recyclebin;
--완전 삭제
DROP TABLE 테이블이름 PURGE;
*/


SELECT *
	FROM recyclebin;
--> 삭제한 테이블 객체 확인 가능

FLASHBACK TABLE "BIN$GN3ZdqRIRwa9C4GaGAWpYg==$0" TO BEFORE DROP;

SELECT * FROM user_tables;
--> test1 테이블 존재 확인

SELECT * FROM test1;
--> 기존 자료 확인


--휴지통 비우기
PURGE recyclebin;
--> 복원 불가

--휴지통 기능을 거치지 않고 직접 삭제
DROP TABLE test1 PURGE;
--> 복원 불가


--시스템에 등록된 사용자 계정 확인
SELECT *
    FROM ALL_USERS;