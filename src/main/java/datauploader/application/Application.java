package datauploader.application;

import datauploader.data.ProductStorage;

import java.io.IOException;

public class Application {
  public static void main(String[] args) {
    String inputPath = args[0];
    String destinationPath = args[1];

    ProductStorage productStorage = new ProductStorage(inputPath);
    try {
      productStorage.loadProducts();
      productStorage.writeToJSON(destinationPath);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
