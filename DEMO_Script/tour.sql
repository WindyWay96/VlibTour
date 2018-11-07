CREATE TABLE  Tour 
(
  TourID           int NOT NULL  ,
  DateID           int NOT NULL ,
  TourName         varchar(50) NOT NULL ,
  TourDescription  varchar(200) NOT NULL ,
  TourStatus       varchar(20) NOT NULL ,
PRIMARY KEY ( TourID ),
CONSTRAINT  FK_26  FOREIGN KEY   ( DateID ) REFERENCES  TOURDATE  ( DateID )
);

INSERT INTO Tour
    (TourID, DateID, TourName, TourDescription, TourStatus)
VALUES
    (301, 201, 'TourName_1', 'TourDescription_1', 'Available'),
    (302, 202, 'TourName_2', 'TourDescription_2', 'Full'),
    (303, 203, 'TourName_3', 'TourDescription_3', 'Available');
