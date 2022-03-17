import interfaces.DataGeneratorService;
import models.Country;
import services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Things to do:
 * 1. convert to maven
 * 2. use postgres instead of sqlite
 * 3. add logging
 * 4. add unit tests "junit, spoke, mockito"
 * 5. repeat SOLID
 * 6. repeat coupling/cohesion in java
 * */
public class Main {
    public static void main(String[] args) {
        String filename = "test.txt";

        BufferedFileWriter<Map<String, Object>> bufferedFileWriter = new BufferedFileWriter<>(filename);
        BufferedFileReader bufferedFileReader = new BufferedFileReader(filename);

//        Country country = new Country();
        Map<String, Object> countryData = new LinkedHashMap<>();
        DataGeneratorService dataGeneratorService = new BasicDataGenerator();
        int rows = 1;

        for (int i = 0; i < rows; i++) {
            countryData.put("id" , dataGeneratorService.generateInt());
            countryData.put("country" , dataGeneratorService.generateString());
            countryData.put("population" , dataGeneratorService.generateInt());
            countryData.put("capital" , dataGeneratorService.generateString());
            countryData.put("biggestStreet" , dataGeneratorService.generateString());
//            country.country = dataGeneratorService.generateString();
//            country.population = dataGeneratorService.generateInt();
//            country.capital = dataGeneratorService.generateString();
//            country.biggestStreet = dataGeneratorService.generateString();

            bufferedFileWriter.writeData(countryData);
        }

//        SQLiteDBService dbService = new SQLiteDBService();
        CountryDBService dbService = new CountryDBService();

        dbService.deleteTable();
        dbService.createTable();

        ArrayList<String> data = bufferedFileReader.readData();
        CountryParser countryParser = new CountryParser();

        ArrayList<Country> countryList = countryParser.parse(data);

        for (Country listCountry : countryList) {
            dbService.insertIntoTable(listCountry);
        }

        dbService.readTable();
    }
}
