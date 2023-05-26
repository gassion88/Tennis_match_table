CREATE SCHEMA IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS Players;
DROP TABLE IF EXISTS Matches;

CREATE TABLE Players (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(30) UNIQUE NOT NULL );

CREATE TABLE Matches (
     ID INT PRIMARY KEY AUTO_INCREMENT,
     Player1 INT NOT NULL,
     Player2 INT NOT NULL,
     Winner INT  NOT NULL,
     foreign key (Player1) references Players(ID),
     foreign key (Player2) references Players(ID),
     foreign key (Winner) references Players(ID) );

insert into Players (Name) values ('John');


