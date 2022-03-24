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
        int targetStringLength = 10;

        // act
        String testValue = basicDataGenerator.generateString(targetStringLength);

        // assert
        assertEquals(testValue.length(), targetStringLength);
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