package File_IO;
import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class ContactManager {

    Scanner scan = new Scanner(System.in);



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
