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
    private static List <String> lines;

    private static void viewContacts() {
        lines = Arrays.asList();

        try {
            for (int i = 0; i<Files.readAllLines(dataFile).size(); i++) {
            print.println(Files.readAllLines(dataFile).get(i));
            print.println("What would you like to do?");
            print.println("--------------------------");
            createMenu();
            }
        }
        catch (Exception e){
            print.println("Hmm, something went terribly wrong...Let's try that again!");
            createMenu();
        }
    }


    public static void createMenu() {
        print.println("1. View Contacts.");
        print.println("2. Add a new contact.");
        print.println("3. Search a contact by name.");
        print.println("4. Delete an existing contact.");
        print.println("5. Exit.");
        print.println("Enter an option (1, 2, 3, 4 or 5)");
        String input = scan.next();
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

    public static void addContact() {
        print.println("Type the name of the contact.");
        String input = scan.nextLine();
        print.println("Please input the phone number for this contact:");
        String phone = scan.next();
        lines = Arrays.asList("Name: " + input + " " + "Phone Number: " + phone);
        try {
           Files.write(dataFile, lines, StandardOpenOption.APPEND);

           print.println("Add another contact?");
           String input2 = scan.next();
            if (input2.equalsIgnoreCase("Y")){addContact();}
            else {
                scan.nextLine();
                createMenu();
            }
        }
        catch (Exception e) {
            print.println("Whoopsie Something went wrong...Try again!");
            addContact();
        }
    }

    public static void searchName() {
        int i = 0;
        print.println("Type the name of the contact to search.");
        String input = scan.next().toLowerCase();
        try {
            for(String line : Files.readAllLines(dataFile)) {
                if (line.toLowerCase().contains(input)) {
                    print.println(Files.readAllLines(dataFile).get(i));
                    print.println("Would you like to search for another contact?");
                    String choice = scan.next();
                    if (choice.equalsIgnoreCase("Y")) {
                        searchName();
                    } else {
                        createMenu();
                    }
                } else {i++;}
            }


        } catch (Exception e) {

        }

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

    }


}
