package datauploader.data;

import datauploader.logic.CSVDataReader;
import datauploader.logic.DataReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import datauploader.logic.XMLDataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ElementStorage {
  private final String inputPath;
  private final List<Element> elementList;

  public ElementStorage(String inputPath) {
    this.inputPath = inputPath;
    this.elementList = new LinkedList<>();
  }

  public void loadProducts() throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath))) {
      String filepath;
      DataReader fileReader;

      while ((filepath = bufferedReader.readLine()) != null) {
        switch (filepath.split("\\.")[1].toLowerCase()) {
          case "csv" -> fileReader = new CSVDataReader(filepath);
          case "xml" -> fileReader = new XMLDataReader(filepath);
          default -> {
            fileReader = null;
            System.out.println("Unsupported file format!\n" + String.format("File omitted (%s)", filepath));
          }
        }

        if (fileReader != null) {
          elementList.addAll(fileReader.readFile());
        }
      }
    }
  }

  public void writeToJSON(String destination) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.writeValue(new File(destination), elementList);
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Element element : elementList) {
      stringBuilder.append(element.toString());
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
}
