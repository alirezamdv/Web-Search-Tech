#!/bin/bash

# clear the page
clear

# dropping the table
mysql < dropSpatialIndex.sql


# creating the table
mysql < createSpatialIndex.sql


