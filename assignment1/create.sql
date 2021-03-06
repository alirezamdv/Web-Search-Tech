-- Create the Database
DROP DATABASE IF EXISTS ad;
CREATE DATABASE IF NOT EXISTS ad;

use ad;

-- Create Table Bidder
CREATE TABLE Bidder
(
    User_ID  VARCHAR(100) NOT NULL,
    Rating   INT          NOT NULL,
    PRIMARY KEY (User_ID)
);
-- Create Location Table
CREATE TABLE Location
(
    User_ID     VARCHAR(100) NOT NULL,
    Location    VARCHAR(100) NOT NULL,
    Country VARCHAR(100) NOT NULL,
    PRIMARY KEY (User_ID),
    FOREIGN KEY (User_ID) REFERENCES Bidder(User_ID)
);

-- Create Table Seller
CREATE TABLE Seller
(
    User_ID  VARCHAR(100) NOT NULL,
    Rating   INT          NOT NULL,
    PRIMARY KEY (User_ID)
);

-- Create Item Table
CREATE TABLE Item
(
    ItemID         INT           NOT NULL,
    Name           VARCHAR(100)  NOT NULL,
    Currently      DECIMAL(8, 2) ,
    FirstBid       DECIMAL(8, 2) ,
    Number_Of_Bids INT           NOT NULL,
    Started        TIMESTAMP     NOT NULL,
    Ends           TIMESTAMP     NOT NULL,
    BuyPrice       DECIMAL(8, 2) ,
    User_ID        VARCHAR(100)  ,
    Country        VARCHAR(100)  NOT NULL,
    Location       VARCHAR(100)  NOT NULL,
    Description    VARCHAR(4000) NOT NULL,
    PRIMARY KEY (ItemID),
    FOREIGN KEY (User_ID) REFERENCES Seller(User_ID)
);

-- Create GeoLocation Table
CREATE TABLE GeoLocation
(
    ItemID    INT  NOT NULL,
    Latitude  DECIMAL(9, 6) NOT NULL,
    Longitude DECIMAL(9, 6) NOT NULL,
    PRIMARY KEY (ItemID),
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);

-- Create ItemCategory Table
CREATE TABLE ItemCategory
(
    ItemID   INT          NOT NULL,
    Category VARCHAR(100) NOT NULL,
    PRIMARY KEY (ItemID, Category),
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);

-- Create Bid Table
CREATE TABLE Bids
(
    User_ID VARCHAR(100)  NOT NULL,
    Time   TIMESTAMP     NOT NULL,
    ItemID INT           NOT NULL,
    Amount DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (User_ID, Time),
    FOREIGN KEY (User_ID) REFERENCES Bidder(User_ID),
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);
