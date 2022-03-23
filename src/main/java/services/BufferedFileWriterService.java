package services;

import interfaces.DataGeneratorService;
import interfaces.FileWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BufferedFileWriterService<T> implements FileWriterService<T> {
    private final String fileName;
    private final DataGeneratorService dataGenerator;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public BufferedFileWriterService() {
        this.fileName = "test.txt";
        this.dataGenerator = new BasicDataGeneratorService();
    }

    public BufferedFileWriterService(String fileName) {
        this.fileName = fileName;
        this.dataGenerator = new BasicDataGeneratorService();
    }

    public BufferedFileWriterService(String fileName, DataGeneratorService dataGenerator) {
        this.fileName = fileName;
        this.dataGenerator = dataGenerator;
    }

    public void writeData(T dataObject) {
        logger.info("Writing data...");
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(this.fileName))) {

            ObjectSerializer<T> objectSerializer = new ObjectSerializer<>();
            List<Object> dataObjectValues = objectSerializer.serialize(dataObject);

            // Even integer values are saved as Strings so leaving that logic as it is
            for (Object value : dataObjectValues) {
                if (value instanceof Integer) {
                    fileWriter.write(String.valueOf((Integer) value));
                } else {
                    fileWriter.write((String) value);
                }
                fileWriter.write(",");
            }
            fileWriter.write("\n");
            logger.info("Data was written successfully!");
        } catch (Exception e) {
            logger.error("Error occurred while writing data.", e);
        }
    }
}
