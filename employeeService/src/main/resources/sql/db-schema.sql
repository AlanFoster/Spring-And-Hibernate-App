--
-- This is the main db-schema that should be ran when creating the Employee application
-- If you are wishing to roll back the application, then run the 'rollback.sql' script
--


--
-- Create the Employee table
--
create table employees (
  id int primary key auto_increment,
  firstName varchar(30),
  secondName varchar(30),
  jobTitle varchar(30),
  deskId varchar(30)
);