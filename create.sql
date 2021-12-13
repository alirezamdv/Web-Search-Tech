-- Create the Database
DROP DATABASE IF EXISTS ad;
CREATE DATABASE IF NOT EXISTS ad;

use ad;


-- Create GeographicCoordinateSystem Table
CREATE TABLE GeoLocation
(
    id        VARCHAR(100)  NOT NULL,
    Latitude  DECIMAL(9, 6) NOT NULL,
    Longitude DECIMAL(9, 6) NOT NULL,
    PRIMARY KEY (id)
);

-- Create Table User
CREATE TABLE User
(
    User_ID  VARCHAR(100) NOT NULL,
    Rating   INT          NOT NULL,
    PRIMARY KEY (User_iD)

);
-- Create Location Table
CREATE TABLE Location
(
    User_ID      VARCHAR(100) NOT NULL,
    Location    VARCHAR(100) NOT NULL,
    Country VARCHAR(100) NOT NULL,
    FOREIGN KEY (User_ID) REFERENCES User(User_ID)
);

-- Create Item Table
CREATE TABLE Item
(
    ItemID         INT           NOT NULL,
    Name           VARCHAR(100)  NOT NULL,
    Currently      DECIMAL(8, 2) DEFAULT 0,
    FirstBid       DECIMAL(8, 2) DEFAULT 0,
    Number_Of_Bids INT           NOT NULL,
    Geo_ID         VARCHAR(100)  NOT NULL,
    Started        TIMESTAMP     NOT NULL,
    Ends           TIMESTAMP     NOT NULL,
    BuyPrice       DECIMAL(8, 2) ,
    UserID         VARCHAR(100)  NOT NULL,
    Country        VARCHAR(100)  NOT NULL,
    Location       VARCHAR(100)  NOT NULL,
    Description    VARCHAR(4000) NOT NULL,
    PRIMARY KEY (ItemID),
    FOREIGN KEY (UserId) REFERENCES User(User_iD), 
    FOREIGN KEY (Geo_ID) REFERENCES GeoLocation(id)
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
    id     VARCHAR(100)  NOT NULL,
    UserID VARCHAR(100)  NOT NULL,
    Time   TIMESTAMP     NOT NULL,
    ItemID INT           NOT NULL,
    Amount DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (UserID, Time)
    -- FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
    -- FOREIGN KEY (UserID) REFERENCES User(User_iD)
);

-- Create Bidder Table
-- CREATE TABLE Bidder (
--                         BidderID VARCHAR(100) NOT NULL,
--                         BidderRating INT NOT NULL,
--                         BidderLocation VARCHAR(100) NOT NULL, --could eventually be null
--                         BidderCountry VARCHAR(100) NOT NULL,  --could eventually be null
--                         PRIMARY KEY (BidderID)
-- );

-- Create Table Seller
-- CREATE TABLE Seller (
--                         SellerID VARCHAR(100) NOT NULL,
--                         SellerRating INT NOT NULL,
--                         SellerLocation VARCHAR(100) NOT NULL,
--                         SellerCountry VARCHAR(100) NOT NULL,
--                         PRIMARY KEY (SellerID)
-- );


-- -- Create BuyPrice Table
-- CREATE TABLE BuyPrice (
--                           ItemID INT NOT NULL,
--                           BuyPrice DECIMAL(8,2) NOT NULL,
--                           PRIMARY KEY (ItemID),
--                           FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
-- );
