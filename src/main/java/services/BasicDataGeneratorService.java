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
        int randomInt = (int) (Math.random() * 100);
        return randomInt;
    }

    public String generateString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
