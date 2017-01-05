CREATE TABLE Identity (
  uuid   VARCHAR(36) PRIMARY KEY ,
  name VARCHAR(50),
  surname  VARCHAR(50),
  age INTEGER
);

CREATE TABLE Role (
  uuid   VARCHAR(36) PRIMARY KEY ,
  name VARCHAR(50),
  description  VARCHAR(256),
  level INTEGER
);