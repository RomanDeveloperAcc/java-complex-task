package services;

import interfaces.DBService;
import interfaces.FileReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Things to change:
 * 1. filename - final
 * 2. would be good to create interface and class based on it
 * 3. more constructors with extended functionality (fileName, dbService) ...
 */
public class BufferedFileReader implements FileReaderService<String> {
    private final String fileName;
    private final DBService dbService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BufferedFileReader() {
        this.fileName = "test.txt";
        this.dbService = new CountryDBService();
    }

    public BufferedFileReader(String fileName) {
        this.fileName = fileName;
        this.dbService = new CountryDBService();
    }

    public BufferedFileReader(String fileName, DBService dbService) {
        this.fileName = fileName;
        this.dbService = dbService;
    }

    public ArrayList<String> readData() {
        logger.info("Reading data...");
        ArrayList<String> dataRows = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
//                String[] data = line.split(",");
//                int id = Integer.parseInt(data[0]);
//                String country = data[1];
//                int population = Integer.parseInt(data[2]);
//                String capital = data[3];
//                String biggestStreet = data[4];
                dataRows.add(line);


//                System.out.println(id + " : " + country + " : " + population + " : " + capital + " : " + biggestStreet);
//                dbService.insertIntoTable(id, country, population, capital, biggestStreet);
            }
            logger.info("Data was successfully read!");
        } catch (IOException e) {
            logger.error("Error while reading data.");
            e.printStackTrace();
        }

        return dataRows;
    }
}
