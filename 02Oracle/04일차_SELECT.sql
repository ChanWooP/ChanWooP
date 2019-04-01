/*
SELECT : 특정 테이블(자료에 물리적 저장소)에 대한 조회 명령
주의)적절한 권한(CONNECT, RESOURCE role + SELECT ANY TABLE)이 부여된 경우만 조회 가능

형식
SELECT column1, column2....columnN
    FROM   table_name
    WHERE  condition
    GROUP BY column_name
    HAVING (arithematic function condition)
    ORDER BY column_name {ASC|DESC};

*/



/*
FUNCTION  : Functions are similar to operators in that they manipulate data items and return a result.

1. function(argument, argument, ...) 
2. 내장 함수(SQL, 오라클 제공 함수), 사용자 정의 함수(PL/SQL)
3. 단일 행 함수(Single-Row Functions), 복수 행 함수(Aggregate Functions)
4. 숫자 자료형 함수, 문자 자료형 함수, 날짜 자료형 함수, 형 변환 함수

*/


--------------------------------
--Single-Row Functions : Single-row functions return a single result row for every row of a queried table or view.



----------------------------------------
--Datetime Functions

--SYSDATE : SYSDATE returns the current date and time set for the operating system on which the database server resides. 

SELECT SYSDATE "NOW"
     FROM DUAL;
--18/08/16

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') "NOW"
     FROM DUAL;
--2018-08-16 09:10:20


--날짜 사이의 간격 (일단위)
SELECT TO_DATE('2019-03-21') - TO_DATE('2019-03-20') "날짜 사이의 간격"
    FROM DUAL;


--날짜 사이의 간격 (일단위)
SELECT TRUNC(SYSDATE - TO_DATE('2019-02-21')) "날짜 사이의 간격"
    FROM DUAL;


--날짜 사이의 간격 (일단위)
SELECT TRUNC(SYSDATE) - TO_DATE('2019-02-21') "날짜 사이의 간격"
    FROM DUAL;
    

--평년(2013) 확인
SELECT TO_DATE('2013-03-01') - TO_DATE('2013-02-27') "날짜 사이의 간격"
    FROM DUAL;
--2

--윤년(2012) 확인
SELECT TO_DATE('2012-03-01') - TO_DATE('2012-02-27') "날짜 사이의 간격"
    FROM DUAL;
--3




----------------------------------------
--Conversion Functions

--TO_CHAR : TO_CHAR (datetime) converts a datetime or interval value of DATE, TIMESTAMP, TIMESTAMP WITH TIME ZONE, TIMESTAMP WITH LOCAL TIME ZONE, INTERVAL DAY TO SECOND, or INTERVAL YEAR TO MONTH data type to a value of VARCHAR2 data type in the format specified by the date format fmt. 
--TO_CHAR : TO_CHAR (number) converts n to a value of VARCHAR2 data type, using the optional number format fmt.
--TO_NUMBER : TO_NUMBER converts expr to a value of NUMBER data type. 
--TO_DATE : TO_DATE converts char of CHAR, VARCHAR2, NCHAR, or NVARCHAR2 data type to a value of DATE data type.

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') "NOW"
     FROM DUAL;
--2018-08-16

SELECT TO_CHAR(SYSDATE, 'YYYY-MM') "NOW"
     FROM DUAL;
--2018-08

SELECT TO_CHAR(SYSDATE, 'YYYY') "NOW"
     FROM DUAL;
--2018

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') "NOW"
     FROM DUAL;
--2018-08-16 09:23


SELECT TO_CHAR(TO_DATE('20180816'), 'YYYY-MM-DD') "FORMAT"
     FROM DUAL;
--2018-08-16

SELECT TO_CHAR(TO_DATE('900816'), 'YYYY-MM-DD') "FORMAT"
     FROM DUAL;
--1990-08-16

SELECT '10' + 20 FROM DUAL;
--30

SELECT TO_NUMBER('10') + 20 FROM DUAL;
--30


SELECT employee_id, first_name, last_name
    , TO_CHAR(hire_date, 'YYYY') "HIRE_DATE_YEAR"
     FROM hr.employees
     ORDER BY "HIRE_DATE_YEAR";


--문제23) hr 계정(소유자)의 employees 테이블의 정보에서 
--입사년도(hire_date)가 2005년도인 경우만 출력하는 쿼리 작성.
--출력시 입사년월일 순으로 출력되도록 한다.
--TO_CHAR() 함수 사용

SELECT  employee_id, first_name, hire_date
    FROM HR.employees
    WHERE TO_CHAR(hire_date, 'YYYY') IN ('2005')
    ORDER BY hire_date;




---------------------------------------
--NULL-Related Functions
--NVL : NVL lets you replace null (returned as a blank) with a string in the results of a query. 
--NVL2 : NVL2 lets you determine the value returned by a query based on whether a specified expression is null or not null.


--산술적 연산의 결과가 NULL이 된다
SELECT NULL + 10 FROM DUAL;
SELECT NULL * 10 FROM DUAL;
SELECT TO_CHAR(NULL) FROM dual;
SELECT NULL, 'null' FROM DUAL;

SELECT employee_id, first_name, last_name
    , commission_pct
    , NVL(commission_pct, 0) commission_pct_
    , NVL(TO_CHAR(commission_pct), 'Not Applicable') commission
    , department_id
  FROM hr.employees;
  
  
  
SELECT employee_id, first_name, last_name, salary
    , commission_pct
    , salary + (salary * NVL(commission_pct, 0)) income
  FROM hr.employees; 
  
  
SELECT employee_id, first_name, last_name, salary
    , commission_pct
    , NVL2(commission_pct
        , salary + (salary * commission_pct)
        , salary) income
  FROM hr.employees;  
  

--문제24) hr 계정(소유자)의 employees 테이블의 정보에서 
--department_id가 null이 아닌 경우 department_id를 출력하고,
--department_id가 null인 경우 '부서없음'으로 출력하는 쿼리 작성. NVL() 함수 사용.

SELECT employee_id, first_name, last_name
    , department_id
    , NVL(TO_CHAR(department_id), '부서없음') department_id_
    FROM hr.employees;





-------------------------------
--Encoding and Decoding Functions

--DECODE : DECODE compares expr to each search value one by one. If expr is equal to a search, then Oracle Database returns the corresponding result. 

/*
40	Human Resources
50	Shipping
60	IT
*/

SELECT employee_id, first_name
    , department_id
    , DECODE(department_id
                    , 40, 'Human Resources'
                    , 50, 'Shipping'
                    , 60, 'IT') "department_name"
    FROM hr.employees
    ORDER BY department_id;
   
   
SELECT employee_id, first_name
    , department_id
    , DECODE(department_id
                , 40, 'Human Resources'
                , 50, 'Shipping'
                , 60, 'IT'
                , 'etc.') "department_name"
    FROM hr.employees
    ORDER BY department_id; 
    
    
SELECT employee_id, first_name
    , department_id
    , DECODE(department_id
                , 40, 'Human Resources'
                , 50, 'Shipping'
                , 60, 'IT') "department_name"
    FROM hr.employees
    WHERE department_id BETWEEN 40 AND 60
    ORDER BY department_id;
    
    

--문제25) hr 계정(소유자)의 employees 테이블의 정보에서 
--salary 컬럼의 값을 3개의 등급으로 구분해서 출력하는 쿼리 작성. 
--DECODE() 함수 사용.
--출력시 salary가 많은 직원을 먼저 출력하도록 한다. 
--'10000미만' -> '10000달러미만'
--'10000이상~20000미만' -> '10000달러대'
--'20000이상' -> '20000달러대'

SELECT employee_id, first_name, salary
        , DECODE(TRUNC(salary, -4)
                    , 10000, '10000달러대'
                    , 20000, '20000달러대'
                    , '10000달러미만') AS "SALARY 등급" 
        FROM hr.employees 
        ORDER BY salary DESC;



------------------------------
--CASE ~ END 구문
--프로그램에서 if ~ else if ~ else 역할을 하는 구문
--형식1
/*
    CASE 수식 
        WHEN 값1 THEN 반환값1
        WHEN 값2 THEN 반환값2
        WHEN 값3 THEN 반환값3
        ELSE 반환값4
    END
*/
SELECT employee_id, first_name, salary
        , CASE TRUNC(salary, -4)
            WHEN 20000 THEN '20000달러대'
            WHEN 10000 THEN '10000달러대'
            ELSE '10000달러미만'
          END "SALARY 등급" 
        FROM hr.employees 
        ORDER BY salary DESC;
        
        
--형식2
/*
    CASE  
        WHEN 조건식1 THEN 반환값1
        WHEN 조건식2 THEN 반환값2
        WHEN 조건식3 THEN 반환값3
        ELSE 반환값4
    END
*/

SELECT employee_id, first_name, salary
        , CASE
            WHEN salary >= 20000 THEN '20000달러대'
            WHEN salary >= 10000 THEN '10000달러대'
            ELSE '10000달러미만'
          END "SALARY 등급" 
        FROM hr.employees 
        ORDER BY salary DESC;




--------------------------------------
--정규표현식을 이용한 검색 함수
--REGEXP_LIKE()
--REGEXP_REPLACE()
--REGEXP_SUBSTR()
--REGEXP_COUNT()

SELECT employee_id, first_name, last_name, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(first_name, '^A');
    
SELECT employee_id, first_name, last_name, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(job_id, 'CLERK$');        


SELECT employee_id, first_name, last_name, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(first_name, '[AB]');

SELECT employee_id, first_name, last_name, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(first_name, '[A-D]');

SELECT employee_id, first_name, last_name, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(first_name, 'A..m');
    
SELECT employee_id, first_name, last_name, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(first_name, '[A-Za-z]{6}');    
    
SELECT employee_id, first_name, last_name
    , phone_number, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(phone_number, '515.123.*');      
    
SELECT employee_id, first_name, last_name
    , phone_number, job_id
    FROM hr.employees
    WHERE REGEXP_LIKE(phone_number, '515.123.*|515.124.*');
