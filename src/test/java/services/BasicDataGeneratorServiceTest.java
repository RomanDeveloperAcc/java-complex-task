package services;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicDataGeneratorServiceTest {

    @Test
    public void generateInt() {
        BasicDataGeneratorService basicDataGenerator = new BasicDataGeneratorService();
        int testValue = basicDataGenerator.generateInt();

        assertTrue(testValue <= 100 && testValue >= 0);
    }

    @Test
    public void generateString() {
        BasicDataGeneratorService basicDataGenerator = new BasicDataGeneratorService();
        String testValue = basicDataGenerator.generateString();

        assertEquals(testValue.length(), 10);
    }

    @Test
    public void getCurrentDate() {
        BasicDataGeneratorService basicDataGenerator = new BasicDataGeneratorService();
        String testValue = basicDataGenerator.getCurrentDate();

        assertNotNull(testValue);
    }
}