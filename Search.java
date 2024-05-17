import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Search {
    public void searchRecord() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String csvFileName = "data.csv"; // Name of the CSV file
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        try (FileReader reade = new FileReader("search.txt")) {
            int dat = reade.read();
            while(dat != -1){
                System.out.print((char)dat);
                dat = reade.read();
            }
        }
        System.out.print("\n\t\t\t===================================================================================================\n\n ");
        System.out.print("\n\t\t\t\t\t\tEnter the ID or Name to search: ");
        String searchTerm = scanner.nextLine();
        
        try {
            File inputFile = new File(csvFileName);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;

            boolean found = false;
            while ((currentLine = reader.readLine()) != null) {
                // Split the line by comma (assuming CSV format)
                String[] data = currentLine.split(",");

                // Check if any field matches the search term
                for (String field : data) {
                    if (field.trim().equals(searchTerm)) {
                        found = true;
                        System.out.println("\n\t\t============================================== Soft Drink is found ========================================\n");
                        System.out.print("\t\tID\t\t\tName\t\t\tQuantity\t\tPrice\t\t\tDelay\n\n");
                        System.out.println("\t\t" + currentLine.trim().replace(",", "\t\t\t")); // Print the entire row
                        System.out.print("\n\n\t\t===========================================================================================================");
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            
            if (!found) {
                System.out.println("\n\t\t\t\t\t\t\tRecord not found.");
            }
            System.out.print("\n\t\t\t\t\t\t\tEnter Number 1 go to menu: ");
                int i = scanner.nextInt();
                if(i == 1){
                    Menu menu1 = new Menu();
                    menu1.menu();
                }
                scanner.close();
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}

