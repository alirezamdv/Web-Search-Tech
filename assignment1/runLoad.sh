#!/bin/bash

rm *.csv
# Dropping the Table
mysql <drop.sql
echo "Tables Were Dropped Successfully."

# Creating the Database and the Tables
mysql <create.sql
echo "Database and Tables Were Created Successfully."

# Compile and run the convertor
~/.jdks/openjdk-17.0.2/bin/javac MySAX.java
echo "Compiling the Project is Done."

~/.jdks/openjdk-17.0.2/bin/java MySAX ebay-data/items-*.xml
echo "Parsing the XML Files are Done."

# Run the load.sql batch file to load the data
mysql --local-infile <load.sql
echo "All Data (CSV Files) Have been Successfully Populated into the Database."
