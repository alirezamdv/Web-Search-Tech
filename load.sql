use ad;

LOAD DATA LOCAL INFILE 'Bidder.csv' INTO TABLE Bidder FIELDS TERMINATED BY '\t'LINES TERMINATED BY '\n';
LOAD DATA LOCAL INFILE 'Location.csv' INTO TABLE Location FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';
LOAD DATA LOCAL INFILE 'Seller.csv' INTO TABLE Seller FIELDS TERMINATED BY '\t'LINES TERMINATED BY '\n';
LOAD DATA LOCAL INFILE 'Item.csv' INTO TABLE Item FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';
LOAD DATA LOCAL INFILE 'GeoLocation.csv' INTO TABLE GeoLocation FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';
LOAD DATA LOCAL INFILE 'ItemCategory.csv' INTO TABLE ItemCategory FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';
LOAD DATA LOCAL INFILE 'Bids.csv' INTO TABLE Bids FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n';
