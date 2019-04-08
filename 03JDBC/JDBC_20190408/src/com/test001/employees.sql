문제1) hr.employees 테이블에서 입사일(hire_date) 정보 중에서 1, 2, 3월 입사자만 출력하는 쿼리 작성. IN 연산자 사용.
SELECT * FROM hr.employees
WHERE TO_CHAR(hire_date,'MM') IN (01,02,03);

문제2) hr.employees 테이블에서 job_id가 Manager('SA_MAN', 'ST_MAN', 'PU_MAN', 'AC_MGR')인 경우 출력하는 쿼리 작성. IN 연산자 사용.
SELECT * FROM hr.employees
WHERE job_id in ('SA_MAN', 'ST_MAN', 'PU_MAN', 'AC_MGR');

문제3) hr.employees 테이블에서 급여(salary)가 10000 이상이면서, 커미션(commission_pct)이 없는(값으로 NULL을 가진) 경우 출력하는 쿼리 작성. IS 연산자 사용.
SELECT * FROM  hr.employees
WHERE salary >= 10000
AND commission_pct IS NULL;

문제4) hr.employees 테이블에서 급여(salary) 수준에 따라서 'A', 'B', 'C' 등급으로 출력하는 쿼리 작성. CASE~END 구문 사용.
급여가 20000 이상인 경우 'A'
급여가 10000 이상인 경우 'B'
급여가 10000 미만인 경우 'C'
SELECT salary, CASE WHEN salary < 10000 THEN 'A'
                    WHEN salary >= 10000 AND salary <= 20000 THEN 'B'
                    ELSE 'C' END "rank"
FROM hr.employees;


문제5) insa 테이블에서 전화번호(tel)가 있으면 '-'을 제거하고 출력하고, 없으면 '전화번호없음'을 출력하는 쿼리 작성. DECODE, REPLACE 함수 사용.
insa 테이블 원본 상태
SELECT tel, DECODE(tel, NULL, '전화번호없음', REPLACE(tel,'-')) "tel_"
FROM insa;

문제6) hr.employees 테이블에서 입사일(hire_date) 정보 중에서 년도별로 인원수 출력하는 쿼리 작성. GROUP BY 구문, COUNT 함수 사용.
SELECT TO_CHAR(hire_date, 'YYYY'), COUNT(*) "total"
FROM hr.employees
GROUP BY TO_CHAR(hire_date, 'YYYY');

문제7) insa 테이블에서 전체인원수, 남자인원수, 여자인원수를 동시 출력하는 쿼리 작성. COUNT, DECODE 함수 사용.
출력 예)
전체인원수   남자인원수   여자인원수
   60      31           29
SELECT COUNT(*) "total"
        ,COUNT(DECODE(SUBSTR(ssn, 8, 1), '1', 1, NULL)) "man"
        ,COUNT(DECODE(SUBSTR(ssn, 8, 1), '2', 1, NULL)) "woman"
FROM insa;


문제8) hr.employees 테이블에서 급여(salary)를 많이 받는 직원부터 순서대로 출력하되, 순번 부여해서 출력하는 쿼리 작성. ROW_NUMBER() 함수 사용.
SELECT salary, ROW_NUMBER() OVER(ORDER BY salary) "rank"
FROM hr.employees;

문제9) hr.employees 테이블에서 employee_id가 150인 사원과 같은 급여를 받는 사원 출력하는 쿼리 작성. 서브 쿼리 사용.
SELECT salary
FROM hr.employees
WHERE salary = (SELECT salary FROM hr.employees WHERE employee_id = 150)

문제10) hr.employees 테이블에서 department_id가 100인 사원 정보 중에서 job_id, salary가 일치하는 사원 출력하는 쿼리 작성. 서브 쿼리 사용.
SELECT *
FROM hr.employees
WHERE department_id = '100'
AND job_

문제11) hr.employees 테이블에서 사원 정보 출력시, 부서명(hr.departments 테이블의 department_name)을 같이 출력하는 쿼리 작성. 연관 서브쿼리 사용.


문제12) hr.employees, hr.jobs 테이블을 조인 지정하고 job_title이 'Stock Manager', 'Stock Clerk'인 직원 정보 출력하는 쿼리 작성. Oracle JOIN 표기법 사용.



문제13) hr.departments 테이블에서 직원이 없는 부서 정보 출력하는 쿼리 작성. LEFT OUTER JOIN 사용.  Oracle JOIN 표기법 사용.


문제14) hr.employees 테이블에서 말단 직원(부하 직원이 없는 직원) 정보 출력하는 쿼리 작성.  SELF JOIN + OUTER JOIN 사용.  Oracle JOIN 표기법 사용.



문제15) 제약조건 종류 및 특징 설명.



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



문제17) employees, jobs 테이블에서 job_title이 'Stock Clerk'인 직원의 급여(salary) 20% 인상, job_title이 'Stock Manager'인 직원의 급여(salary) 10% 인상하는 쿼리 작성. InlineView + JOIN + UPDATE 사용.


문제18) employees, jobs 테이블에서 job_title이 'Stock Clerk'인 직원 정보 삭제하는 쿼리 작성. InlineView + JOIN + DELETE 사용.
