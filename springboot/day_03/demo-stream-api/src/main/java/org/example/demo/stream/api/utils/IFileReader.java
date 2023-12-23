package org.example.demo.stream.api.utils;

import org.example.demo.stream.api.model.Person;

import java.util.List;

public interface IFileReader {
    List<Person> readFile(String filePath);
}
