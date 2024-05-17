import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Menu
 */
public class Menu {
    private String ps;
    String user;
    Scanner scanner = new Scanner(System.in);
    public void password() throws IOException{
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        FileReader reader = new FileReader("pass.txt");
                int data = reader.read();
                while(data != -1){
                    System.out.print((char)data);
                    data = reader.read();
                }
                reader.close();
        if(ps == null){
            System.out.println("\n\t\t\t\t\tPlease Enter the new password!");
        }
        else{
            System.out.print("\n\t\t\t\t\tPlease Enter the password : ");
            user = scanner.nextLine();
            if(user.equals(ps)){
                menu();
            }else{
                password();
            }
        }
    }
    public void setPs(String ps) {
        this.ps = ps;
    }
    public void menu() throws IOException{
        while(true){
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            FileReader reader = new FileReader("men.txt");
                    int data = reader.read();
                    while(data != -1){
                        System.out.print((char)data);
                        data = reader.read();
                    }
                    reader.close();
            System.out.println("\n\n\t\t\t\t\t =================== Please Choise the Options =================\n");
            System.out.println("\t\t\t\t\t\t\t    1 . Input Soft Drink\n");// marina
            System.out.println("\t\t\t\t\t\t\t    2 . Display Soft Drink\n"); // liny
            System.out.println("\t\t\t\t\t\t\t    3 . Remove Soft Drink\n");// heng
            System.out.println("\t\t\t\t\t\t\t    4 . Search Soft Drink\n");// one
            System.out.println("\t\t\t\t\t\t\t    5 . Update Soft Drink in list\n");// tha
            System.out.println("\t\t\t\t\t\t\t    6 . Exit Program\n");// tha
            System.out.println("\t\t\t\t\t ================================================================\n");
            int op;
            System.out.print("\n\n\t\t\t\t\t\t\t Enter the Options : ");
            op = scanner.nextInt();
            switch (op) {
                case 1 ->{
                    read r = new read();
                    r.input();
                    break;
                }
                case 2 ->{
                    Output output1 = new Output();
                    output1.display();
                    break;
                }
                case 3 ->{
                    Delete delete1 = new Delete();
                    delete1.deleteRecord();
                    break;
                }
                case 4 ->{
                    Search search = new Search();
                    search.searchRecord();
                    break;
                }
                case 5 ->{
                    Update update = new Update();
                    update.update();
                    break;
                }
                case 6 ->{
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    E e = new E();
                    e.disE();
                    System.exit(0);
                }
            }
        }
    }
}