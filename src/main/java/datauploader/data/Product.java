package datauploader.data;

import java.util.Map;

public class Product {
  private String type;
  private String key;
  private String identifier;
  private Map<String, Object> simpleAttributes;

  public Product(String type,
                 String key,
                 String identifier,
                 Map<String, Object> attributes) {
    this.type = type;
    this.key = key;
    this.identifier = identifier;
    this.simpleAttributes = attributes;

  }
}
