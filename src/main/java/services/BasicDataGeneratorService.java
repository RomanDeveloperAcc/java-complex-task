package services;

import interfaces.DataGeneratorService;
import interfaces.DateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Things to change:
 * 1. create interface and class based on it
 */
public class BasicDataGeneratorService implements DataGeneratorService, DateService {
    protected Logger log = LoggerFactory.getLogger(getClass());

    public int generateInt() {
//        System.out.println("Generating integer value...");
//        log.info("Generating int...");
        int randomInt = (int) (Math.random() * 100);
//        System.out.println("Integer value was successfully generated!");
//        log.info("Generating int...COMPLETE");
        return randomInt;
    }

    public String generateString() {
//        System.out.println("Generating string value...");
        log.info("Generating string...");
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) // rozberis
                .toString();

//        System.out.println("String value was successfully generated!");
        log.info("Generating string...COMPLETE");

        return generatedString;
    }

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
