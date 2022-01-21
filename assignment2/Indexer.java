import java.io.StringReader;
import java.io.File;
import java.nio.file.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.sql.*;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Indexer {
    private static HashMap<String, String> items = new HashMap<>();

    public Indexer() {
    }

    public static IndexWriter indexWriter;

    public static void main(String args[]) throws SQLException {
        queryToGetItemID_ItemName_Categories_Description(DbManager.getConnection(true));
//        String usage = "java Indexer";
        rebuildIndexes("indexes");
    }

    public static void insertDoc(IndexWriter i, String doc_id, String line) {
        Document doc = new Document();
        doc.add(new TextField("doc_id", doc_id, Field.Store.YES));
        doc.add(new TextField("line", line, Field.Store.YES));
        try {
            i.addDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rebuildIndexes(String indexPath) {
        try {
            Path path = Paths.get(indexPath);
            System.out.println("Indexing to directory '" + indexPath + "'...\n");
            Directory directory = FSDirectory.open(path);
            IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());
            //	    IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            //IndexWriterConfig config = new IndexWriterConfig(new EnglishAnalyzer());
            IndexWriter i = new IndexWriter(directory, config);
            i.deleteAll();
            for (Map.Entry<String, String> item : items.entrySet()) {
                insertDoc(i, item.getKey(), item.getValue());
            }

            i.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void queryToGetItemID_ItemName_Categories_Description(Connection connection) throws SQLException {

        String query = "SELECT item.item_id, item.item_name, \n" +
                "GROUP_CONCAT(category_name SEPARATOR ' ') AS categories, item.description\n" +
                "FROM item\n" +
                "JOIN has_category ON item.item_id = has_category.item_id GROUP BY item.item_id;";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String item_item_id = rs.getString("item.item_id");
                String item_item_name = rs.getString("item.item_name");
                String category_concatenated = rs.getString("categories_concatenated");
                String description = rs.getString("item.description");
                items.put(
                        item_item_id, concatenateString(item_item_name, category_concatenated, description)
                );
            }

        } catch (SQLException e) {
            e.getNextException();
        }
    }

    private static String concatenateString(String itemName, String categoriesConcatenated, String description) {
        return itemName + " " + categoriesConcatenated + " " + description;
    }
}
