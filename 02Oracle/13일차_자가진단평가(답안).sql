
----------------------------------------
insa 테이블 구조
 
NUM      NOT NULL NUMBER(5)    --직원번호
NAME     NOT NULL VARCHAR2(20)  --이름
SSN      NOT NULL VARCHAR2(14)   --주민번호
IBSADATE NOT NULL DATE           --입사일
TEL               VARCHAR2(15)   --전화번호
CITY              VARCHAR2(10)   --지역
BUSEO    NOT NULL VARCHAR2(15)   --부서
JIKWI    NOT NULL VARCHAR2(15)   --직위
BASICPAY NOT NULL NUMBER(10)     --기본급
SUDANG   NOT NULL NUMBER(10)     --수당
----------------------------------------
HR.employees 테이블 구조
 
EMPLOYEE_ID    NOT NULL NUMBER(6)    
FIRST_NAME              VARCHAR2(20) 
LAST_NAME      NOT NULL VARCHAR2(25) 
EMAIL          NOT NULL VARCHAR2(25) 
PHONE_NUMBER            VARCHAR2(20) 
HIRE_DATE      NOT NULL DATE         
JOB_ID         NOT NULL VARCHAR2(10) 
SALARY                  NUMBER(8,2)  
COMMISSION_PCT          NUMBER(2,2)  
MANAGER_ID              NUMBER(6)    
DEPARTMENT_ID           NUMBER(4) 
----------------------------------------
HR.departments 테이블 구조
 
DEPARTMENT_ID   NOT NULL NUMBER(4)    
DEPARTMENT_NAME NOT NULL VARCHAR2(30) 
MANAGER_ID               NUMBER(6)    
LOCATION_ID              NUMBER(4)   
----------------------------------------
HR.jobs 테이블 구조
 
JOB_ID     NOT NULL VARCHAR2(10) 
JOB_TITLE  NOT NULL VARCHAR2(35) 
MIN_SALARY          NUMBER(6)    
MAX_SALARY          NUMBER(6)  
----------------------------------------
 
 
문제1) hr.employees 테이블에서 입사일(hire_date) 정보 중에서 1, 2, 3월 입사자만 출력하는 쿼리 작성. IN 연산자 사용.
 
SELECT first_name, last_name, hire_date
FROM hr.employees
WHERE TO_CHAR(hire_date, 'MM') IN ('01', '02', '03');
 
 
 
문제2) hr.employees 테이블에서 job_id가 Manager('SA_MAN', 'ST_MAN', 'PU_MAN', 'AC_MGR')인 경우 출력하는 쿼리 작성. IN 연산자 사용.
 
SELECT first_name, last_name, job_id
FROM hr.employees
WHERE job_id IN ('SA_MAN', 'ST_MAN', 'PU_MAN', 'AC_MGR');
 
 
 
문제3) hr.employees 테이블에서 급여(salary)가 10000 이상이면서, 커미션(commission_pct)이 없는(값으로 NULL을 가진) 경우 출력하는 쿼리 작성. IS 연산자 사용.
 
SELECT first_name, last_name, salary, commission_pct
FROM hr.employees
WHERE salary >= 10000
AND commission_pct IS NULL;
 
 
 
문제4) hr.employees 테이블에서 급여(salary) 수준에 따라서 'A', 'B', 'C' 등급으로 출력하는 쿼리 작성. CASE~END 구문 사용.
급여가 20000 이상인 경우 'A'
급여가 10000 이상인 경우 'B'
급여가 10000 미만인 경우 'C'
 
SELECT first_name, last_name, salary, CASE WHEN salary < 10000 THEN 'C'
                WHEN salary < 20000 THEN 'B'
                ELSE 'C' END grade
FROM hr.employees;
 
 
 
문제5) insa 테이블에서 전화번호(tel)가 있으면 '-'을 제거하고 출력하고, 없으면 '전화번호없음'을 출력하는 쿼리 작성. DECODE, REPLACE 함수 사용.
 
SELECT name, DECODE (tel, NULL, '전화번호없음', REPLACE(tel, '-', '')) tel
FROM insa;
 
 
 
문제6) hr.employees 테이블에서 입사일(hire_date) 정보 중에서 년도별로 인원수 출력하는 쿼리 작성. GROUP BY 구문, COUNT 함수 사용.
 
SELECT TO_CHAR(hire_date, 'YYYY'), COUNT(*)
FROM hr.employees
GROUP BY TO_CHAR(hire_date, 'YYYY');
 
 
 
문제7) insa 테이블에서 전체인원수, 남자인원수, 여자인원수를 동시 출력하는 쿼리 작성. COUNT, DECODE 함수 사용.
출력 예)
전체인원수   남자인원수   여자인원수
   60         31              29
 
SELECT (SELECT COUNT(*)
        FROM insa) all_,
    (SELECT COUNT(*)
    FROM insa
    WHERE SUBSTR(ssn, 8, 1) = '1') men,
    (SELECT COUNT(*)
    FROM insa
    WHERE SUBSTR(ssn, 8, 1) = '2') women
FROM DUAL;
 
 
 
문제8) hr.employees 테이블에서 급여(salary)를 많이 받는 직원부터 순서대로 출력하되, 순번 부여해서 출력하는 쿼리 작성. ROW_NUMBER() 함수 사용.
 
SELECT ROW_NUMBER() OVER(ORDER BY salary) num_, first_name, last_name, salary
FROM hr.employees;
 
 
 
문제9) hr.employees 테이블에서 employee_id가 150인 사원과 같은 급여를 받는 사원 출력하는 쿼리 작성. 서브 쿼리 사용.
 
SELECT employee_id, first_name, last_name, salary
FROM hr.employees
WHERE salary = (SELECT salary
        FROM hr.employees
        WHERE employee_id = '150');
 
 
 
문제10) hr.employees 테이블에서 department_id가 100인 사원 정보 중에서 job_id, salary가 일치하는 사원 출력하는 쿼리 작성. 서브 쿼리 사용.
 
SELECT first_name, last_name, job_id, salary
FROM hr.employees, (SELECT job_id jid, salary sal
            FROM hr.employees 
            WHERE department_id = '100')
WHERE department_id = '100'
AND job_id = jid
AND salary = sal;
 
 
 
 
 
문제11) hr.employees 테이블에서 사원 정보 출력시, 부서명(hr.departments 테이블의 department_name)을 같이 출력하는 쿼리 작성. 연관 서브쿼리 사용.
 
SELECT e.first_name, e.last_name, d.department_name
FROM hr.employees e, hr.departments d
WHERE e.department_id = d.department_id;
 
 
 
문제12) hr.employees, hr.jobs 테이블을 조인 지정하고 job_title이 'Stock Manager', 'Stock Clerk'인 직원 정보 출력하는 쿼리 작성. Oracle JOIN 표기법 사용.
 
SELECT e.first_name, e.last_name, j.job_title
FROM hr.employees e, hr.jobs j
WHERE e.job_id = j.job_id
AND j.job_title IN ('Stock Manager', 'Stock Clerk');
 
 
 
문제13) hr.departments 테이블에서 직원이 없는 부서 정보 출력하는 쿼리 작성. LEFT OUTER JOIN 사용.  Oracle JOIN 표기법 사용.
 
SELECT department_name
FROM hr.departments d, hr.employees e
WHERE d.department_id = e.department_id(+)
AND e.employee_id IS NULL;
 
 
 
문제14) hr.employees 테이블에서 말단 직원(부하 직원이 없는 직원) 정보 출력하는 쿼리 작성.  SELF JOIN + OUTER JOIN 사용.  Oracle JOIN 표기법 사용.
 
SELECT e1.employee_id, e1.first_name, e1.last_name
FROM hr.employees e1, hr.employees e2
WHERE e1.employee_id = e2.manager_id(+)
AND e2.employee_id IS NULL;
 
 
 
문제15) 제약조건 종류 및 특징 설명.
 
PRIMARY KEY - 중복 불가, NULL값 지정 불가
FOREIGN KEY - 다른 테이블의 특정 컬럼을 참조, 해당 테이블에 없는 값은 입력 불가
CHECK - 조건에 지정된 값들만 입력할 수 있음
UNIQUE - 중복 불가, NULL값 지정 가능
NOT NULL - NULL값 지정 불가
 
 
문제16) 아래 항목(컬럼)으로 구성된 insa 테이블 생성 및 NUM 컬럼에 Primary Key 제약 조건 지정, 샘플 자료(2명 분의 자료) 입력 쿼리 작성.
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
 
CREATE TABLE insa_ (
    NUM NUMBER(5) NOT NULL,      --PK
    NAME NVARCHAR2(20) NOT NULL,
    SSN VARCHAR2(14) NOT NULL, 
    IBSADATE DATE NOT NULL,
    TEL VARCHAR2(15),
    CITY NVARCHAR2(10),
    BUSEO NVARCHAR2(15) NOT NULL,
    JIKWI NVARCHAR2(15) NOT NULL,
    BASICPAY NUMBER(10) NOT NULL,
    SUDANG NUMBER(10) NOT NULL
);
 
ALTER TABLE insa_
ADD CONSTRAINT insa__pk PRIMARY KEY (num);
 
 
 
문제17) employees, jobs 테이블에서 job_title이 'Stock Clerk'인 직원의 급여(salary) 20% 인상, job_title이 'Stock Manager'인 직원의 급여(salary) 10% 인상하는 쿼리 작성. InlineView + JOIN + UPDATE 사용.
 
UPDATE employees e
SET e.basicpay = (SELECT DECODE (j.job_title, 'Stock Clerk', e.basicpay * 1.2, 'Stock Manager', e.basicpay * 1.1, e.basicpay)
                FROM jobs j
                WHERE e.jobId = j.jobId);
 
 
 
문제18) employees, jobs 테이블에서 job_title이 'Stock Clerk'인 직원 정보 삭제하는 쿼리 작성. InlineView + JOIN + DELETE 사용.
 
DELETE FROM employees
WHERE jobId = (SELECT jobId
        FROM jobs
        WHERE job_title = 'Stock Clerk');
 
 
--------------------------------------

 