CREATE TABLE scores(
    sid_ VARCHAR2(4)
    ,name_ NVARCHAR2(50)
    ,sub1 NUMBER(3)
    ,sub2 NUMBER(3)
    ,sub3 NUMBER(3)
);

ALTER TABLE scores
ADD CONSTRAINT scores_sid_pk PRIMARY KEY(sid_);

ALTER TABLE scores
ADD CONSTRAINT scores_sub1_ck CHECK(sub1 BETWEEN 0 AND 100);
ALTER TABLE scores
ADD CONSTRAINT scores_sub2_ck CHECK(sub2 BETWEEN 0 AND 100);
ALTER TABLE scores
ADD CONSTRAINT scores_sub3_ck CHECK(sub3 BETWEEN 0 AND 100);

INSERT INTO scores (sid_, name_, sub1, sub2, sub3)
VALUES('S001', 'ȫ�浿', 90, 97, 100);
INSERT INTO scores (sid_, name_, sub1, sub2, sub3)
VALUES('S002', 'ȫ�浿2', 50, 90, 88);
INSERT INTO scores (sid_, name_, sub1, sub2, sub3)
VALUES('S003', 'ȫ�浿3', 60, 69, 100);
INSERT INTO scores (sid_, name_, sub1, sub2, sub3)
VALUES('S004', 'ȫ�浿4', 90, 65, 87);
COMMIT;

--�����
CREATE OR REPLACE VIEW scoresView
AS
SELECT sid_, name_, sub1, sub2, sub3
        ,(sub1 + sub2 + sub3) sum_
        ,ROUND(((sub1 + sub2 + sub3)/3),2) avg_
        ,DENSE_RANK() OVER(ORDER BY (sub1 + sub2 + sub3) DESC) rank_
    FROM scores;
    

--��ü
SELECT sid_, name_, sub1, sub2, sub3
        sum_, avg_, rank_
    FROM scoresView
    ORDER BY sid_;


--��ȣ����
SELECT sid_, name_, sub1, sub2, sub3
        sum_, avg_, rank_
    FROM scoresView
    WHERE sid_ = 'S002';
    ORDER BY sid_;
    
--�̸�����
SELECT sid_, name_, sub1, sub2, sub3
        sum_, avg_, rank_
    FROM scoresView
    WHERE INSTR(LOWER(name_),LOWER('2')) > 0;
    ORDER BY sid_;