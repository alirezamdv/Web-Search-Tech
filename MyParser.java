import java.io.File;
import java.io.FileReader;
import java.text.*;
import java.util.*;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//import Bid;

public class MyParser extends DefaultHandler {
//    private Bid bid;

    public static void main(String[] args)
             {
                 try {
                     SAXParserFactory factory = SAXParserFactory.newInstance();
                     SAXParser saxParser = factory.newSAXParser();
                     MyParser myParser = new MyParser();
                     String[] p = {"ebay_data/items-0.xml"};

                     // Parse each file provided on the
                     // command line.
                     for (String arg : p) {
                         File inputFile = new File(arg);

                         ((SAXParser) saxParser).parse(inputFile, myParser);

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
                             String qName, Attributes atts) {
        if (qName.equalsIgnoreCase("Item")) {
            System.out.println(atts.getValue("ItemID"));

        }

        if ("".equals (uri))
            System.out.println("Start element: " + qName);
        else
            System.out.println("Start element: {" + uri + "}" + name);
        for (int i = 0; i < atts.getLength(); i++) {
            System.out.println("Attribute: " + atts.getLocalName(i) + "=" + atts.getValue(i));
        }
    }


    public void endElement(String uri, String name, String qName) {
        if ("".equals(uri))
            System.out.println("End element: " + qName);
        else
            System.out.println("End element:   {" + uri + "}" + name);
    }


    public void characters(char ch[], int start, int length) {
        System.out.print("Characters:    \"");
        for (int i = start; i < start + length; i++) {
            switch (ch[i]) {
                case '\\':
                    System.out.print("\\\\");
                    break;
                case '"':
                    System.out.print("\\\"");
                    break;
                case '\n':
                    System.out.print("\\n");
                    break;
                case '\r':
                    System.out.print("\\r");
                    break;
                case '\t':
                    System.out.print("\\t");
                    break;
                default:
                    System.out.print(ch[i]);
                    break;
            }
        }
        System.out.print("\"\n");
    }

}
