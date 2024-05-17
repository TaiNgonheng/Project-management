import java.io.*;
import java.util.Scanner;

public class Delete {
    public void deleteRecord() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String csvFileName = "data.csv"; // Name of the CSV file
        String tempFileName = "temp.csv"; // Temporary file to write updated data
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        try (FileReader reade = new FileReader("delete.txt")) {
            int dat = reade.read();
            while(dat != -1){
                System.out.print((char)dat);
                dat = reade.read();
            }
        }
        System.out.print("\n\t\t\t=============================================================================================\n");
        System.out.print("\n\t\t\t\t\t\t\t\t1. Remove by ID\n");
        System.out.print("\n\t\t\t\t\t\t\t\t2. Remove by Name\n\n");
        System.out.print("\t\t\t=============================================================================================\n");
        System.out.print("\n\t\t\t\t\t\t\t\tChoice the option : ");
        int op = scanner.nextInt();
        int idToDelete=0;
        String nameToDelete="";
        if(op == 1){
            System.out.print("\n\t\t\t\t\t\t\tEnter the id to delete: ");
            idToDelete = scanner.nextInt();
        }
        else if(op == 2){
            System.out.print("\n\t\t\t\t\t\t\tEnter the Name to delete: ");
            scanner.nextLine();
            nameToDelete = scanner.nextLine();
        }
        else{
            Menu menu = new Menu();
            menu.menu();
        }
        boolean found = false;
        try {
            File inputFile = new File(csvFileName);
            File tempFile = new File(tempFileName);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemoveid = String.valueOf(idToDelete);
            String linetoRemovename = String.valueOf(nameToDelete);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Split the line by comma (assuming CSV format)
                String[] data = currentLine.split(",");

                // Check if the ID matches the one to delete
                if (data.length > 0 && data[0].trim().equals(lineToRemoveid)) {
                    found = true;
                    continue; // Skip this line
                }
                if (data.length > 1 && data[1].trim().equals(linetoRemovename)) {
                    found = true;
                    continue; // Skip this line
                }
                writer.write(currentLine + System.getProperty("line.separator")); // Write the line to the temporary file
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.err.println("\n\t\t\t\t\t\t\tCould not delete the original file.");
                return;
            }

            if (found && !tempFile.renameTo(new File(csvFileName))) {
                System.err.println("\n\t\t\t\t\t\t\tCould not rename the temporary file.");
                return; // Exit method if renaming fails
            } else if (found) {
                System.out.println("\n\t\t\t\t\t\t\tRecord with ID " + idToDelete + " deleted successfully.");
                System.out.println("\t\t\t=============================================================================================\n");
                System.out.print("\n\t\t\t\t\t\t\tEnter Number 1 go to menu: ");
                int i = scanner.nextInt();
                if(i == 1){
                    Menu menu1 = new Menu();
                    menu1.menu();
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.err.println("Error modifying CSV file: " + e.getMessage());
        }
    }
}
