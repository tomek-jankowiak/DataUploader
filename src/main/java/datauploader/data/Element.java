package datauploader.data;

import java.util.Map;

public class Element {
  private String type;
  private String key;
  private String identifier;
  private Map<String, Object> simpleAttributes;

  public Element() {
  }

  public Element(String type,
                 String key,
                 String identifier,
                 Map<String, Object> attributes) {
    this.type = type;
    this.key = key;
    this.identifier = identifier;
    this.simpleAttributes = attributes;
  }

  public String getType() {
    return type;
  }

  public String getKey() {
    return key;
  }

  public String getIdentifier() {
    return identifier;
  }

  public Map<String, Object> getSimpleAttributes() {
    return simpleAttributes;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public void setSimpleAttributes(Map<String, Object> simpleAttributes) {
    this.simpleAttributes = simpleAttributes;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(String.format("type: %s\n", type));
    str.append(String.format("key: %s\n", key));
    str.append(String.format("identifier: %s\n", identifier));
    str.append("attributes: {\n");
    for (String key : simpleAttributes.keySet()) {
      str.append(String.format("\t%s: %s\n", key, simpleAttributes.get(key)));
    }
    str.append("}\n");

    return str.toString();
  }
}
