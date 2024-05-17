import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Update {
    public void update() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String csvFileName = "data.csv"; // Name of the CSV file
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        try (FileReader reade = new FileReader("update.txt")) {
            int dat = reade.read();
            while(dat != -1){
                System.out.print((char)dat);
                dat = reade.read();
            }
        }
        try {
            // Read existing data from CSV file
            List<String> existingData = readCSV(csvFileName);
            String idToUpdate = "";
            String nameToUpdate = "";

            System.out.println("\n\t\t============================================== EXISTING SOFT DRINKS ======================================");
            System.out.print("\t\tID\t\t\tName\t\t\tQuantity\t\tPrice\t\t\tDelay\n\n");
            for (String data : existingData) {
                System.out.println("\t\t" + data.trim().replace(",", "\t\t\t"));
            }
            System.out.println("\t\t==========================================================================================================");
            System.out.println("\n\t\t\t\t\t\t\t1. Update Soft Drink by ID");
            System.out.println("\n\t\t\t\t\t\t\t2. Update Soft Drink by Name");
            System.out.println("\n\t\t==========================================================================================================");
            System.out.print("\n\t\t\t\t\t\t\tEnter the Option : ");
            int op = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (op == 1) {
                // Prompt user to enter ID and name to search for record to update
                System.out.print("\n\t\t\t\t\t\t\tEnter the ID of the soft drink to update: ");
                idToUpdate = scanner.nextLine();
            } else if (op == 2) {
                System.out.print("\n\t\t\t\t\t\t\tEnter the name of the soft drink to update: ");
                nameToUpdate = scanner.nextLine();
            } else {
                Menu nMenu = new Menu();
                nMenu.menu();
            }
            int index = findRecordIndex(existingData, idToUpdate, nameToUpdate);
            if (index != -1) {
                // Prompt user for updated data
                System.out.print("\n\t\t\t* Enter the new id: ");
                String idSD = scanner.nextLine();
                System.out.print("\n\t\t\t* Enter the new name : ");
                String nameSD = scanner.nextLine();
                System.out.print("\n\t\t\t* Enter the new quantity : ");
                String qty = scanner.nextLine();
                System.out.print("\n\t\t\t* Enter the new price : ");
                String priceSD = scanner.nextLine();
                System.out.print("\n\t\t\t* Enter the new delay : ");
                String delay = scanner.nextLine();

                // Update the selected record
                String updatedData = idSD + "," + nameSD + "," + qty + "," + priceSD + "," + delay;
                existingData.set(index, updatedData);

                // Write the updated data back to the CSV file
                writeCSV(csvFileName, existingData);

                System.out.print("\n\t\t\t\t\t\t\tData updated successfully.");
            } else {
                System.out.println("\n\t\t\t\t\t\t\tSoft drink with ID '" + idToUpdate + "' and name '" + nameToUpdate + "' not found.");
            }

            System.out.print("\n\t\t\t\t\t\t\tEnter 1 to go back to menu: ");
            int i = scanner.nextInt();
            if (i == 1) {
                Menu menu1 = new Menu();
                menu1.menu();
            }
        } catch (IOException e) {
            System.err.println("Error reading or writing CSV file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private int findRecordIndex(List<String> existingData, String idToUpdate, String nameToUpdate) {
        for (int i = 0; i < existingData.size(); i++) {
            String[] fields = existingData.get(i).split(",");
            String id = fields[0].trim();
            String name = fields[1].trim();
            if (id.equalsIgnoreCase(idToUpdate.trim())) {
                return i;
            }else if(name.equalsIgnoreCase(nameToUpdate.trim())){
                return i;
            }
        }
        return -1; // Return -1 if record not found
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
