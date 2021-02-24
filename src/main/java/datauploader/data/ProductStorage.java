package datauploader.data;

import java.util.LinkedList;
import java.util.List;

public class ProductStorage {
  private final String inputPath;
  private final List<Product> productList;

  public ProductStorage(String inputPath) {
    this.inputPath = inputPath;
    this.productList = new LinkedList<>();
  }
}
