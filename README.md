# Web-Search-Tech

1. design a database schema for a given dataset,
2. shred XML data into csv-files, and
3. create tables in a database and populate them from the csv-files.

- We use Sax Parser because of the most important advantage that the resulting program uses hardly any memory! Thus, it can handle huge XML files
- Java Version in VM is "openjdk 17" so we changed the deprecated Class with SaxParsrFactory [documentation](https://www.tutorialspoint.com/java_xml/java_sax_parse_document.htm) 