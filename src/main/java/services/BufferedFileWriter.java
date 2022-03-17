package services;

import interfaces.DataGeneratorService;
import interfaces.FileWriterService;
import models.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Things to change:
 * 1. fileName - final
 * 2. more constructors with extended functionality (fileName, dataGeneratorService) ...
 * 3. separate generation logic from `writeData` method
 * 4. would be good to create interface and class based on it
 */
public class BufferedFileWriter<T extends Map<String, Object>> implements FileWriterService<T> {
    private final String fileName;
    private final DataGeneratorService dataGenerator;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public BufferedFileWriter() {
        this.fileName = "test.txt";
        dataGenerator = new BasicDataGenerator();
    }

    public BufferedFileWriter(String fileName) {
        this.fileName = fileName;
        this.dataGenerator = new BasicDataGenerator();
    }

    public BufferedFileWriter(String fileName, BasicDataGenerator dataGenerator) {
        this.fileName = fileName;
        this.dataGenerator = dataGenerator;
    }

    public void writeData(T dataObject) {
        logger.info("Writing data...");
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.fileName))) {

            // WIP
//            Country testCountry = new Country();
//
//            Field[] fields = testCountry.getClass().getDeclaredFields();
//
//            for (Field field : fields) {
//                System.out.println(field.getName());
//            }
            for (Object value : dataObject.values()) {
                if (value instanceof Integer) {
                    fileWriter.write(String.valueOf((Integer) value));
                } else {
                    fileWriter.write((String) value);
                }
                fileWriter.write(",");
            }
            fileWriter.write("\n");
//            fileWriter.write(country.id + "," + country.country + "," + country.population + "," + country.capital + "," + country.biggestStreet + "\n");
            logger.info("Data was written successfully!");
        } catch (IOException e) {
            logger.error("Error while writing data.");
            e.printStackTrace();
        }
    }
}
