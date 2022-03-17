package services;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicDataGeneratorTest {

    @Test
    public void generateInt() {
        BasicDataGenerator basicDataGenerator = new BasicDataGenerator();
        int testValue = basicDataGenerator.generateInt();

        assertTrue(testValue <= 100 && testValue >= 0);
    }

    @Test
    public void generateString() {
        BasicDataGenerator basicDataGenerator = new BasicDataGenerator();
        String testValue = basicDataGenerator.generateString();

        assertEquals(testValue.length(), 10);
    }

    @Test
    public void getCurrentDate() {
        BasicDataGenerator basicDataGenerator = new BasicDataGenerator();
        String testValue = basicDataGenerator.getCurrentDate();

        assertNotNull(testValue);
    }
}