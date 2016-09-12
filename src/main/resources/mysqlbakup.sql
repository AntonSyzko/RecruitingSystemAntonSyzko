CREATE TABLE department (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
department_name VARCHAR(255)
);

   CREATE TABLE department_employee (
  department_id BIGINT NOT NULL ,
  employees_id BIGINT NOT NULL
);

   CREATE TABLE editor (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
     name VARCHAR(255),
     password VARCHAR(255)
   );
   CREATE TABLE editor_roles (
     editor_id BIGINT NOT NULL,
     role_id BIGINT NOT NULL
   );
   CREATE TABLE employee (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
     birth_date DATE,
     first_name VARCHAR(255),
     is_active BOOLEAN,
     last_name VARCHAR(255),
     recruited_date DATE,
     salary DOUBLE PRECISION NOT NULL,
     department_id BIGINT
   );


   CREATE TABLE roles (
     id BIGINT NOT NULL PRIMARY KEY ,
     role_name VARCHAR(255)
   );



