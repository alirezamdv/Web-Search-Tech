# Assignment 2: Search and Retrieval

1- use JDBC to fetch data from the "ad" database,
2- insert this data into a Lucene index for full text search, and
3- provide a search function that combines Lucene's full text search with MySQL's spatial queries.

## Part A: Create a Spatial Index in MySQL

### TODOs

- [ ] create a new table that associates geo-coordinates (as POINTs) to item-IDs, i.e., a table with two columns: one column for item-IDs, and another column for geo-coordinates. Geo-coordinates are points, represented using the POINT data type of MySQL.
- [ ] fill this table with all items that have latitude and longitude information, i.e., write a SQL insert into statement with a SQL query that selects each item-ID together with its latitude and longitued information (converted into a POINT).
- [ ] create a spatial index for the point column of your table from the previous step. Recall that the point-column must be declared as NOT NULL and that the table of the previous step must be created using ENGINE=MyISAM, i.e., the statement of the previous step has this structure
` CREATE TABLE IF NOT EXISTS xxx (...) ENGINE = MyISAM; `

## Part B: Create a Lucene Index

Here you are asked to write a program Indexer.java that creates a lucene index.

The index should be stored in a directory named **indexes** (under the current working directory from which runLoad.sh is called).


## Part C: Implement the Search Function

You will write the Java program Searcher.java which carries out keyword and spatial search over the ebay data. The Searcher program will take as first argument a list of space-separated keywords, given within quotes. For instance:

` java Searcher "star trek" `

returns a list of item-IDs, item-names, and Lucene scores of all items that contain the word "star" or the word "trek" (or both) in the name of the item, or in one of the categories of the item, or in the description of the item. This list is returned in HTML format. In fact, just plain text without HTML-tags is fine. In the first line, print the number of hits. Your program should print to stdout, not a file. When you initilize the query parser, use again the SimpleAnalyzer of Lucene.

Your Searcher program should be implemented in such a way that it can also take three arguments, in the following way:
` java Searcher "star trek" -x longitude -y latitude -w width `



## Part D: Automize

Write a small runLoad.sh script. First, if they do not exists yet, this script creates the geo-coordinates table and the spatial index. Next, the script compiles your Indexer.java and runs it in order to build the Lucene index. Finally, the script compiles your Searcher.java program. Thus, after completion of this script, one may call the Searcher program via java Searcher "list of keywords" (or, with the additional parameters) to obtain the correct output.
