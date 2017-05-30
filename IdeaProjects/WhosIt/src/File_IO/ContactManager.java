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
//    private static FileReader reader = new FileReader(dataFile);

    private static void viewContacts() throws IOException {
        lines = Arrays.asList();

        try {
            for (int i = 0; i<Files.readAllLines(dataFile).size(); i++) {
                if(Files.readAllLines(dataFile).size() == 0) {
                    print.println("There are no contacts to display!");
                    print.println("What would you like to do?");
                    print.println("--------------------------");
                    createMenu();
                } else {
                    print.println(Files.readAllLines(dataFile).get(i));
                    print.println("What would you like to do?");
                    print.println("--------------------------");
                    createMenu();
                    print.println(Files.readAllLines(dataFile).size());
                }
            }
        }
        catch (Exception e){
            print.println("Hmm, something went terribly wrong...Let's try that again!");
            createMenu();
        }
    }


    public static void createMenu() throws IOException {
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
        print.println("Iput first name of the contact:");
        String fName = scan.next();
        print.println("Input Last Name:");
        String lName = scan.next();
        print.println("Please input the phone number for this contact:");
        String phone = scan.next();
        String phoneResult = phoneNumberParse(phone);
        lines = Arrays.asList("Name: " + fName + " " + lName + "| Phone Number: " + phoneResult);
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

    public static void deleteContact() throws IOException {
        int i = 0;
        print.println("Type the name of the contact that you want to delete.");
        String input = scan.next().toLowerCase();
        lines = Files.readAllLines(dataFile);
        try {
            for(String line : lines) {
                if (line.toLowerCase().contains(input)) {
                    lines.remove(line);
                    Files.write(dataFile, lines);
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
            print.println("Delete messed up! Let's try that again.");
            createMenu();
        }
    }

    public static String phoneNumberParse(String number) {
        String part1 = number.substring(0, 3);
        String part2 = number.substring(3, 6);
        String part3 = number.substring(6, 10);
        String result = (part1 + "-" + part2 + "-" + part3);
        return result;
    }

    public static void main(String[] args) throws IOException {

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
