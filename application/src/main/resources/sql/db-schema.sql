##
## The Employee table
##
create table employees (
  id int primary key auto_increment,
  firstName varchar(30),
  secondName varchar(30),
  jobTitleId int,
  deskId varchar(30),
  index FK_JOBTITLE (jobTitleId),
  constraint FK_JOBTITLE foreign key (jobTitleId) references jobtitles (jobTitleId)
);

##
## Stores the available jobs
##
create table jobs (
  jobId int primary key auto_increment,
  jobTitle varchar(30)
);

##
## Insert the default job titles into the jobTitles database
##
insert into jobs (jobTitle) values ("HR"), ("Operations"), ("Developer"), ("Tester");


