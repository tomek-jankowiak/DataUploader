package datauploader.application;

import datauploader.data.ProductStorage;

public class Application {
  public static void main(String[] args) {
    String inputPath = args[0];
    String destinationPath = args[1];

    ProductStorage productStorage = new ProductStorage(inputPath);
  }
}
