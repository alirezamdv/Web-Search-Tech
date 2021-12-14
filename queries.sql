-- Find the number of users in the database.
SELECT COUNT(*) FROM (SELECT User_ID FROM Seller UNION SELECT User_ID FROM Bidder) as x;

-- Find the number of items in "New York", i.e., itmes whose location is exactly the string "New York". Pay special attention to case sensitivity. E.g., you should not match items in "new york".
SELECT COUNT(Location) FROM Item WHERE BINARY Item.Location='New York';

-- Find the number of auctions belonging to exactly four categories. Be careful to remove duplicates, if you store them.
SELECT count(*) FROM (SELECT ItemID FROM ItemCategory GROUP BY ItemId Having count(Category)=4) as ICII;

-- Find the ID(s) of current (unsold) auction(s) with the highest bid. Remember that the data was captured at December 20th, 2001, one second after midnight. Pay special attention to the current auctions without any bid.

SET @v1 := (SELECT MAX(Currently) FROM Item WHERE Number_Of_Bids > 0);
SELECT @v1;
SELECT ItemID
FROM Item
WHERE Currently = @v1 AND Number_Of_Bids > 0;

-- Find the number of sellers whose rating is higher than 1000.
SELECT COUNT(*) FROM Seller WHERE Seller.Rating > 1000;

-- Find the number of users who are both sellers and bidders.
SELECT COUNT(*) FROM Seller WHERE Seller.User_ID IN (SELECT User_ID FROM Bidder);

-Find the number of categories that include at least one item with a bid of more than $100.

SELECT COUNT(*) FROM (SELECT ItemCategory.Category FROM Bids, ItemCategory WHERE Bids.ItemID = ItemCategory.ItemID AND Bids.Amount > 100 GROUP BY ItemCategory.Category) AS compount;
