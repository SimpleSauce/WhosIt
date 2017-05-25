package File_IO;
import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class ContactManager {

    Scanner scan = new Scanner(System.in);

    public static void createMenu (){
        System.out.println("1. View Contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5)");
    }

    public static void main(String[] args) {
        String directory = "data";
        String filename = "contacts.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);

        }

        if (Files.notExists(dataFile)) {
            Files.createFile(dataFile);
        }

    }






}
