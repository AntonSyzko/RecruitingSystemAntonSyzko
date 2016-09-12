INSERT INTO editor VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3');
INSERT INTO editor VALUES (2,'user','ee11cbb19052e40b07aac0ca060c23ee');




INSERT INTO  department VALUES (1,'sales');
INSERT INTO  department VALUES (2,'support');
INSERT INTO  department VALUES (3,'payments');


   INSERT INTO editor_roles VALUES (1,2);
   INSERT INTO editor_roles VALUES (1,1);




INSERT INTO employee VALUES (1,'2012-12-12','John',true,'Peterson','2013-12-12','1000',1);
INSERT INTO employee VALUES (2,'2008-12-12','Ben',true,'Allison','2010-12-12','10000',1);
INSERT INTO employee VALUES (3,'2010-12-12','Greg',true,'Howe','2005-12-12','100',2);
INSERT INTO employee VALUES (4,'2001-12-12','Phill',true,'Grenadier','2003-12-12','100000',2);
INSERT INTO employee VALUES (5,'2000-12-12','Zack',true,'Wild','2001-12-12','8000',3);
INSERT INTO employee VALUES (6,'2005-12-12','Drew',true,'Levy','2009-12-12','7000',3);



   INSERT INTO roles VALUES (1,'ROLE_ADMIN');
      INSERT INTO roles VALUES (2,'ROLE_USER');


INSERT INTO department_employee VALUES (1,1);
INSERT INTO department_employee VALUES (2,2);
INSERT INTO department_employee VALUES (3,3);
INSERT INTO department_employee VALUES (1,4);
INSERT INTO department_employee VALUES (2,5);
INSERT INTO department_employee VALUES (3,6);