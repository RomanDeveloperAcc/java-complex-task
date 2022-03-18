package services;

import interfaces.DBService;
import interfaces.DateService;
import models.Country;

import java.sql.*;

/**
 * Things to do:
 * 1. create single interface for crud operations
 * 2. read about jdbc statements
 */
public class SQLiteDBService implements DBService<Country> {
    public static final String DB_NAME = "test.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/rdany/Workspace/java-educational-task/complex-task/" + DB_NAME;

    public static final String TABLE_NAME = "test";

    public static final String ID_COLUMN = "_id";
    public static final String COUNTRY_COLUMN = "country";
    public static final String POPULATION_COLUMN = "population";
    public static final String CAPITAL_COLUMN = "capital";
    public static final String BIGGEST_STREET_COLUMN = "biggestStreet";

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final DateService dateGenerator = new BasicDataGeneratorService();

    public void createTable() {
        System.out.println(ANSI_BLUE + dateGenerator.getCurrentDate() + " - Creating table..." + ANSI_RESET);
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists " + TABLE_NAME + " (" +
                    ID_COLUMN + " integer, " +
                    COUNTRY_COLUMN + " text, " +
                    POPULATION_COLUMN + " integer, " +
                    CAPITAL_COLUMN + " text, " +
                    BIGGEST_STREET_COLUMN + " text)"
            );

            System.out.println(ANSI_GREEN + "Table exists!" + ANSI_RESET);
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Something went wrong. " + e.getMessage() + ANSI_RESET);
        }
    }

    public void readTable() {
        System.out.println(ANSI_BLUE + "Reading data from table..." + ANSI_RESET);
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from " + TABLE_NAME)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(COUNTRY_COLUMN) + " " + resultSet.getInt(POPULATION_COLUMN));
            }

            System.out.println(ANSI_GREEN + "Data was successfully read from the table!" + ANSI_RESET);
        } catch (SQLException e) {
            System.out.println(ANSI_RED +"Something went wrong. " + e.getMessage() + ANSI_RESET);
        }
    }

    public void insertIntoTable(Country country) {
        System.out.println(ANSI_BLUE + "Inserting data into table..." + ANSI_RESET);
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()) {
            statement.execute("insert into " + TABLE_NAME + " values (" + country.id + ",'" + country.country + "'," + country.population + ",'" + country.capital + "','" + country.biggestStreet + "')");
            System.out.println(ANSI_GREEN + "Data was successfully inserted into table!" + ANSI_RESET);
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Something went wrong. " + e.getMessage() + ANSI_RESET);
        }
    }

    public void deleteTable() {
        System.out.println(ANSI_BLUE + "Deleting table..." + ANSI_RESET);
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()) {
            statement.execute("drop table if exists " + TABLE_NAME);
            System.out.println(ANSI_GREEN + "Table was successfully deleted!" + ANSI_RESET);
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Something went wrong. " + e.getMessage() + ANSI_RESET);
        }
    }
}
