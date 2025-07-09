package utils; // Declares the package name as 'utils', grouping utility-related classes

import java.io.FileInputStream; // Imports class to read data from files as input streams
import java.io.IOException;     // Imports exception handling for input/output operations
import java.util.Properties;    // Imports the Properties class to work with key-value config data

// This class is used to read configuration values from a .properties file
public class ConfigReader {

    // Static variable to hold the loaded properties so we don't reload the file every time
    private static Properties prop;

    // Method to load and return all properties from the config file
    public static Properties getProperties() {
        // Load properties only if not already loaded (singleton pattern)
        if (prop == null) {
            prop = new Properties(); // Create a new Properties object

            try {
                // Load the config.properties file from the specified path
                FileInputStream ip = new FileInputStream("config.properties");

                // Load key-value pairs from the file into the Properties object
                prop.load(ip);
            } catch (IOException e) {
                // Print the error details if file not found or unreadable
                e.printStackTrace();

                // Stop execution if the file can't be loaded
                throw new RuntimeException("❌ Could not load config.properties");
            }
        }
        // Return the loaded Properties object
        return prop;
    }

    // Method to retrieve a specific property's value by key
    public static String getProperty(String key) {
        return getProperties().getProperty(key); // Fetch the value for the given key
    }
}
