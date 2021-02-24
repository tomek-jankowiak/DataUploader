package datauploader.logic;

import datauploader.data.Product;

import java.io.IOException;
import java.util.List;

public interface IFileReader {
  List<Product> readFile() throws IOException;
}
