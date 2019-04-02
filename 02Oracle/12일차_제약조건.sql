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

    
---------------------------------------------
--무결성

/*
1. 무결성에는 개체 무결성(Entity Integrity), 참조 무결성(Relational Integrity), 도메인 무결성(Domain Integrity)가 있다.
2. 개체 무결성
개체 무결성은 릴레이션에 저장되는 튜플(tuple)의 유일성을 보장하기 위한 제약조건이다.
3.참조 무결성
참조 무결성은 릴레이션 간의 데이터의 일관성을 보장하기 위한 제약조건이다.
4. 도메인 무결성
도메인 무결성은 속성에서 허용 가능한 값의 범위를 지정하기 위한 제약조건이다.
예를 들어, 학생 정보 저장용 테이블을 만든다면
--테이블 생성
CREATE TABLE member (     --테이블 이름 member
	sid NUMBER        --고유번호 저장용 컬럼
	,name VARCHAR2(10) --이름, 10글자만 허용
	,kor NUMBER(3)     --국어, 숫자 3자리만 허용(0~999). 0~100 제한.
	,eng NUMBER(3)     --영어, 숫자 3자리만 허용
	,mat NUMBER(3)     --수학, 숫자 3자리만 허용
);
5. 제약조건 종류
- PRIMARY KEY(PK) : 해당 컬럼 값은 반드시 존재해야 하며, 유일해야 함(NOT NULL과 UNIQUE 제약조건을 결합한 형태)
- FOREIGN KEY(FK) : 해당 컬럼 값은 참조되는 테이블의 컬럼 값 중의 하나와 일치하거나 NULL을 가짐
- UNIQUE(UK) : 테이블내에서 해당 컬럼 값은 항상 유일해야 함
- NOT NULL : 컬럼은 NULL 값을 포함할 수 없다.
- CHECK(CK) : 해당 컬럼에 저장 가능한 데이터 값의 범위나 조건 지정
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
--DEFAULT 키워드
--사용자가 명시적인 값을 입력하지 않는 경우
--자동으로 지정된 값을 입력하는 설정
--NOT NULL 제약 지정된 컬럼에 자동으로 값 입력시 유용.

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
--제약 분석 쿼리

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
SELECT utc.table_name AS table_name --제약이 지정된 테이블명
	, utc.column_name AS column_name --제약이 지정된 컬럼명
	, data_type   --제약이 지정된 컬럼의 자료형
	, data_length   --제약이 지정된 컬럼의 크기(length)
	, data_precision   --제약이 지정된 컬럼의 크기(precision)
	, nullable    --제약이 지정된 컬럼의 NULL 허용 여부
	, data_default    --제약이 지정된 컬럼의 DEFAULT 값
	, constraint_name    --제약명
	, constraint_type     --제약종류
	, search_condition  --CHECK 제약인 경우 조건식
	, delete_rule    --FOREIGN KEY 제약인 경우 ON DELETE CASCADE 지정 여부
	, r_table_name   --FOREIGN KEY 제약인 경우 참조 테이블명
	, r_conlumn_name   --FOREIGN KEY 제약인 경우 참조 컬럼명
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
  
  
--특정 테이블에 추가된 제약의 종류 확인하는 쿼리  
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
  
  

--특정 테이블에 추가된 제약의 종류 확인하는 쿼리  -> 뷰로 등록 (뷰 생성 권한 필요)
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
  

--뷰 등록후 제약 조건 확인 쿼리 실행
SELECT *  
    FROM constraintsCheckView
  WHERE table_name='EMPLOYEES';
  
  
  
  
  
-------------------------------------------------------
--ON DELETE CASCADE 옵션
-- FK 제약 지정시 부모 테이블의 row(PK)가 삭제될 때 자식 테이블의 row(FK)의 삭제 여부 결정




---------------------------
--ON DELETE CASCADE 옵션 지정 전
CREATE TABLE jobs (
	jikwi_id NUMBER
	,jikwi_name VARCHAR2(10)
	,CONSTRAINT JOBS_JIKWI_ID_PK PRIMARY KEY(jikwi_id)
);

INSERT INTO jobs (jikwi_id, jikwi_name) VALUES (1, '과장');
INSERT INTO jobs (jikwi_id, jikwi_name) VALUES (2, '대리');
INSERT INTO jobs (jikwi_id, jikwi_name) VALUES (3, '사원');
COMMIT;


--FK 제약 지정
CREATE TABLE employees (
	sid NUMBER PRIMARY KEY
	,name VARCHAR2(10) 
	,jikwi_id NUMBER REFERENCES jobs(jikwi_id)
);

INSERT INTO employees (sid, name, jikwi_id)
	VALUES (1, '홍길동', 1);
INSERT INTO employees (sid, name, jikwi_id)
	VALUES (2, '김길동', 2);
COMMIT;

--employees 테이블 자료 확인
SELECT * FROM employees;

--employees 테이블에서 '김길동' 자료 삭제 시도. --O
DELETE FROM employees WHERE sid=2;

--employees 테이블 자료 확인
SELECT * FROM employees;

--jobs 테이블 자료 확인
SELECT * FROM jobs;

--jobs 테이블에서 '과장' 자료 삭제 시도. --X
DELETE FROM jobs WHERE jikwi_id=1;

--jobs 테이블에서 '사원' 자료 삭제 시도. --O
DELETE FROM jobs WHERE jikwi_id=3;

--jobs 테이블 자료 확인
SELECT * FROM jobs;

--> ON DELETE CASCADE 옵션 지정이 필요합니다.
--> employees 테이블에서 FK 제약 제거 후 다시 FK 제약 지정
SELECT * FROM user_constraints WHERE table_name='EMPLOYEES';
--> DELETE_RULE 컬럼의 값 확인 -> NO_ACTION -> ON DELETE CASCADE 옵션 지정 X -> 부모 row 삭제시, 자식 row가 같이 삭제되지 않는다. 기본값.


--기존 FK 제약 삭제
ALTER TABLE employees
    DROP CONSTRAINT SYS_C007031;
    
----------------------------
--ON DELETE CASCADE 옵션 지정 후
ALTER TABLE employees
	ADD CONSTRAINT EMPLOYEES_JIKWI_ID_FK
		FOREIGN KEY (jikwi_id)
		REFERENCES jobs(jikwi_id)
		ON DELETE CASCADE;


SELECT * FROM user_constraints WHERE table_name='EMPLOYEES';
--> DELETE_RULE 컬럼의 값 확인 -> CASCADE -> ON DELETE CASCADE 옵션 지정 O -> 부모 row 삭제시, 자식 row가 같이 삭제될 수 있다.

SELECT * FROM jobs;
SELECT * FROM employees;
        
--jobs 테이블에서 '과장' 자료 삭제 시도. --O
DELETE FROM jobs WHERE jikwi_id=1;
--> employees 테이블의 자료가 같이 삭제된다.

SELECT * FROM jobs;
SELECT * FROM employees;



--DELETE_RULE 확인을 위한 사용자 뷰 생성
CREATE OR REPLACE VIEW constraintsCheckView
AS
SELECT utc.table_name AS table_name --제약이 지정된 테이블명
	, utc.column_name AS column_name --제약이 지정된 컬럼명
	, data_type   --제약이 지정된 컬럼의 자료형
	, data_length   --제약이 지정된 컬럼의 크기(length)
	, data_precision   --제약이 지정된 컬럼의 크기(precision)
	, nullable    --제약이 지정된 컬럼의 NULL 허용 여부
	, data_default    --제약이 지정된 컬럼의 DEFAULT 값
	, constraint_name    --제약명
	, constraint_type     --제약종류
	, search_condition  --CHECK 제약인 경우 조건식
	, delete_rule    --FOREIGN KEY 제약인 경우 ON DELETE CASCADE 지정 여부
	, r_table_name   --FOREIGN KEY 제약인 경우 참조 테이블명
	, r_conlumn_name   --FOREIGN KEY 제약인 경우 참조 컬럼명
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

--뷰 등록후 제약 조건 확인 쿼리 실행
SELECT *  
    FROM constraintsCheckView
  WHERE table_name='EMPLOYEES';