package datauploader.logic;

import datauploader.data.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVFileReader implements IFileReader {
  private final String filepath;
  private final static String SEPARATOR = ";";

  public CSVFileReader(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public List<Product> readFile() throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
      List<Product> productList = new LinkedList<>();
      String fileline;
      String[] colNames = bufferedReader.readLine().split(SEPARATOR);

      while ((fileline = bufferedReader.readLine()) != null) {
        String[] values = fileline.split(SEPARATOR);
        String key = values[0];
        String identifier = values[1];
        String type = values[2];
        Map<String, Object> attributes = new HashMap<>();
        for (int i = 3; i < values.length; i++) {
          attributes.put(colNames[i], values[i]);
        }

        productList.add(new Product(type, key, identifier, attributes));
      }

      return productList;
    }
  }
}
