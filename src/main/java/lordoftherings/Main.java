package lordoftherings;

import com.github.javafaker.Faker;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection;
        Faker fakeData = new Faker();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:myDB.db");

            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS lordOfTheRings(id INTEGER PRIMARY KEY, characterName STRING NOT NULL, location STRING NOT NULL)");
            statement.executeUpdate("CREATE INDEX IF NOT EXISTS characterDetails on lordOfTheRings (characterName, location)");
            String insertDetails = "INSERT INTO lordOfTheRings (characterName, location) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertDetails);

            int createRows = 0;
            while (createRows < 10) {

                String character = fakeData.lordOfTheRings().character();
                String location = fakeData.lordOfTheRings().location();

                preparedStatement.setString(1, character);
                preparedStatement.setString(2, location);

                preparedStatement.executeUpdate();
                createRows++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
