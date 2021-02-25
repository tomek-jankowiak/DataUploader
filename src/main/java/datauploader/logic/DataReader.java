package datauploader.logic;

import datauploader.data.Element;

import java.io.IOException;
import java.util.List;

public interface DataReader {
  List<Element> readFile() throws IOException;
}
