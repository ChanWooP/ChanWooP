/*
회원정보관리 v1.0

1. 요구분석
- 자료 저장은 Oracle 데이터베이스 이용
- 회원 정보에 대한 입력, 출력 가능
- 회원 정보는 회원번호(PK), 이름, 전화번호, 이메일로 구성한다.
- 회원번호는 'M001','M002',... 형태로 만든다.


2. 화면설계
--- 회원 정보 관리 ---
1.회원정보출력  2.회원정보입력
선택>2
회원번호>M001
이름>hong
전화번호>010-1234-1234
이메일>hong@naver.com
회원정보를 입력할까요(0/1)?1
회원정보가 입력되었습니다.

--- 회원 정보 관리 ---
1.회원정보출력  2.회원정보입력
선택>1
1.회원정보출력
----------------------------------------
회원번호 / 이름 / 전화번호 / 이메일
M001 / hong / 010-1234-1234 / hong@naver.com
총 1명

--- 회원 정보 관리 ---
1.회원정보출력  2.회원정보입력
선택>0
프로그램 종료 

*/
--3. 데이터베이스 구현
--테이블 생성
CREATE TABLE members(
    mid VARCHAR2(4) -- PK, M001, M002~
    ,name_ NVARCHAR2(50)
    ,phone VARCHAR2(20)
    ,email VARCHAR2(50)
);

--제약조건
ALTER TABLE members
ADD CONSTRAINT mid_pk PRIMARY KEY(mid);

--샘플 자료 입력 쿼리
INSERT INTO members VALUES('M001','홍길동','010-1111-1111','test@email.com');
INSERT INTO members VALUES('M002','박길동','010-1111-1111','test2@email.com');
INSERT INTO members VALUES('M003','Createsint','010-1111-1111','test3@email.com');
INSERT INTO members VALUES('M004','Daniel Aevan','010-1111-1111','test4@email.com');
COMMIT;
--출력 쿼리
    --전체 출력
    SELECT mid, name_, phone, email
        FROM members
        ORDER BY mid;
    
    -- 검색 출력 
    SELECT mid, name_, phone, email
        FROM members
        WHERE INSTR(LOWER(name_),LOWER('홍길동')) > 0
        ORDER BY mid;