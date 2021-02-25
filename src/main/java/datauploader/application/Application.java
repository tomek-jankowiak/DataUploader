package datauploader.application;

import datauploader.data.ElementStorage;

import java.io.IOException;

public class Application {
  public static void main(String[] args) {
    String inputPath = args[0];
    String destinationPath = args[1];

    ElementStorage elementStorage = new ElementStorage(inputPath);
    try {
      elementStorage.loadProducts();
      elementStorage.writeToJSON(destinationPath);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
