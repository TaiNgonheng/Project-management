import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Input {
    ArrayList<String> nameSD = new ArrayList<String>();
    ArrayList<Double> priceSD = new ArrayList<Double>();
    ArrayList<Integer> idSD = new ArrayList<Integer>();
    ArrayList<String> delay = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    public void inputitems() throws IOException{
        System.out.print("\n\t\t\t\t\t\t\tEnter the number of the Item: ");
        int size = scanner.nextInt();
        for(int i = 0; i < size; i++){
            System.out.println("\t\t\t\t===================================== INPUT THE SOFT DRINK "+i+1+" ============================================");
            System.out.print("\n\t\t\t\t\tEnter the ID of the soft drinks: ");
            int id = scanner.nextInt();
            idSD.add(id);
            System.out.print("\n\t\t\t\t\tEnter the Price of the soft drinks: ");
            double pr = scanner.nextFloat();
            priceSD.add(pr);
            scanner.nextLine();
            System.out.print("\n\t\t\t\t\tEnter the Name of the soft drinks: ");
            String name = scanner.nextLine();
            nameSD.add(name);
            System.out.print("\n\t\t\t\t\tEnter the Delay of the soft drinks: ");
            String del = scanner.nextLine();
            delay.add(del);
        }
        try (FileWriter filew = new FileWriter("File.txt")) {
            PrintWriter pfilew = new PrintWriter(filew);
            for(int i=0 ; i<size ; i++){
                pfilew.append("\n\t\t   "+idSD.get(i)+"\t\t "+nameSD.get(i)+"\t\t\t\t\t "+priceSD.get(i)+"\t\t\t\t "+delay.get(i)+"\n");
            }
        }
        int i = scanner.nextInt();
        if(i == 1){
            Menu menu = new Menu();
            menu.menu();
        }
        scanner.close();
    }
}
