-- ******* Initialize simple DB:for testing VlibTour ********;
-- **********************************************************;

-- **************************************  USERS 

CREATE TABLE  USERS 
(
  UserID        int NOT NULL  ,
  User_Name     varchar(50) NOT NULL ,
  User_Phone    varchar(50) NOT NULL ,
  User_Address  varchar(100) NOT NULL ,
PRIMARY KEY ( UserID )
);

INSERT INTO USERS
    (UserID, User_Name, User_Phone, User_Address)
VALUES
    (101, 'Joe', '0783187922', '7 Cite Universitaire 75015'),
    (102, 'Avrel', '0893134988', '19 place du Panthéon 75005'),
    (103, 'William', '0683187924', '15 boulevard carnot 75012');


-- **************************************  TOURDATE 

CREATE TABLE  TOURDATE 
(
  DateID      int NOT NULL  ,
  Tour_Date   date NOT NULL ,
  Start_Time  time NOT NULL ,
  End_Time    time NOT NULL ,
  Duration    char(20) NOT NULL ,
PRIMARY KEY ( DateID )
);

INSERT INTO TOURDATE
    (DateID, Date, Start_Time, End_Time, Duration)
VALUES
    (201, '2018-10-22', '08:00:00', '10:00:00', '2 hours'),
    (202, '2018-11-10', '15:00:00', '20:30:00', '4 hours 30 mins'),
    (203, '2018-12-14', '14:45:00', '18:00:00', '3 hours 45 mins');



-- **************************************  TOURS

CREATE TABLE  TOURS 
(
  TourID           int NOT NULL  ,
  DateID           int NOT NULL ,
  TourName         varchar(50) NOT NULL ,
  TourDescription  varchar(200) NOT NULL ,
  TourStatus       varchar(20) NOT NULL ,
PRIMARY KEY ( TourID ),
CONSTRAINT  FK_26  FOREIGN KEY   ( DateID ) REFERENCES  TOURDATE  ( DateID )
);

INSERT INTO TOURS
    (TourID, DateID, TourName, TourDescription, TourStatus)
VALUES
    (301, 201, 'TourName_1', 'TourDescription_1', 'Available'),
    (302, 202, 'TourName_2', 'TourDescription_2', 'Full'),
    (303, 203, 'TourName_3', 'TourDescription_3', 'Available');




-- **************************************  POI 

CREATE TABLE  POIS 
(
  POID             int NOT NULL  ,
  TourID           int NOT NULL ,
  POI_Name         varchar(50) NOT NULL ,
  POI_Description  varchar(200) NOT NULL ,
  POI_Address      varchar(100) NOT NULL ,
PRIMARY KEY ( POID ),
CONSTRAINT  FK_20  FOREIGN KEY   ( TourID ) REFERENCES  TOURS  ( TourID )
);

INSERT INTO POIS
    (POID, TourID, POI_Name, POI_Description, POI_Address)
VALUES
    (401, 301, 'POI_Name_1', 'POI_Description_1', 'POI_Address_1'),
    (402, 302, 'POI_Name_2', 'POI_Description_2', 'POI_Address_2'),
    (403, 303, 'POI_Name_3', 'POI_Description_3', 'POI_Address_3');




-- **************************************  GROUPTOUR 

CREATE TABLE  GROUPS 
(
  GroupID           int NOT NULL  ,
  No0fParticipants  int NOT NULL ,
 PRIMARY KEY ( GroupID ),
 CONSTRAINT  FK_41  FOREIGN KEY   ( GroupID) REFERENCES  TOURS  ( TourID )
);

INSERT INTO GROUPS
    (GroupID, No0fParticipants)
VALUES
    (301, 3),
    (302, 10),
    (303, 7);




-- **************************************  REGISTER

CREATE TABLE REGISTER
(
 RegisterID int NOT NULL  ,
 GroupID    int NOT NULL ,
 UserID     int NOT NULL ,
PRIMARY KEY (RegisterID),
CONSTRAINT FK_60 FOREIGN KEY (GroupID) REFERENCES GROUPS (GroupID),
CONSTRAINT FK_63 FOREIGN KEY (UserID) REFERENCES USERS (UserID)
);

INSERT INTO REGISTER
    (RegisterID, GroupID, UserID)
VALUES
    (1, 301, 101),
    (2, 302, 102),
    (3, 303, 103);


