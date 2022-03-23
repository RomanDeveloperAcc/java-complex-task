package services;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicDataGeneratorServiceTest {

    @Test
    public void generateInt() {
        // arrange
        BasicDataGeneratorService basicDataGenerator = new BasicDataGeneratorService();

        // act
        int testValue = basicDataGenerator.generateInt();

        // assert
        assertTrue(testValue <= 100 && testValue >= 0);
    }

    @Test
    public void generateString() {
        // arrange
        BasicDataGeneratorService basicDataGenerator = new BasicDataGeneratorService();

        // act
        String testValue = basicDataGenerator.generateString();

        // assert
        assertEquals(testValue.length(), 10);
    }

    @Test
    public void getCurrentDate() {
        // arrange
        BasicDataGeneratorService basicDataGenerator = new BasicDataGeneratorService();

        // act
        String testValue = basicDataGenerator.getCurrentDate();

        // assert
        assertNotNull(testValue);
    }
}