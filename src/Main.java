import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Versenyzo> listOfAll = Read("rovidprogram.csv");
        List<Versenyzo> listOfTop = Read("donto.csv");
        Feladatok(listOfAll, listOfTop);

        /* 
        for (Versenyzo versenyzo : listOfTop) {
            System.out.print(versenyzo);
        }
        */
    }

    private static void Feladatok(List<Versenyzo> listOfAll, List<Versenyzo> listOfTop)
    {
        System.out.printf("A rövidprogramba elindult versenyzők száma: %d", listOfAll.size());
    }

    private static List<Versenyzo> Read(String file){
        List<Versenyzo> list = new ArrayList<>();
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