import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class E {
    public void disE() throws FileNotFoundException{
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        try (FileReader reader = new FileReader("Exit.txt")) {
            int data = reader.read();
            while(data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
