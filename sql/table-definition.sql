CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA r2schema;

CREATE TABLE APP_USER (
    APP_USERID UUID default uuid_generate_v4(),
    USERNAME VARCHAR(100) not null,
    FIRST_NAME VARCHAR (50) not null,
    LAST_NAME VARCHAR (50) not null,
    ACCT_LOCKED BOOLEAN not null default false,
    ACTIVE BOOLEAN not null default true,
    CREATED_DATE DATE not null default now(),
    PRIMARY KEY(APP_USERID)
);

CREATE TABLE APP_ACCOUNT(
   ACCT_ID UUID default uuid_generate_v4(),
   EMAIL VARCHAR(100) not null,
   PASSWORD VARCHAR (250) not null,
   ACCT_LOCKED BOOLEAN not null default false,
   ACTIVE BOOLEAN not null default true,
   CREATED_DATE DATE NOT NULL DEFAULT now(),
   CREATED_BY VARCHAR(50),
   MODIFIED_DATE DATE,
   MODIFIED_BY VARCHAR(50),
   PRIMARY KEY(ACCT_ID)
);

CREATE TABLE APP_ACCOUNT_ROLE(
   ACCT_ROLE_ID UUID default uuid_generate_v4(),
   ACCT_ID UUID NOT NULL,
   ROLE VARCHAR(100) not null,
   CREATED_DATE DATE NOT NULL DEFAULT now(),
   CREATED_BY VARCHAR(50),
   MODIFIED_DATE DATE,
   MODIFIED_BY VARCHAR(50),
   PRIMARY KEY(ACCT_ROLE_ID),
   FOREIGN KEY (ACCT_ID) REFERENCES APP_ACCOUNT(ACCT_ID)
);

CREATE TABLE APP_ACCT_USER(
  ACCT_USERID UUID DEFAULT uuid_generate_v4(),
  ACCT_ID UUID NOT NULL,
  FIRST_NAME VARCHAR (50) not null,
  LAST_NAME VARCHAR (50) not null,
  CREATED_DATE DATE NOT NULL DEFAULT now(),
  CREATED_BY VARCHAR(50),
  MODIFIED_DATE DATE,
  MODIFIED_BY VARCHAR(50),
  PRIMARY KEY(ACCT_USERID),
  FOREIGN KEY (ACCT_ID) REFERENCES APP_ACCOUNT(ACCT_ID)
);

CREATE TABLE APP_USER_ADDRESS(
  ADDRESS_ID UUID DEFAULT uuid_generate_v4(),
  ACCT_ID UUID NOT NULL,
  STREET1 VARCHAR (150) not null,
  STREET2 VARCHAR (150) null,
  STATE VARCHAR (150) not null,
  ZIP VARCHAR (25) not null,
  CITY VARCHAR (150) not null,
  COUNTRY VARCHAR (150) not null,
  ADDRESS_TYPE VARCHAR (25) not null,
  CREATED_DATE DATE NOT NULL DEFAULT now(),
  CREATED_BY VARCHAR(50),
  MODIFIED_DATE DATE,
  MODIFIED_BY VARCHAR(50),
  PRIMARY KEY(ADDRESS_ID),
  FOREIGN KEY (ACCT_ID) REFERENCES APP_ACCOUNT(ACCT_ID)
);

CREATE TABLE APP_SCHEDULE(
  SCHEDULE_ID UUID DEFAULT uuid_generate_v4(),
  ACCT_ID UUID NOT NULL,
  TITLE VARCHAR (250) not null,
  DESCRIPTION TEXT null,
  LOCATION VARCHAR (250) null,
  BLOCKED BOOLEAN NOT NULL DEFAULT FALSE,
  START_DT TIMESTAMP not null,
  END_DT TIMESTAMP not null,
  CREATED_DATE DATE NOT NULL DEFAULT now(),
  CREATED_BY VARCHAR(50),
  MODIFIED_DATE DATE,
  MODIFIED_BY VARCHAR(50),
  PRIMARY KEY(SCHEDULE_ID),
  FOREIGN KEY (ACCT_ID) REFERENCES APP_ACCOUNT(ACCT_ID)
);

