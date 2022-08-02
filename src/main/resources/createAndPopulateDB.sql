CREATE TABLE tb_emp (id serial,name varchar(255),gender varchar(255),department varchar(255),dob date);
INSERT INTO tb_emp(id,name,gender,department,dob) VALUES (4,'Andy Ayres','Male','Sales','1993-09-11');
INSERT INTO tb_emp(id,name,gender,department,dob) VALUES (5,'John Grillo','Male','Management','1993-04-29');
INSERT INTO tb_emp(id,name,gender,department,dob) VALUES (3,'Joseph Haberberger','Male','IT','1993-06-10');
INSERT INTO tb_emp(id,name,gender,department,dob) VALUES (6,'Autumn Lange','Female','Science','2020-07-28');
SELECT * from tb_emp;