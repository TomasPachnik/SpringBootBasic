CREATE TABLE identity (
  uuid VARCHAR(36) PRIMARY KEY ,
  name VARCHAR(50),
  surname VARCHAR(50),
  login VARCHAR(50),
  enabled BOOLEAN,
  email VARCHAR(256),
  encodedPassword VARCHAR(256),
  age INTEGER
);

CREATE TABLE role (
  uuid VARCHAR(36) PRIMARY KEY ,
  name VARCHAR(50),
  description VARCHAR(256),
  level INTEGER,
  identity_uuid VARCHAR(36)
);

CREATE TABLE role_identity (
  identity_uuid VARCHAR(36),
  role_uuid VARCHAR(36),
  FOREIGN KEY (identity_uuid)
  REFERENCES Identity(uuid),
  FOREIGN KEY (role_uuid)
  REFERENCES Role(uuid)
);

CREATE TABLE token (
  uuid VARCHAR(36) PRIMARY KEY,
  token VARCHAR(48),
  validity BIGINT,
  login VARCHAR(50)
);