import interfaces.DBService;
import interfaces.DataGeneratorService;
import interfaces.FileReaderService;
import interfaces.FileWriterService;
import models.Country;
import services.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filename = "test.txt";

        FileWriterService<Country> bufferedFileWriter = new BufferedFileWriterService<>(filename);
        FileReaderService bufferedFileReader = new BufferedFileReaderService(filename);

        Country country = new Country();
        DataGeneratorService dataGeneratorService = new BasicDataGeneratorService();

        int targetStringLength = 10;
        int rows = 1;

        for (int i = 0; i < rows; i++) {
            country.id = dataGeneratorService.generateInt();
            country.country = dataGeneratorService.generateString(targetStringLength);
            country.population = dataGeneratorService.generateInt();
            country.capital = dataGeneratorService.generateString(targetStringLength);
            country.biggestStreet = dataGeneratorService.generateString(targetStringLength);

            bufferedFileWriter.writeData(country);
        }

        DBService<Country> dbService = new CountryDBService();

        dbService.deleteTable();
        dbService.createTable();

        ArrayList<String> data = bufferedFileReader.readData();
        CountryParserService countryParser = new CountryParserService();

        ArrayList<Country> countryList = countryParser.parse(data);

        for (Country listCountry : countryList) {
            dbService.insertIntoTable(listCountry);
        }

        dbService.readTable();
    }
}
