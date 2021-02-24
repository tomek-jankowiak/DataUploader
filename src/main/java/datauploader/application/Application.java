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
      System.out.println(productStorage.toString());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
