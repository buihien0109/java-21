package vn.techmaster.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import vn.techmaster.demo.model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class JsonFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file using java.nio.file.Path
            Path jsonFilePath = Paths.get(filePath);
            byte[] jsonData = Files.readAllBytes(jsonFilePath);

            // Convert JSON to List of Post objects
            return objectMapper.readValue(jsonData, new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
