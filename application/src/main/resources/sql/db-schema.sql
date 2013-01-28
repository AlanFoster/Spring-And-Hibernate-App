--
-- The Employee table
--
create table employees (
  id int primary key auto_increment,
  firstName varchar(30),
  secondName varchar(30),
  jobTitle varchar(30),
  deskId varchar(30)
);