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


public class MyParser extends DefaultHandler {

    // csv path for tables
    private static final String ITEM_PATH = "Item.csv";
    private static final String BID_PATH = "Bids.csv";
    private static final String CATEGORY_PATH = "ItemCategory.csv";
    private static final String BIDDER_PATH = "Bidder.csv";
    private static final String SELLER_PATH = "Seller.csv";
    private static final String GEO_PATH = "GeographicCoordinateSystem.csv";


    

    //for holding  the string value from parsing
    String currentValue = "";

    boolean inItem = false;
    boolean inBid = false;
    boolean isInDescription = false;
    private Item item;
    private Bids bids;
    private Bidder bidder;
    private Seller seller;
    private ItemCategory itemCategory;
    private GeoLocation geoLocation;

    // for description,
    private StringBuilder builder;

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MyParser myParser = new MyParser();
            String[] p = {"./items-0.xml"};

            // Parse each file provided on the
            // command line.
            for (String arg : p) {
                File inputFile = new File(arg);

                saxParser.parse(inputFile, myParser);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public MyParser() {
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
            bids = new Bids();
            bidder = new Bidder();
            seller = new Seller();
            itemCategory = new ItemCategory();
            geoLocation = new GeoLocation();


            // set new items
            // for holding the itemID
            String itemID = atts.getValue("ItemID");
            item.setItemID(itemID);
            bids.setItemID(itemID);

            geoLocation.setItemID(itemID);
            itemCategory.setItemID(itemID);

            System.out.println("in item");
        } else if (qName.equalsIgnoreCase("Bid")) {
            inBid = true;
            System.out.println("in Bid");

        } else if (qName.equalsIgnoreCase("Bidder")) {
            String bidderID = atts.getValue("UserID");
            String bidderRating = atts.getValue("Rating");
            bids.setBidderID(bidderID);
            bidder.setBidderID(bidderID);
            bidder.setBidderRating(bidderRating);


            System.out.println("in Bid");

        } else if (qName.equalsIgnoreCase("Seller")) {
            String sellerID = atts.getValue("UserID");
            String sellerRating = atts.getValue("Rating");
            item.setSellerID(sellerID);
            seller.setSellerID(sellerID);
            seller.setSellerRating(sellerRating);

            System.out.println("in Seller");

        } else if (qName.equalsIgnoreCase("Description")) {

            System.out.println("in Description");
            isInDescription = true;
            builder = new StringBuilder();

        } else if (qName.equalsIgnoreCase("Location")) {
            String lat = atts.getValue("Latitude");
            String longi = atts.getValue("Longitude");


            if (lat!=null) {
                geoLocation.setItemLatitude(lat);
                geoLocation.setItemLongitude(longi);
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
            writeToTable(seller, SELLER_PATH);
            writeToTable(item, ITEM_PATH);
        } else if (qName.equalsIgnoreCase("Name")) {
            item.setName(currentValue);
        } else if (qName.equalsIgnoreCase("Category")) {
            itemCategory.setItemCategory(currentValue);
        } else if (qName.equalsIgnoreCase("Currently")) {
            item.setCurrently(strip(currentValue));
        } else if (qName.equalsIgnoreCase("Buy_Price")) {
            item.setBuyPrice(strip(currentValue));
        } else if (qName.equalsIgnoreCase("First_Bid")) {
            item.setFirstBid(strip(currentValue));
        } else if (qName.equalsIgnoreCase("Number_of_Bids")) {
            item.setNumberOfBids(currentValue);
        } else if (qName.equalsIgnoreCase("Time")) {
            bids.setBidderTime(convertTimeToMySQL(currentValue));
        } else if (qName.equalsIgnoreCase("Amount")) {
            bids.setBidderAmount(strip(currentValue));
        } else if (qName.equalsIgnoreCase("Bid")) {
            //write bid and user to tables
            writeToTable(bids, BID_PATH);
            writeToTable(bidder, BIDDER_PATH);
            

            inBid = false;

        } else if (qName.equalsIgnoreCase("Location")) {


           //This part is not clear yet 
            if (inBid) {
                bidder.setBidderLocation(currentValue);
            } else {
                String lat = geoLocation.getItemLatitude();
                if (lat!=null){
                    //write to table geolocation
                    writeToTable(geoLocation, GEO_PATH);
                    //"reset" geoLocation
                    //geoLocation = new GeoLocation()
                    seller.setSellerLocation(currentValue);
                } else {
                    seller.setSellerLocation(currentValue);
                }
            }
            
        } else if (qName.equalsIgnoreCase("Country")) {
            if (inBid) {
                bidder.setBidderCountry(currentValue);
            } else {
                seller.setSellerCountry(currentValue);
            }
            

        } else if (qName.equalsIgnoreCase("Started")) {

            item.setStarted(convertTimeToMySQL(currentValue));

        } else if (qName.equalsIgnoreCase("Ends")) {

            item.setEnds(convertTimeToMySQL(currentValue));

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
            }else {
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
        SimpleDateFormat ebayTimePattern = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
        SimpleDateFormat MySQLPattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String MySQLFormattedPattern = "";
        try {
            Date parsedEbayTime = ebayTimePattern.parse(eBayTime);
            MySQLFormattedPattern = MySQLPattern.format(parsedEbayTime);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return MySQLFormattedPattern;
    }

}
