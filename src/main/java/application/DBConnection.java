package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Opens the MySQL connection using settings from db.properties.
 *
 * db.properties is git-ignored. Copy db.properties.example to db.properties
 * (in the project root, or in src/main/resources) and fill in your values.
 * Environment variables DB_URL, DB_USER and DB_PASSWORD override the file.
 */
public class DBConnection {

    private static final String FILE_NAME = "db.properties";
    private static final Properties PROPS = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        Path external = Paths.get(FILE_NAME);
        try (InputStream in = Files.exists(external)
                ? new FileInputStream(external.toFile())
                : DBConnection.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (in != null) {
                props.load(in);
            } else {
                System.out.println("db.properties not found. Copy db.properties.example to db.properties.");
            }
        } catch (IOException e) {
            System.out.println("Could not read db.properties: " + e.getMessage());
        }
        return props;
    }

    private static String setting(String envName, String propName) {
        String env = System.getenv(envName);
        return (env != null && !env.isBlank()) ? env : PROPS.getProperty(propName);
    }

    public static Connection getConnection() {
        String url = setting("DB_URL", "db.url");
        String user = setting("DB_USER", "db.user");
        String password = setting("DB_PASSWORD", "db.password");
        if (url == null || user == null || password == null) {
            System.out.println("Database settings are missing. Check db.properties.");
            return null;
        }
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Database Connection Failed: " + e.getMessage());
            return null;
        }
    }
}
