CREATE TABLE employee(
  id INTEGER AUTO_INCREMENT,
  name VARCHAR(50),
  email VARCHAR(50) NOT NULL UNIQUE,
  department VARCHAR(50),
  PRIMARY KEY (id)
);


CREATE TABLE vacation_type (
  id INTEGER NOT NULL AUTO_INCREMENT,
  type VARCHAR(10) NOT NULL,
  days INTEGER NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE vacation (
  id INTEGER NOT NULL AUTO_INCREMENT,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  vacation_type_id INTEGER NOT NULL,
  employee_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee (id),
   FOREIGN KEY (vacation_type_id) REFERENCES vacation_type (id)
);


CREATE TABLE vacation_balance (
  id INTEGER NOT NULL AUTO_INCREMENT,
  vacation_type_id INTEGER NOT NULL,
  days INTEGER NOT NULL,
  employee_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee (id),
  FOREIGN KEY (vacation_type_id) REFERENCES vacation_type (id)
);


