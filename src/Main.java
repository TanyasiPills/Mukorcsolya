import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
    }


    public static List<Versenyzo> Read(String file){
        List<Versenyzo> list = new ArrayList<Versenyzo>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                data = Arrays.stream(data).map(e -> e.replace(",", ".")).toArray(String[]::new);
                list.add(new Versenyzo(data));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}