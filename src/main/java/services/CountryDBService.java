package services;

import interfaces.DBService;
import interfaces.DateService;
import models.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CountryDBService implements DBService<Country> {
    public static final String CONNECTION_STRING = "jdbc:postgresql:testdb";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASSWORD = "password";

    public static final String TABLE_NAME = "test";

    public static final String ID_COLUMN = "_id";
    public static final String COUNTRY_COLUMN = "country";
    public static final String POPULATION_COLUMN = "population";
    public static final String CAPITAL_COLUMN = "capital";
    public static final String BIGGEST_STREET_COLUMN = "biggestStreet";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final DateService dateGenerator = new BasicDataGeneratorService();

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, DB_LOGIN, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists " + TABLE_NAME + " (" +
                    ID_COLUMN + " integer, " +
                    COUNTRY_COLUMN + " text, " +
                    POPULATION_COLUMN + " integer, " +
                    CAPITAL_COLUMN + " text, " +
                    BIGGEST_STREET_COLUMN + " text)"
            );
            logger.info("Table '" + TABLE_NAME + "' exists!");
        } catch (SQLException e) {
            logger.error("Something went wrong. " + e.getMessage());
        }
    }

    public void readTable() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, DB_LOGIN, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from " + TABLE_NAME)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(COUNTRY_COLUMN) + " " + resultSet.getInt(POPULATION_COLUMN));
            }
            logger.info("Data was successfully read from table '" + TABLE_NAME + "'!");
        } catch (SQLException e) {
            logger.error("Something went wrong. " + e.getMessage());
        }
    }

    public void insertIntoTable(Country country) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, DB_LOGIN, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("insert into " + TABLE_NAME + " values (" + country.id + ",'" + country.country + "'," + country.population + ",'" + country.capital + "','" + country.biggestStreet + "')");
            logger.info("Data was successfully inserted into table '" + TABLE_NAME + "'!");
        } catch (SQLException e) {
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

    public void deleteTable() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, DB_LOGIN, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("drop table if exists " + TABLE_NAME);
            logger.info("'" + TABLE_NAME + "' table was successfully deleted!");
        } catch (SQLException e) {
            logger.error( "Something went wrong. " + e.getMessage());
        }
    }
}
