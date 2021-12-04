-- Create the Database if not Exist
CREATE DATABASE IF NOT EXISTS ad;
USE ad;


-- Create Item Table
CREATE TABLE Item (
                      ItemID INT NOT NULL,
                      Name VARCHAR(100) NOT NULL,
                      Currently DECIMAL(8,2) NOT NULL,
                      FirstBid DECIMAL(8,2) NOT NULL,
                      Number_Of_Bids INT NOT NULL,
                      Location VARCHAR(100) NOT NULL,
                      Country VARCHAR(100) NOT NULL,
                      Started TIMESTAMP NOT NULL,
                      Ends TIMESTAMP NOT NULL,
                      BuyPrice DECIMAL(8,2) NOT NULL, --Could be Null maybe default value "-"
                      SellerID VARCHAR(100) NOT NULL,
                      Description VARCHAR(4000) NOT NULL,
                      PRIMARY KEY (ItemID),
                      FOREIGN KEY (SellerID) REFERENCES Seller(SellerID)
                      FOREIGN KEY (Location) REFERENCES Seller(SellerLocation)
);

-- Create ItemCategory Table
CREATE TABLE ItemCategory (
                              ItemID INT NOT NULL,
                              Category VARCHAR(100) NOT NULL,
                              PRIMARY KEY (ItemID, Category),
                              FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);

-- Create Bid Table
CREATE TABLE Bids (
                     UserID VARCHAR(100) NOT NULL,
                     Time TIMESTAMP NOT NULL,
                     ItemID INT NOT NULL,
                     Amount DECIMAL(8, 2) NOT NULL,
                     PRIMARY KEY (UserID, Time),
                     FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
                     FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Bidder Table
CREATE TABLE Bidder (
                        BidderID VARCHAR(100) NOT NULL,
                        BidderRating INT NOT NULL,
                        BidderLocation VARCHAR(100) NOT NULL, --could eventually be null
                        BidderCountry VARCHAR(100) NOT NULL,  --could eventually be null
                        PRIMARY KEY (BidderID)
);

-- Create Table Seller
CREATE TABLE Seller (
                        SellerID VARCHAR(100) NOT NULL,
                        SellerRating INT NOT NULL,
                        SellerLocation VARCHAR(100) NOT NULL,
                        SellerCountry VARCHAR(100) NOT NULL,
                        PRIMARY KEY (SellerID)
);

-- Create GeographicCoordinateSystem Table
CREATE TABLE GeographicCoordinates (
                                        ItemID INT NOT NULL,
                                        Latitude DECIMAL(3, 6) NOT NULL,
                                        Longitude DECIMAL(3, 6) NOT NULL,
                                        PRIMARY KEY (ItemID),
                                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);

-- -- Create BuyPrice Table
-- CREATE TABLE BuyPrice (
--                           ItemID INT NOT NULL,
--                           BuyPrice DECIMAL(8,2) NOT NULL,
--                           PRIMARY KEY (ItemID),
--                           FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
-- );
