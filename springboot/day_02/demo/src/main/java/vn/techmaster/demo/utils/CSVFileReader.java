package vn.techmaster.demo.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vn.techmaster.demo.model.Book;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Primary
@Component
public class CSVFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return new CsvToBeanBuilder<Book>(reader)
                    .withType(Book.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}
