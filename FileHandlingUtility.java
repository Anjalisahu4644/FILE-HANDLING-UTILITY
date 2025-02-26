import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileHandlingUtility {
    private static final String FILE_PATH = "example.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // Display menu options
            System.out.println("Choose an operation:");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter text to write to file:");
                    String text = scanner.nextLine();
                    writeToFile(text);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    System.out.println("Enter text to replace:");
                    String oldText = scanner.nextLine();
                    System.out.println("Enter new text:");
                    String newText = scanner.nextLine();
                    modifyFile(oldText, newText);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to write text to the file
    public static void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(content + "\n"); // Append new text to the file
            System.out.println("Text written to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read and display the file content
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print each line of the file
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to modify existing text in the file
    public static void modifyFile(String oldText, String newText) {
        try {
            Path path = Paths.get(FILE_PATH);
            String content = new String(Files.readAllBytes(path)); // Read file content
            content = content.replace(oldText, newText); // Replace old text with new text
            Files.write(path, content.getBytes()); // Write updated content back to the file
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
