package services;

import interfaces.DBService;
import interfaces.FileReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BufferedFileReaderService implements FileReaderService {
    private final String fileName;
    private final DBService dbService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BufferedFileReaderService() {
        this.fileName = "test.txt";
        this.dbService = new CountryDBService();
    }

    public BufferedFileReaderService(String fileName) {
        this.fileName = fileName;
        this.dbService = new CountryDBService();
    }

    public BufferedFileReaderService(String fileName, DBService dbService) {
        this.fileName = fileName;
        this.dbService = dbService;
    }

    public ArrayList<String> readData() {
        logger.info("Reading data...");
        ArrayList<String> dataRows = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                dataRows.add(line);
            }
            logger.info("Data was successfully read!");
        } catch (Exception e) {
            logger.error("Error while reading data.", e);
        }

        return dataRows;
    }
}
