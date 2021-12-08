#!/bin/bash

rm *.csv
# Dropping the Table
mysql  < drop.sql
echo "Tables Were Dropped Successfully."

# Creating the Database and the Tables
mysql  < create.sql
echo "Database and Tables Were Created Successfully."

# Compile and run the convertor
javac MyParser.java
echo "Compiling the Project is Done."

java MySParser ebay_data/items-*.xml
echo "Parsing the XML Files are Done."

# Run the load.sql batch file to load the data
mysql --local-infile  < load.sql
echo "All Data (CSV Files) Have been Successfully Populated into the Database."





