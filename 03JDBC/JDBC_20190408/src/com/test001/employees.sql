CREATE TABLE Employees(
    id_ NUMBER
    ,age NUNBER
    ,first_ VARCHAR2(20)
    ,last_ VARCHAR2(20)
);
INSERT INTO Employees VALUES (100, 18, 'Zara', 'Ali');
INSERT INTO Employees VALUES (101, 25, 'Mahnaz', 'Fatma');
INSERT INTO Employees VALUES (102, 30, 'Zaid', 'Khan');
INSERT INTO Employees VALUES (103, 28, 'Sumit', 'Mittal');
Commit;

SELECT id_, age, first_, last_ FROM employees;

ALTER TABLE employees
	ADD CONSTRAINT ID_PK PRIMARY KEY(id_);
