USE ad;
CREATE TABLE IF NOT EXISTS geo 
  ( 
     itemID   INTEGER NOT NULL, 
     Location POINT NOT NULL, 
     SPATIAL INDEX (Location), 
     PRIMARY KEY (itemID), 
     FOREIGN KEY (itemID) REFERENCES item(ItemID) 
  ) 
ENGINE=MyISAM; 

INSERT INTO geo 
            (itemID, 
             Location) 
SELECT item_id, 
       Geomfromwkb(Point(item_coordinates.latitude, item_coordinates.longitude))
FROM   item_coordinates;
