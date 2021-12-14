import java.io.*;
import java.text.*;
import java.util.*;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

import javax.lang.model.type.NullType;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

///////////////////////////////////
// Different Classes for the tables
///////////////////////////////////

//Bidder Table
class Bidder {
    private String bidderID;
    private String bidderRating;

    public Bidder() {
    }

    public String getBidderID() {
        return bidderID;
    }

    public void setBidderID(String bidderID) {
        this.bidderID = bidderID;
    }

    public String getBidderRating() {
        return bidderRating;
    }

    public void setBidderRating(String bidderRating) {
        this.bidderRating = bidderRating;
    }

    @Override
    public String toString() {
        return bidderID + '\t' +
               bidderRating;
    }
}

//Bids Table
class Bids {
    private String userId;
    private String bidderTime;
    private String itemID;
    private String bidderAmount;

    public Bids() {
    }

    public String getBidderTime() {
        return bidderTime;
    }

    public void setBidderTime(String bidderTime) {
        this.bidderTime = bidderTime;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getBidderAmount() {
        return bidderAmount;
    }

    public void setBidderAmount(String bidderAmount) {
        this.bidderAmount = bidderAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return  userId + '\t' +
                bidderTime + '\t' +
                itemID + '\t' +
                bidderAmount;
    }
}



//Geolocation Table
class GeoLocation {
    private String id;
    private String itemLatitude;
    private String itemLongitude;


    public GeoLocation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemLatitude() {
        return itemLatitude;
    }

    public void setItemLatitude(String itemLatitude) {
        this.itemLatitude = itemLatitude;
    }

    public String getItemLongitude() {
        return itemLongitude;
    }

    public void setItemLongitude(String itemLongitude) {
        this.itemLongitude = itemLongitude;
    }

    @Override
    public String toString() {
        return id + '\t' +
               itemLatitude + '\t' +
               itemLongitude;
    }
}

//Item Table
class Item {
    private String itemID;
    private String name;
    private String currently;
    private String firstBid;
    private String numberOfBids;
    private String buyPrice;
    private String geo_id;
    private String started;
    private String ends;
    private String userID;
    private String description;
    private String country;
    private String location;

    public Item() {
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrently() {
        return currently;
    }

    public void setCurrently(String currently) {
        this.currently = currently;
    }

    public String getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(String firstBid) {
        this.firstBid = firstBid;
    }

    public String getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(String numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getGeo_id() {
        return geo_id;
    }

    public void setGeo_id(String geo_id) {
        this.geo_id = geo_id;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return itemID + "\t" +
                name + "\t" +
                currently + "\t" +
                firstBid + "\t" +
                numberOfBids + "\t" +
                started + "\t" +
                ends + "\t" +
                buyPrice + "\t" +
                userID + "\t" +
                country + "\t" +
                location + "\t" +
                description;
    }
}



//ItemCategory Table
class ItemCategory {
    private String itemID;
    private String itemCategory;

    public ItemCategory() {
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String toString() {
        return  itemID + '\t' +
                itemCategory;
    }
}


//Location Table
class Location {
    private String id;
    private String location;
    private String country;

    public Location() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return id + '\t' +
                location + '\t' +
                country;
    }

}

//Seller Table

class Seller {
    private String sellerID;
    private String sellerRating;

    public Seller() {
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    @Override
    public String toString() {
        return sellerID + '\t' +
               sellerRating;
    }
}

////////////////////////
//MySax Parser class
////////////////////////

public class MySAX extends DefaultHandler {

    // csv path for tables
    private static final String ITEM_PATH = "Item.csv";
    private static final String BID_PATH = "Bids.csv";
    private static final String CATEGORY_PATH = "ItemCategory.csv";
    private static final String USER_PATH = "User.csv";
    private static final String BIDDER_PATH = "Bidder.csv";
    private static final String SELLER_PATH = "Seller.csv";
    private static final String LOCATION_PATH = "Location.csv";
    private static final String GEO_PATH = "GeoLocation.csv";


    // for eliminating the duplicates
    private final LinkedList<String> bidUserIDLinkedList = new LinkedList<>();
    private final LinkedList<String> sellerUserIDLinkedList = new LinkedList<>();
    private final LinkedList<String> bidderUserIDLinkedList = new LinkedList<>();
    private static final Set<ItemCategory> itemCategorySet = new LinkedHashSet<>();

    private boolean writeToBidderTableAllowed = false;
    private boolean writeToSellerTableAllowed = false;

    private boolean isGeolocation = false;


    //for holding  the string value from parsing
    String currentValue = "";

    boolean inItem = false;
    boolean inBid = false;
    private int bidNumber = 0;
    boolean isInDescription = false;
    boolean isInCategory = false;
    private Item item;
    private Bids bids;
    private Bidder bidder;
    private Seller seller;
    private ItemCategory itemCategory;
//    private Location seller_location;
    private Location bidder_location;
    private GeoLocation geoLocation;

    // for description,
    private StringBuilder builder;

    // for Category,
    private StringBuilder categoryBuilder;

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MySAX mySax = new MySAX();
//            String[] p = {"ebay_data/items-1.xml"};

            // Parse each file provided on the
            // command line.
            for (String arg : args) {
                File inputFile = new File(arg);

                saxParser.parse(inputFile, mySax);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public MySAX() {
        super();
    }

    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try {
                am = nf.parse(money).doubleValue();
            } catch (ParseException e) {
                System.out.println("This method should work for all " +
                        "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////


    public void startDocument() {
        System.out.println("Start document");
    }


    public void endDocument() {
        System.out.println("End document");
    }


    public void startElement(String uri, String name,
                             String qName, Attributes atts)
            throws SAXException {
        if (qName.equalsIgnoreCase("Item")) {
            inItem = true;
            item = new Item();
            seller = new Seller();
            //seller.setUser_id(atts.getValue("UserID"));
            itemCategory = new ItemCategory();


            // set new items
            // for holding the itemID
            String itemID = atts.getValue("ItemID");
            item.setItemID(itemID);
            itemCategory.setItemID(itemID);

        } else if (qName.equalsIgnoreCase("Bid")) {
            inBid = true;
            bids = new Bids();
            bids.setItemID(item.getItemID());
            //bids.setId(generateUUID());

        } else if (qName.equalsIgnoreCase("Bidder")) {
            String user_id = atts.getValue("UserID");
            String bidderRating = atts.getValue("Rating");
            if (!bidderUserIDLinkedList.contains(user_id)) {
                bidder = new Bidder();
                bidder.setBidderID(user_id);
                bidder.setBidderRating(bidderRating);

                bidderUserIDLinkedList.add(user_id);
                writeToBidderTableAllowed = true;
            }
            //bid and bidder(user) relation
            bids.setUserId(atts.getValue("UserID"));


        } else if (qName.equalsIgnoreCase("Seller")) {
            String user_id = atts.getValue("UserID");
            String sellerRating = atts.getValue("Rating");
            if (!sellerUserIDLinkedList.contains(user_id)) {
                //seller.setUser_id(atts.getValue("UserID"));
                seller.setSellerID(user_id);
                seller.setSellerRating(sellerRating);

                writeToSellerTableAllowed = true;
                sellerUserIDLinkedList.add(user_id);
            }
            // Item and seller(user) relation
            item.setUserID(user_id);


        } else if (qName.equalsIgnoreCase("Description")) {

            //System.out.println("in Description");
            isInDescription = true;
            builder = new StringBuilder();

        } else if (qName.equalsIgnoreCase("Category")) {
            isInCategory = true;
            categoryBuilder = new StringBuilder();

        } else if (qName.equalsIgnoreCase("Location")) {
            if (inBid) {
                bidder_location = new Location();
                //bidder_location.setId(generateUUID());
                bidder_location.setId(bidder.getBidderID());
            }


            if (atts.getLength() == 2) {
                geoLocation = new GeoLocation();
                geoLocation.setId(item.getItemID());
                geoLocation.setItemLatitude(atts.getValue("Latitude"));
                geoLocation.setItemLongitude(atts.getValue("Longitude"));

                //location and geolocation relation
                //item.setGeo_id(geoLocation.getId());

                isGeolocation = true;
            }

        }


//        if ("".equals (uri)){
//        System.out.println("Start element: " + qName);}
//        else
//            System.out.println("Start element: {" + uri + "}" + name);
//        for (int i = 0; i < atts.getLength(); i++) {
//            System.out.println("Attribute: " + atts.getLocalName(i) + "=" + atts.getValue(i));
//        }
    }


    public void endElement(String uri, String name, String qName) {
        if (qName.equalsIgnoreCase("Item")) {
            //writeToTable(bidder, BIDDER_PATH);
            if (writeToSellerTableAllowed) {
                writeToTable(seller, SELLER_PATH);
//                writeToTable(seller_location, LOCATION_PATH);

                writeToSellerTableAllowed = false;

            }
            if (bidNumber > 0) {
                writeToTable(bids, BID_PATH);
                bidNumber = 0;
            }
            writeToTable(item, ITEM_PATH);

        } else if (qName.equalsIgnoreCase("Name")) {
            item.setName(currentValue);
        } else if (qName.equalsIgnoreCase("Category")) {
            isInCategory = false;
            String categoryFullText = categoryBuilder.toString();
            itemCategory.setItemCategory(categoryFullText);
            writeToTable(itemCategory, CATEGORY_PATH);
        } else if (qName.equalsIgnoreCase("Currently")) {
            item.setCurrently(strip(currentValue));
        } else if (qName.equalsIgnoreCase("Buy_Price")) {
            item.setBuyPrice(strip(currentValue));
        } else if (qName.equalsIgnoreCase("First_Bid")) {
            item.setFirstBid(strip(currentValue));
        } else if (qName.equalsIgnoreCase("Number_of_Bids")) {
            item.setNumberOfBids(currentValue);
            try {
                bidNumber = Integer.parseInt(currentValue);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        } else if (qName.equalsIgnoreCase("Time")) {
            bids.setBidderTime(convertTimeToMySQL(currentValue));
        } else if (qName.equalsIgnoreCase("Amount")) {
            bids.setBidderAmount(strip(currentValue));
        } else if (qName.equalsIgnoreCase("Bid")) {
            //write bid to table
            writeToTable(bids, BID_PATH);
            inBid = false;
        } else if (qName.equalsIgnoreCase("Location")) {
            //This part is not clear yet
            if (inBid) {
                bidder_location.setLocation(currentValue);
            } else {
//                seller.setLocation_id(seller_location.getId());
                item.setLocation(currentValue);

                if (isGeolocation) {
                    //write to table geolocation
                    writeToTable(geoLocation, GEO_PATH);
                    isGeolocation = false;
                }
            }

        } else if (qName.equalsIgnoreCase("Country")) {
            if (inBid) {
                bidder_location.setCountry(currentValue);
            } else {
                item.setCountry(currentValue);
            }

        } else if (qName.equalsIgnoreCase("Started")) {

            item.setStarted(convertTimeToMySQL(currentValue));

        } else if (qName.equalsIgnoreCase("Ends")) {

            item.setEnds(convertTimeToMySQL(currentValue));

        } else if (qName.equalsIgnoreCase("Bidder")) {
            
            if (writeToBidderTableAllowed) {
                writeToTable(bidder, BIDDER_PATH);
                writeToTable(bidder_location, LOCATION_PATH);
                writeToBidderTableAllowed = false;
            }
            // bidder(user) and location relation
            //bidder.setLocation_id(bidder_location.getId());

        } else if (qName.equalsIgnoreCase("Description")) {

            isInDescription = false;

            String descriptionFullText = builder.toString();

            builder.setLength(0);
            // shrink the item to 4000 characters only
            if (descriptionFullText.length() > 4000) {
                descriptionFullText = descriptionFullText.substring(0, 3999);
            }
//             to replace new lines, because this new lines break the number of
//             rows in db
            descriptionFullText = descriptionFullText
                    .replace("\n", "")
                    .replace("\r", "");
            item.setDescription(descriptionFullText);
        }

    }
//        if ("".equals(uri))
//            System.out.println("End element: " + qName);
//        else
//            System.out.println("End element:   {" + uri + "}" + name);


    public void characters(char ch[], int start, int length) {

        if (isInDescription) {
            builder.append(ch, start, length);
        } else if (isInCategory) {
            categoryBuilder.append(ch, start, length);
        } else {
            // for holding the value from parsing
            currentValue = new String(ch, start, length);
        }
//            switch (ch[i]) {
//                case '\\':
//                    System.out.print("\\\\");
//                    break;
//                case '"':
//                    System.out.print("\\\"");
//                    break;
//                case '\n':
//                    System.out.print("\\n");
//                    break;
//                case '\r':
//                    System.out.print("\\r");
//                    break;
//                case '\t':
//                    System.out.print("\\t");
//                    break;
//                default:
//                    System.out.print(ch[i]);
//                    break;
//            }

//        System.out.print("\"\n");
    }


    private static <T> void writeToTable(T table, String tablePath) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(tablePath, true));
            bw.write(table.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    private static String convertTimeToMySQL(String eBayTime) {
        SimpleDateFormat ebayTimePattern = new SimpleDateFormat("MMM-dd-yy HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat MySQLPattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String MySQLFormattedPattern = "";
        try {
            Date parsedEbayTime = ebayTimePattern.parse(eBayTime);
            MySQLFormattedPattern = MySQLPattern.format(parsedEbayTime);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return MySQLFormattedPattern;
    }

    private static String generateUUID() {
        UUID id = UUID.randomUUID();
        return String.valueOf(id);
    }
}
