package services;

import interfaces.TextParserService;
import models.Country;

import java.util.ArrayList;

public class CountryParserService implements TextParserService<Country> {
    public ArrayList<Country> parse(ArrayList<String> list) {
        ArrayList<Country> countryList = new ArrayList<>();

        for (String dataRow : list) {
            Country country = new Country();
            String[] dataElements = dataRow.split(",");
            country.id = Integer.parseInt(dataElements[0]);
            country.country = dataElements[1];
            country.population = Integer.parseInt(dataElements[2]);
            country.capital = dataElements[3];
            country.biggestStreet = dataElements[4];

            countryList.add(country);
        }

        return countryList;
    }
}
