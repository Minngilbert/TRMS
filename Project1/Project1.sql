--Creation Statements
create database trmsdb;

create table reimbursement(
applicationid serial primary key,
submittedon date not null,
employeeid int not null,
educationtypeid int not null
);

create table formdetails(
formid serial primary key,
applicationid int not null,
formfirstname varchar(20) not null,
formlastname varchar(20) not null,
formeventname varchar(30) not null,
formeventcost numeric not null,
formeventstartdate date not null,
formeventaddress varchar(40) not null,
formeventcity varchar(20) not null,
formeventstate varchar(40) not null,
formeventzip int not null,
formgradedformat varchar(12) not null,
formdescription varchar(1000) not null
);

create table approval(
applicationid int not null,
dsapprovalflag boolean not null,
dhapprovalflag boolean not null,
bencoapprovalflag boolean not null
);

create table employee(
employeeid serial primary key,
firstname varchar(20) not null,
lastname varchar(20) not null,
reportsto int not null,
isds boolean not null,
isdh boolean not null,
isbenco boolean not null
);

create table educationtype(
educationtypeid serial primary key,
educationtype varchar(20) not null,
coverage numeric not null
);

create table testtable(
columnid int primary key
);

--FOREIGN KEYS
--Adds ReportsTo FK internal to Employee table
ALTER TABLE "employee"
ADD CONSTRAINT "FK_EmployeeReportsTo" 
FOREIGN KEY ("reportsto") 
REFERENCES "employee"("employeeid")
on delete cascade;

--FK between reimbursement-formdetails tables
ALTER TABLE "formdetails"
add constraint "FK_ReimbursementFormDetails"
foreign key ("applicationid")
references "reimbursement"("applicationid")
on delete cascade;

--FK between approval-reimbursement tables
alter table "approval"
add constraint "FK_ApprovalReimbursement"
foreign key ("applicationid")
references "reimbursement"("applicationid")
on delete cascade;

--FK between reimbursement-employee tables
alter table "reimbursement"
add constraint "FK_ReimbursementEmployee"
foreign key ("employeeid")
references "employee"("employeeid")
on delete cascade;

--FK between reimbursement-educationtype tables
alter table "reimbursement"
add constraint "FK_ReimbursementEducationType"
foreign key ("educationtype")
references "educationtype"("educationtype")
on delete cascade;

alter table reimbursement 
delete "FK_ReimbursementEducationType" cascade;

alter table reimbursement 
add column educationtype varchar(20);

--Default Values & Table Alterations
alter table "approval"
alter column "dsapprovalflag"
set default null;

alter table "approval" 
alter column "dhapprovalflag"
set default null;

alter table "approval" 
alter column "bencoapprovalflag"
set default null;

alter table "reimbursement"
add column "urgent" boolean;

alter table "reimbursement" 
alter column "urgent"
set default false;

alter table reimbursement 
alter column "urgent"
set not null;

alter table educationtype 
alter column "educationtype"
type varchar(40);

alter table employee 
add column "email" varchar(30);

alter table employee 
add column "password" varchar(30);

alter table employee 
drop column "isds";

alter table employee 
alter column reportsto
drop not null;

alter table reimbursement 
alter column submittedon
set default now();

alter table employee 
add constraint
UniqueEmail unique (email);

alter table employee 
add column "reimbursementamount" numeric;

alter table employee 
alter column reimbursementamount
set default 1000.00;

alter table approval 
add column additionalcomments varchar(1000);

alter table approval 
alter column additionalcomments
set default null;

alter table reimbursement 
add column employeerequestsinfo boolean;

alter table reimbursement 
alter column employeerequestsinfo
set default null;

alter table reimbursement 
add column reimbursementamountchanged boolean;

alter table reimbursement 
alter column reimbursementamountchanged
set default false;

alter table public.testtable 
alter column columnid
type varchar(20);

drop table approval;

alter table formdetails 
alter column bencoapprovalflag
type varchar(20);

alter table formdetails 
alter column bencoapprovalflag
set default 'Pending';

alter table formdetails 
add column dhapprovalflag boolean;

alter table formdetails 
alter column dhapprovalflag
set default null;

alter table formdetails 
add column bencoapprovalflag boolean;

alter table formdetails 
alter column bencoapprovalflag
set default null;

alter table formdetails 
add column formeventstarttime varchar(40);

alter table formdetails 
add column formeventendtime varchar(40);

alter table formdetails 
add column gradecutoff varchar(40);

alter table formdetails 
alter column formeventstartdate
type varchar(40);

update formdetails set dsapprovalflag = 'Approved' where applicationid = ?;

alter table formdetails 
add column denialreason varchar(1000);

alter table reimbursement 
add column moreinformation varchar(1000);

alter table reimbursement 
alter column employeerequestsinfo
set default false;

alter table reimbursement 
rename column employeerequestsinfo to dsrequestsinfo;

alter table reimbursement 
add column dhrequestsinfo boolean;

alter table reimbursement 
alter column dhrequestsinfo
set default false;

alter table reimbursement 
add column bencorequestsinfo boolean;

alter table reimbursement
alter column bencorequestsinfo
set default false;

alter table employee 
add column isDS boolean;

alter table employee 
alter column isDS
set default false;

select *
from employee m
inner join employee e on e.employeeid = m.reportsto;

select bencoapprovalflag from formdetails where bencoapprovalflag = 'Approved';

--Seed Values
insert into educationtype 
(educationtype, coverage) values ('Certification', 1);

insert into educationtype 
(educationtype, coverage) values ('Technical Training', 0.9);

insert into educationtype 
(educationtype, coverage) values ('University Courses', 0.8);

insert into educationtype 
(educationtype, coverage) values ('Certification Preparation Classes', 0.7);

insert into educationtype 
(educationtype, coverage) values ('Seminars', 0.6);

insert into educationtype 
(educationtype, coverage) values ('Other', 0.3);

insert into employee 
(firstname, lastname, reportsto, isdh, isbenco, email, password)
values
('Napoleon', 'Bonaparte', null, false, false, 'empror4lyfe@aol.com', 'NotShort');

insert into employee 
(firstname, lastname, reportsto, isdh, isbenco, email, password)
values
('John', 'Doe', 1, false, false, 'johndoe@yahoo.com', 'WhoAmI');

insert into employee 
(firstname, lastname, reportsto, isdh, isbenco, email, password)
values
('Terry', 'Scabber', 2, false, false, 'password@protonmail.com', '12345');

insert into employee 
(firstname, lastname, reportsto, isdh, isbenco, email, password)
values
('Daddy', 'Warbucks', null, false, true, 'moneymoney@gmail.com', 'MusicalFan33');

insert into employee 
(firstname, lastname, reportsto, isdh, isbenco, email, password)
values
('Sidney', 'Smith', null, true, false, 'pronouncedsir@ymail.com', 'napoleonsux');

insert into employee 
(firstname, lastname, reportsto, isdh, isbenco, email, password)
values
('Big', 'Daddy', 1, false, false, 'bosshogg@juno.com', 'TheyMadeMeDoIt');

insert into reimbursement 
(employeeid, educationtypeid)
values
(2, 3);

insert into formdetails 
(applicationid, formfirstname, formlastname, formeventname, formeventcost,formeventstartdate, formeventaddress, formeventcity, formeventstate, formeventzip, formgradedformat, formdescription)
values
(1, 'John', 'Doe', 'TrainingTest', 400, '09-11-2020', '123 Testing Lane', 'Testington', 'Testsiana', 12345, 'Presentation', 'Blahblahblah');

alter table educationtype 
add primary key (educationtype);


--Triggers


--Update Functions
--Trigger needed: If DS and DH are same person, flip both flags.

--create or replace function urgent_flip() --NOT TESTED, REVIEW
--returns trigger as $$
--begin 
--	update reimbursement 
--	set urgent = true
--	where 7 > date_part('day', "formdetails"."formeventstartdate" - submittedon);
--end;
--$$ language plpgsql;

--create or replace function overdue_flip() --NOT IMPLEMENTED, REVIEW
--returns trigger as $$
--begin 
--	update approval 
--	set dsapprovalflag = true,
--	set dhapprovalflag = true,
--	where 14 > date_part('day', curdate() - submittedon);
--end
--$$ language plpgsql;

