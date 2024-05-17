import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class read{
    public void input() throws FileNotFoundException, IOException {
        String nameSD;
        String idSD;
        String priceSD;
        String delay;
        String qty;
        Scanner scanner = new Scanner(System.in);
        String csvFileName = "data.csv"; // Name of the CSV file
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        try (FileReader reade = new FileReader("input.txt")) {
            int dat = reade.read();
            while(dat != -1){
                System.out.print((char)dat);
                dat = reade.read();
            }
        }
        System.out.print("\n\n\n\t\t========================================= INPUT THE SOFT DRINK ==========================================\n");
        System.out.print("\n\t\t\t\t\t* Enter the id: ");
        idSD = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t* Enter the name : ");
        nameSD = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t* Enter the qautity : ");
        qty = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t* Enter the Price : ");
        priceSD = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t* Enter the Delay : ");
        delay = scanner.nextLine();// New data to add
        System.out.println("\n\t\t=========================================================================================================");
            String newData = idSD+","+nameSD+","+qty+","+priceSD+","+delay;

        try {
            // Read existing data from CSV file
            List<String> existingData = readCSV(csvFileName);

            // Add new data to the list
            existingData.add(newData);

            // Write the combined data back to the CSV file
            writeCSV(csvFileName, existingData);

            System.out.print("\n\t\t\t\t\t\t\tNew data added successfully.");
            System.out.print("\n\t\t\t\t\t\t\tEnter Number 1 go to menu: ");
            int i = scanner.nextInt();
            if(i == 1){
                Menu menu1 = new Menu();
                menu1.menu();
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error reading or writing CSV file: " + e.getMessage());
        }
    }

    // Method to read data from CSV file
    private static List<String> readCSV(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();
        return lines;
    }

    // Method to write data to CSV file
    private static void writeCSV(String fileName, List<String> lines) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        for (String line : lines) {
            writer.println(line); 
        }

        writer.close();
    }
}
