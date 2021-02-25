package datauploader.data;

import datauploader.logic.CSVFileReader;
import datauploader.logic.IFileReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import datauploader.logic.XMLFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ProductStorage {
  private final String inputPath;
  private final List<Product> productList;

  public ProductStorage(String inputPath) {
    this.inputPath = inputPath;
    this.productList = new LinkedList<>();
  }

  public void loadProducts() throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath))) {
      String filepath;
      IFileReader fileReader;

      while ((filepath = bufferedReader.readLine()) != null) {
        switch (filepath.split("\\.")[1].toLowerCase()) {
          case "csv" -> fileReader = new CSVFileReader(filepath);
          case "xml" -> fileReader = new XMLFileReader(filepath);
          default -> {
            fileReader = null;
            System.out.println("Unsupported file format!\n" + String.format("File omitted (%s)", filepath));
          }
        }

        if (fileReader != null) {
          productList.addAll(fileReader.readFile());
        }
      }
    }
  }

  public void writeToJSON(String destination) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.writeValue(new File(destination), productList);
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Product product : productList) {
      stringBuilder.append(product.toString());
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
}
