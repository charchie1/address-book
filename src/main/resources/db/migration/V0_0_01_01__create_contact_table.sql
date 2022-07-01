CREATE SEQUENCE seq_contact_id;

CREATE TABLE contact(
  contact_id NUMBER   NOT NULL PRIMARY KEY,
  name       VARCHAR2 NULL,
  telephone  VARCHAR2 NULL,
  street     VARCHAR2 NULL,
  city       VARCHAR2 NULL,
  country    VARCHAR2 NULL,
  post_code  VARCHAR2 NULL
);