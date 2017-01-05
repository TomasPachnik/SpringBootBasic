CREATE TABLE Identity (
  uuid   VARCHAR(36) PRIMARY KEY ,
  name VARCHAR(50),
  surname  VARCHAR(50),
  login  VARCHAR(50),
  email  VARCHAR(256),
  encodedPassword  VARCHAR(256),
  age INTEGER
);

CREATE TABLE Role (
  uuid   VARCHAR(36) PRIMARY KEY ,
  name VARCHAR(50),
  description  VARCHAR(256),
  level INTEGER,
  identity_uuid   VARCHAR(36),
  FOREIGN KEY (identity_uuid)
  REFERENCES Identity(uuid)
);