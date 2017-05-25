package File_IO;

import java.nio.file.*;
import java.util.*;
import java.io.*;

public class ContactManager {

    private static Scanner scan = new Scanner(System.in);
    private static PrintStream print = System.out;
    private static String directory = "data";
    private static String filename = "contacts.txt";
    private static Path dataDirectory = Paths.get(directory);
    private static Path dataFile = Paths.get(directory, filename);
    private static String input = scan.nextLine();

    private static void viewContacts() {

    }

    public static void createMenu() {
        print.println("1. View Contacts.");
        print.println("2. Add a new contact.");
        print.println("3. Search a contact by name.");
        print.println("4. Delete an existing contact.");
        print.println("5. Exit.");
        print.println("Enter an option (1, 2, 3, 4 or 5)");
    }

    public static void addContact() {
        print.println("Type the name of the contact.");
        try {
            Path
        } catch (Exception e) {
            print.println("Whoopsie Something went wrong...Try again!");
            addContact();
        }

    }

    public static void searchName() {
        print.println("Type the name of the contact to search.");
    }

    public static void deleteContact() {
        print.println("Type the name of the contact that you want to delete.");
    }

    public static void main(String[] args) {


        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
        } catch (Exception e) {
            print.println("Something went wrong.");
        }

        try {
            if (Files.notExists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (Exception e) {
            print.println("Something went wrong.");
        }

        createMenu();

        switch(input) {
            case "1": {
                viewContacts();
            } break;
            case "2": {
                addContact();
            } break;
            case "3": {
                searchName();
            } break;
            case "4": {
                deleteContact();
            } break;
            default: {System.exit(0);}
        }

    }


}
