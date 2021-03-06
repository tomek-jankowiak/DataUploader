package datauploader.logic;

import datauploader.data.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XMLDataReader extends DefaultHandler implements DataReader {
  private final String filepath;
  private final List<Element> elementList;
  private Element element;
  private Map<String, Object> productAttributes;
  private StringBuilder data;
  private boolean bIdentifier;
  private boolean bParameters;

  public XMLDataReader(String filepath) {
    this.filepath = filepath;
    this.elementList = new LinkedList<>();
    this.bIdentifier = false;
    this.bParameters = false;
  }

  @Override
  public List<Element> readFile() throws IOException {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(new File(filepath), this);
      return elementList;
    } catch (SAXException | ParserConfigurationException e) {
      throw new IOException();
    }
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName.equalsIgnoreCase("produkt")) {
      String type = attributes.getValue("typ");
      String key = attributes.getValue("klucz");
      element = new Element();
      element.setType(type);
      element.setKey(key);
    } else if (qName.equalsIgnoreCase("identyfikator")) {
      bIdentifier = true;
    } else if (qName.equalsIgnoreCase("parametry")) {
      productAttributes = new HashMap<>();
      bParameters = true;
    }
    data = new StringBuilder();
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if (qName.equalsIgnoreCase("produkt")) {
      element.setSimpleAttributes(productAttributes);
      elementList.add(element);
    } else if (bIdentifier) {
      element.setIdentifier(data.toString());
      bIdentifier = false;
    } else if (qName.equalsIgnoreCase("parametry")) {
      bParameters = false;
    } else if (bParameters) {
      productAttributes.put(qName, data.toString());
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    data.append(new String(ch, start, length));
  }
}
