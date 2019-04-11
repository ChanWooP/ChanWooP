직원관리 v2.0 (콘솔 + 데이터베이스 버전)



----------------------
1. 요구분석
- 직원관리에서 직원정보에 대한 입력, 출력, 검색, 삭제 기능 구현.
- 기초정보관리에서 지역관리, 부서관리, 직위관리 별도 운영.
- 관리자정보관리에서 패스워드변경 기능 구현.
- 특정 정보 입력시 항목번호 구성은 자동 생성되도록 한다.
- 지역정보 입력, 출력 항목은 지역번호, 지역명 이다.
- 부서정보 입력, 출력 항목은 부서번호, 부서명 이다.
- 직위정보 입력, 출력 항목은 직위번호, 직위명, 최소기본급 이다.
- 직원정보 입력 항목은 이름, 주민번호, 입사일, 전화번호, 지역번호, 부서번호, 직위번호, 기본급, 수당 이다.
- 직원정보 출력 항목은 사번, 이름, 주민번호, 입사일, 전화번호, 지역명, 부서명, 직위명, 기본급, 수당, 급여로 구성된다.
- 직원정보 전체출력시 정렬 기준(사번, 이름, 지역명, 부서명, 직위명)별 출력되도록 한다.
- 직원정보 검색시 검색 기준(사번, 이름, 지역명, 부서명, 직위명)별 출력되도록 한다.
- 오라클 데이터베이스를 이용해서 자료 저장
- PreparedStatement 인터페이스 사용





----------------------
2. 데이터베이스 준비

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
	AS newId FROM regions), '서울');
    INSERT INTO regions (regId, reg_name)
    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) 
	AS newId FROM regions), '인천');
    INSERT INTO regions (regId, reg_name)
    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) 
	AS newId FROM regions), '부산');
COMMIT;


CREATE TABLE departments (
	deptId VARCHAR2(67)   --PK. DEPT01, DEPT02, ...
	,dept_name  NVARCHAR2(20) NOT NULL
);

ALTER TABLE departments
 ADD CONSTRAINT departments_deptId_pk PRIMARY KEY(deptId);


INSERT INTO departments (deptId, dept_name)
    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) 
	AS newId FROM departments), '개발부');
INSERT INTO departments (deptId, dept_name)
    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) 
	AS newId FROM departments), '영업부');
INSERT INTO departments (deptId, dept_name)
    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) 
	AS newId FROM departments), '회계부');
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
        AS newId FROM jobs), '사원', 2000000);
INSERT INTO jobs (jobId, job_title, min_basicPay)
    VALUES ((SELECT CONCAT('JOB', LPAD(NVL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0)) 
        AS newId FROM jobs), '과장', 3000000);
INSERT INTO jobs (jobId, job_title, min_basicPay)
    VALUES ((SELECT CONCAT('JOB', LPAD(NVL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0)) 
        AS newId FROM jobs), '차장', 4000000);
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
        AS newId FROM employees), '홍길동', '901010-1234567', '2010-10-10'
        , '010-1234-1234', 'REG01', 'DEPT01', 'JOB02', 3000000, 1000000);
INSERT INTO employees  (empId, name_, ssn, hiredate, phone
    , regId, deptId, jobId, basicpay, extrapay)
    VALUES ((SELECT CONCAT('EMP', LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) 
        AS newId FROM employees), '박유천', '991110-1234569', '2013-10-10'
        , '010-1234-1234', 'REG01', 'DEPT03', 'JOB01', 2000000, 1000000);
INSERT INTO employees  (empId, name_, ssn, hiredate, phone
    , regId, deptId, jobId, basicpay, extrapay)
    VALUES ((SELECT CONCAT('EMP', LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) 
        AS newId FROM employees), '아이린', '701010-1234567', '2000-12-10'
        , '010-1234-1234', 'REG02', 'DEPT01', 'JOB03', 4000000, 1000000);
COMMIT;

select * from employees
----------------------
3. 화면 구성
>>직원관리 v2.0
1.로그인
선택(0/1)>1
아이디>admin
패스워드>1111
관리자 'admin' 로그인되었습니다.


>>직원관리 v2.0
1.직원관리  2.기초정보관리  3.관리자정보관리
선택>2


>>직원 관리 v2.0 > 기초 정보 관리
1.지역관리  2.부서관리  3.직위관리
선택>1


>>직원 관리 v2.0 > 기초 정보 관리 > 지역 관리
1.지역입력  2.지역출력  3.지역삭제
선택>1


>>직원 관리 v2.0 > 기초 정보 관리 > 지역 관리 > 지역 입력
--------------
지역번호 / 지역명
REG01 / 강원
REG02 / 경기
REG03 / 경남
REG04 / 경북
--------------
신규 지역 이름>서울
신규 지역 정보 입력 완료!


>>직원 관리 v2.0 > 기초 정보 관리 > 지역 관리
1.지역입력  2.지역출력  3.지역삭제
선택>2
--------------
지역번호 / 지역명
REG01 / 강원
REG02 / 경기
REG03 / 경남
REG04 / 경북
REG05 / 서울
--------------


>>직원 관리 v2.0 > 기초 정보 관리 > 지역 관리
1.지역입력  2.지역출력  3.지역삭제
선택>3
--------------
지역번호 / 지역명 / 삭제가능여부
REG01 / 강원 / X
REG02 / 경기 / X
REG03 / 경남 / X
REG04 / 경북 / O
REG05 / 서울 / X
--------------
지역번호>REG04
REG04 지역이 삭제되었습니다.


>>직원 관리 v2.0 > 기초 정보 관리 > 지역 관리
1.지역입력  2.지역출력  3.지역삭제
선택>0


>>직원 관리 v2.0 > 기초 정보 관리
1.지역관리  2.부서관리  3.직위관리
선택>0


>>직원 관리 v2.0
1.직원관리  2.기초정보관리  3.관리자정보관리
선택>1


>>직원 관리 v2.0 > 직원 관리
1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제
선택>1


>>직원 관리 v2.0 > 직원 관리 > 직원 입력
이름>박길동
주민번호(YYMMDD-NNNNNNN)>800110-1234567
입사일(YYYY-MM-DD)>2010-11-31
날짜 형식이 틀렸습니다.
입사일(YYYY-MM-DD)>2010-11-30
전화번호(010-XXXX-XXXX)>010-3456-6789
--------------
지역번호 / 지역명
REG01 / 강원
REG02 / 경기
REG03 / 경남
REG04 / 경북
REG05 / 서울
--------------
지역번호>REG06
지역번호의 범위가 아닙니다.
지역번호>REG02
--------------
부서번호 / 부서명
DEPT01 / 개발부
DEPT02 / 기획부
DEPT03 / 영업부
--------------
부서번호>DEPT03
--------------
직위번호 / 직위명 / 최소기본급
JOB01 / 과장 / 2000000
JOB02 / 대리 / 1500000
JOB03 / 사원 / 1000000
--------------
직위번호>JOB01
기본급>2000000
수당>100000
직원 정보 입력 완료!


>>직원 관리 v2.0 > 직원 관리
1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제
선택>2


>>직원 관리 v2.0 > 직원 관리 > 직원 전체 출력
1.사번정렬  2.이름정렬  3.지역명정렬  4.부서명정렬  5.직위명정렬
선택>1


>>직원 관리 v2.0 > 직원 관리 > 직원 전체 출력 > 사번 정렬
전체 인원: 62명
--------------
사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여
EMP001 / 홍길동 / 771212-1022432 / 1998-10-11 / 011-2356-4528 / 서울 / 기획부 / 부장 / 2,610,000 / 200,000 / 2,810,000
EMP002 / 이순신 / 801007-1544236 / 2000-11-29 / 010-4758-6532 / 경기 / 총무부 / 사원 / 1,320,000 / 200,000 / 1,520,000
...


>>직원 관리 v2.0 > 직원 관리 > 직원 전체 출력
1.사번정렬  2.이름정렬  3.지역명정렬  4.부서명정렬  5.직위명정렬
선택>0


>>직원 관리 v2.0 > 직원 관리
1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제
선택>0


>>직원 관리 v2.0
1.직원관리  2.기초정보관리  3.관리자정보관리
선택>3


>>직원 관리 v2.0 > 관리자정보관리
1.패스워드변경
선택>1
현재패스워드>1111
신규패스워드>1234
패스워드를 변경할까요(0/1)>1
관리자 'admin'의 패스워드가 변경되었습니다.

>>직원 관리 v2.0 > 관리자정보관리
1.패스워드변경
선택>0


>>직원 관리 v2.0
1.직원관리  2.기초정보관리  3.관리자정보관리
선택>0
관리자 'admin' 로그아웃되었습니다.


>>직원관리 v2.0
1.로그인
선택(0/1)>0

프로그램이 종료되었습니다.