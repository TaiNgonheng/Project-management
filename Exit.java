import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exit {
    void exit() throws FileNotFoundException, IOException{
                    System.out.flush();
                    try (FileReader reade = new FileReader("Exit.txt")) {
                        int dat = reade.read();
                        while(dat != -1){
                            System.out.print((char)dat);
                            dat = reade.read();
                        }
                    }
    }
}
