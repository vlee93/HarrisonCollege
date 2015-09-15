--------------------------------------------------------
--  File created - Monday-September-14-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence H_CLASS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_CLASS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_COURSE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_COURSE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 26 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_DEPARTMENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_DEPARTMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_ENROLLMENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_ENROLLMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_MAJOR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_MAJOR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_STAFF_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_STAFF_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 5 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_STUDENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_STUDENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence H_CLASSROOM_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "TESTDB"."H_CLASSROOM_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table H_CLASS
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_CLASS" 
   (	"CRN_NUMBER" NUMBER, 
	"COURSE_ID" NUMBER, 
	"STAFF_ID" NUMBER, 
	"ROOM_ID" NUMBER, 
	"SEMESTER" VARCHAR2(255 BYTE), 
	"CLASS_DAYS" VARCHAR2(20 BYTE), 
	"START_TIME" VARCHAR2(20 BYTE), 
	"END_TIME" VARCHAR2(20 BYTE), 
	"ENROLL_CAP" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_CLASSROOM
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_CLASSROOM" 
   (	"ROOM_ID" NUMBER, 
	"BLDG_NAME" VARCHAR2(255 BYTE), 
	"MAX_CAPACITY" NUMBER, 
	"ROOM_NO" VARCHAR2(20 BYTE), 
	"AVAILABLE" NUMBER(1,0) DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_COURSE
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_COURSE" 
   (	"COURSE_NO" NUMBER, 
	"SUBJECT_CODE" VARCHAR2(255 BYTE), 
	"COURSE_NAME" VARCHAR2(255 BYTE), 
	"COURSE_DESC" VARCHAR2(255 BYTE), 
	"CREDITS" NUMBER, 
	"DEPT_ID" NUMBER, 
	"COURSE_ID" NUMBER, 
	"AVAILABLE" NUMBER(1,0) DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_DEPARTMENT
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_DEPARTMENT" 
   (	"DEPT_ID" NUMBER, 
	"DEPT_NAME" VARCHAR2(255 BYTE), 
	"AVAILABLE" NUMBER(1,0) DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_ENROLLMENT
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_ENROLLMENT" 
   (	"ENR_ID" NUMBER, 
	"STUDENT_ID" NUMBER, 
	"CLASS_ID" NUMBER, 
	"GRADE" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_MAJOR
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_MAJOR" 
   (	"MAJOR_ID" NUMBER, 
	"MAJOR_NAME" VARCHAR2(255 BYTE), 
	"DEPT_ID" NUMBER, 
	"AVAILABLE" NUMBER(1,0) DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_STAFF
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_STAFF" 
   (	"STAFF_ID" NUMBER, 
	"STAFF_NAME" VARCHAR2(255 BYTE), 
	"DEPT_ID" NUMBER, 
	"OFFICE_NO" VARCHAR2(20 BYTE), 
	"TYPE" VARCHAR2(255 BYTE), 
	"USER_PASSWORD" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table H_STUDENT
--------------------------------------------------------

  CREATE TABLE "TESTDB"."H_STUDENT" 
   (	"STUDENT_ID" NUMBER, 
	"STUDENT_NAME" VARCHAR2(255 BYTE), 
	"MAJOR_ID" NUMBER, 
	"START_YEAR" NUMBER, 
	"USER_PASSWORD" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into TESTDB.H_CLASS
SET DEFINE OFF;
Insert into TESTDB.H_CLASS (CRN_NUMBER,COURSE_ID,STAFF_ID,ROOM_ID,SEMESTER,CLASS_DAYS,START_TIME,END_TIME,ENROLL_CAP) values (1,1,1,21,'Fall 2015','MWF','8','11',30);
Insert into TESTDB.H_CLASS (CRN_NUMBER,COURSE_ID,STAFF_ID,ROOM_ID,SEMESTER,CLASS_DAYS,START_TIME,END_TIME,ENROLL_CAP) values (2,2,2,22,'Fall 2015','TR','10','12',30);
Insert into TESTDB.H_CLASS (CRN_NUMBER,COURSE_ID,STAFF_ID,ROOM_ID,SEMESTER,CLASS_DAYS,START_TIME,END_TIME,ENROLL_CAP) values (3,3,1,21,'Summer 2015','TR','17','20',30);
REM INSERTING into TESTDB.H_CLASSROOM
SET DEFINE OFF;
Insert into TESTDB.H_CLASSROOM (ROOM_ID,BLDG_NAME,MAX_CAPACITY,ROOM_NO,AVAILABLE) values (21,'Humanities',300,'H321',1);
Insert into TESTDB.H_CLASSROOM (ROOM_ID,BLDG_NAME,MAX_CAPACITY,ROOM_NO,AVAILABLE) values (22,'Humanities',50,'H319',1);
Insert into TESTDB.H_CLASSROOM (ROOM_ID,BLDG_NAME,MAX_CAPACITY,ROOM_NO,AVAILABLE) values (23,'Schlegel',415,'S419',1);
REM INSERTING into TESTDB.H_COURSE
SET DEFINE OFF;
Insert into TESTDB.H_COURSE (COURSE_NO,SUBJECT_CODE,COURSE_NAME,COURSE_DESC,CREDITS,DEPT_ID,COURSE_ID,AVAILABLE) values (101,'ACC','Intro to Corporate Accounting','Teaches about financial statement, P&L basics',3,2,1,1);
Insert into TESTDB.H_COURSE (COURSE_NO,SUBJECT_CODE,COURSE_NAME,COURSE_DESC,CREDITS,DEPT_ID,COURSE_ID,AVAILABLE) values (201,'OMG','Operations Management','Basic supply and demand for factories',4,2,2,1);
Insert into TESTDB.H_COURSE (COURSE_NO,SUBJECT_CODE,COURSE_NAME,COURSE_DESC,CREDITS,DEPT_ID,COURSE_ID,AVAILABLE) values (101,'ENG','Into to Phonetics','This is how we pronounce shit',2,1,3,1);
Insert into TESTDB.H_COURSE (COURSE_NO,SUBJECT_CODE,COURSE_NAME,COURSE_DESC,CREDITS,DEPT_ID,COURSE_ID,AVAILABLE) values (201,'LIT','Shakespeare','He was kinda famous',3,1,4,1);
Insert into TESTDB.H_COURSE (COURSE_NO,SUBJECT_CODE,COURSE_NAME,COURSE_DESC,CREDITS,DEPT_ID,COURSE_ID,AVAILABLE) values (202,'CRR','Creative Writing','Intro to Creative Writing',1,1,5,1);
Insert into TESTDB.H_COURSE (COURSE_NO,SUBJECT_CODE,COURSE_NAME,COURSE_DESC,CREDITS,DEPT_ID,COURSE_ID,AVAILABLE) values (102,'ECO','Intro to Econometrics','Shit is hard',4,2,6,1);
REM INSERTING into TESTDB.H_DEPARTMENT
SET DEFINE OFF;
Insert into TESTDB.H_DEPARTMENT (DEPT_ID,DEPT_NAME,AVAILABLE) values (1,'Humanities',1);
Insert into TESTDB.H_DEPARTMENT (DEPT_ID,DEPT_NAME,AVAILABLE) values (2,'Business',1);
REM INSERTING into TESTDB.H_ENROLLMENT
SET DEFINE OFF;
Insert into TESTDB.H_ENROLLMENT (ENR_ID,STUDENT_ID,CLASS_ID,GRADE) values (4,1,1,98);
Insert into TESTDB.H_ENROLLMENT (ENR_ID,STUDENT_ID,CLASS_ID,GRADE) values (5,2,3,91);
Insert into TESTDB.H_ENROLLMENT (ENR_ID,STUDENT_ID,CLASS_ID,GRADE) values (6,1,2,70);
Insert into TESTDB.H_ENROLLMENT (ENR_ID,STUDENT_ID,CLASS_ID,GRADE) values (7,2,2,99);
REM INSERTING into TESTDB.H_MAJOR
SET DEFINE OFF;
Insert into TESTDB.H_MAJOR (MAJOR_ID,MAJOR_NAME,DEPT_ID,AVAILABLE) values (1,'English Literature',1,1);
Insert into TESTDB.H_MAJOR (MAJOR_ID,MAJOR_NAME,DEPT_ID,AVAILABLE) values (2,'Business Administration',2,1);
REM INSERTING into TESTDB.H_STAFF
SET DEFINE OFF;
Insert into TESTDB.H_STAFF (STAFF_ID,STAFF_NAME,DEPT_ID,OFFICE_NO,TYPE,USER_PASSWORD) values (1,'Alton Henley',2,'B100','Instructor','password');
Insert into TESTDB.H_STAFF (STAFF_ID,STAFF_NAME,DEPT_ID,OFFICE_NO,TYPE,USER_PASSWORD) values (2,'Dave Wolf',2,'B101','Advisor','password');
Insert into TESTDB.H_STAFF (STAFF_ID,STAFF_NAME,DEPT_ID,OFFICE_NO,TYPE,USER_PASSWORD) values (3,'Dharma Dunno',1,'H100','Instructor','password');
Insert into TESTDB.H_STAFF (STAFF_ID,STAFF_NAME,DEPT_ID,OFFICE_NO,TYPE,USER_PASSWORD) values (4,'Siva Toolong',1,'A001','Admin','password');
REM INSERTING into TESTDB.H_STUDENT
SET DEFINE OFF;
Insert into TESTDB.H_STUDENT (STUDENT_ID,STUDENT_NAME,MAJOR_ID,START_YEAR,USER_PASSWORD) values (1,'Vicky Lee',2,2011,'password');
Insert into TESTDB.H_STUDENT (STUDENT_ID,STUDENT_NAME,MAJOR_ID,START_YEAR,USER_PASSWORD) values (2,'Ahmed Hameed',1,2006,'password');
--------------------------------------------------------
--  DDL for Index H_CLASS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_CLASS_PK" ON "TESTDB"."H_CLASS" ("CRN_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_CLASSROOM_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_CLASSROOM_PK" ON "TESTDB"."H_CLASSROOM" ("ROOM_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_COURSE_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_COURSE_PK1" ON "TESTDB"."H_COURSE" ("COURSE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_DEPARTMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_DEPARTMENT_PK" ON "TESTDB"."H_DEPARTMENT" ("DEPT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_ENROLLMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_ENROLLMENT_PK" ON "TESTDB"."H_ENROLLMENT" ("ENR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_MAJOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_MAJOR_PK" ON "TESTDB"."H_MAJOR" ("MAJOR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_STAFF_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_STAFF_PK" ON "TESTDB"."H_STAFF" ("STAFF_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index H_STUDENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TESTDB"."H_STUDENT_PK" ON "TESTDB"."H_STUDENT" ("STUDENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table H_CLASS
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_CLASS" ADD CONSTRAINT "H_CLASS_PK" PRIMARY KEY ("CRN_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_CLASS" MODIFY ("CRN_NUMBER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_CLASSROOM
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_CLASSROOM" ADD CONSTRAINT "H_CLASSROOM_PK" PRIMARY KEY ("ROOM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_CLASSROOM" MODIFY ("ROOM_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_COURSE
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_COURSE" ADD CONSTRAINT "H_COURSE_PK" PRIMARY KEY ("COURSE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_COURSE" MODIFY ("COURSE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_DEPARTMENT
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_DEPARTMENT" ADD CONSTRAINT "H_DEPARTMENT_PK" PRIMARY KEY ("DEPT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_DEPARTMENT" MODIFY ("DEPT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_ENROLLMENT
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_ENROLLMENT" ADD CONSTRAINT "H_ENROLLMENT_PK" PRIMARY KEY ("ENR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_ENROLLMENT" MODIFY ("ENR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_MAJOR
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_MAJOR" ADD CONSTRAINT "H_MAJOR_PK" PRIMARY KEY ("MAJOR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_MAJOR" MODIFY ("MAJOR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_STAFF
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_STAFF" ADD CONSTRAINT "H_STAFF_PK" PRIMARY KEY ("STAFF_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_STAFF" MODIFY ("STAFF_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table H_STUDENT
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_STUDENT" ADD CONSTRAINT "H_STUDENT_PK" PRIMARY KEY ("STUDENT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "TESTDB"."H_STUDENT" MODIFY ("STUDENT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table H_CLASS
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_CLASS" ADD CONSTRAINT "COURSE_CONST" FOREIGN KEY ("COURSE_ID")
	  REFERENCES "TESTDB"."H_COURSE" ("COURSE_ID") ENABLE;
  ALTER TABLE "TESTDB"."H_CLASS" ADD CONSTRAINT "ROOM_CONST" FOREIGN KEY ("ROOM_ID")
	  REFERENCES "TESTDB"."H_CLASSROOM" ("ROOM_ID") ENABLE;
  ALTER TABLE "TESTDB"."H_CLASS" ADD CONSTRAINT "STAFF_CONST" FOREIGN KEY ("STAFF_ID")
	  REFERENCES "TESTDB"."H_STAFF" ("STAFF_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table H_COURSE
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_COURSE" ADD CONSTRAINT "DEPT_CONST" FOREIGN KEY ("DEPT_ID")
	  REFERENCES "TESTDB"."H_DEPARTMENT" ("DEPT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table H_ENROLLMENT
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_ENROLLMENT" ADD CONSTRAINT "CLASS_CONST" FOREIGN KEY ("CLASS_ID")
	  REFERENCES "TESTDB"."H_CLASS" ("CRN_NUMBER") ENABLE;
  ALTER TABLE "TESTDB"."H_ENROLLMENT" ADD CONSTRAINT "STUDENT_CONST" FOREIGN KEY ("STUDENT_ID")
	  REFERENCES "TESTDB"."H_STUDENT" ("STUDENT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table H_MAJOR
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_MAJOR" ADD CONSTRAINT "DEPT_CONST3" FOREIGN KEY ("DEPT_ID")
	  REFERENCES "TESTDB"."H_DEPARTMENT" ("DEPT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table H_STAFF
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_STAFF" ADD CONSTRAINT "DEPT_CONST2" FOREIGN KEY ("DEPT_ID")
	  REFERENCES "TESTDB"."H_DEPARTMENT" ("DEPT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table H_STUDENT
--------------------------------------------------------

  ALTER TABLE "TESTDB"."H_STUDENT" ADD CONSTRAINT "MAJOR_CONST" FOREIGN KEY ("MAJOR_ID")
	  REFERENCES "TESTDB"."H_MAJOR" ("MAJOR_ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_STUDENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_STUDENT_TRG" 
BEFORE INSERT ON H_STUDENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_STUDENT_SEQ.NEXTVAL INTO :NEW.STUDENT_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_STUDENT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_STAFF_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_STAFF_TRG" 
BEFORE INSERT ON H_STAFF 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_STAFF_SEQ.NEXTVAL INTO :NEW.STAFF_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_STAFF_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_MAJOR_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_MAJOR_TRG" 
BEFORE INSERT ON H_MAJOR 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_MAJOR_SEQ.NEXTVAL INTO :NEW.MAJOR_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_MAJOR_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_ENROLLMENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_ENROLLMENT_TRG" 
BEFORE INSERT ON H_ENROLLMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_ENROLLMENT_SEQ.NEXTVAL INTO :NEW.ENR_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_ENROLLMENT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_DEPARTMENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_DEPARTMENT_TRG" 
BEFORE INSERT ON H_DEPARTMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_DEPARTMENT_SEQ.NEXTVAL INTO :NEW.DEPT_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_DEPARTMENT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_COURSE_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_COURSE_TRG" 
BEFORE INSERT ON H_COURSE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_COURSE_SEQ.NEXTVAL INTO :NEW.COURSE_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_COURSE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_CLASSROOM_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_CLASSROOM_TRG" 
BEFORE INSERT ON H_CLASSROOM 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_CLASSROOM_SEQ.NEXTVAL INTO :NEW.ROOM_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_CLASSROOM_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger H_CLASS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TESTDB"."H_CLASS_TRG" 
BEFORE INSERT ON H_CLASS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING THEN
      SELECT H_CLASS_SEQ.NEXTVAL INTO :NEW.CRN_NUMBER FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "TESTDB"."H_CLASS_TRG" ENABLE;