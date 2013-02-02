--
-- This is the main db-schema that should be ran when creating the Employee application
-- If you are wishing to roll back the application, then run the 'rollback.sql' script
--

--
-- Create the Job table which stores the available jobs
--

CREATE TABLE jobs (
  jobId    INT PRIMARY KEY AUTO_INCREMENT,
  jobTitle VARCHAR(30)
);

--
-- Insert some example jobs into the jobs table
--

INSERT INTO jobs (jobTitle) VALUES ('HR');
INSERT INTO jobs (jobTitle) VALUES ('Operations');
INSERT INTO jobs (jobTitle) VALUES ('Engineer');
INSERT INTO jobs (jobTitle) VALUES ('Senior Engineer');
INSERT INTO jobs (jobTitle) VALUES ('Executive Engineer');
INSERT INTO jobs (jobTitle) VALUES ('Developer');
INSERT INTO jobs (jobTitle) VALUES ('Tester');

--
-- Create the Employee table
-- index FK_JOBTITLEID (jobId),
--
 CREATE TABLE employees (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  firstName  VARCHAR(30),
  secondName VARCHAR(30),
  jobId      INT,
  deskId     VARCHAR(30),
  CONSTRAINT FK_JOBTITLEID FOREIGN KEY (jobId) REFERENCES jobs (jobId)
);