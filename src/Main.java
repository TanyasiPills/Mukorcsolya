import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Versenyzo> listOfAll;
    static List<Versenyzo> listOfTop;
    public static void main(String[] args) {
        listOfAll = Read("rovidprogram.csv");
        listOfTop = Read("donto.csv");
        Feladatok();

        /* 
        for (Versenyzo versenyzo : listOfTop) {
            System.out.print(versenyzo);
        }
        */
    }

    private static void Feladatok()
    {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.printf("A rövidprogramba elindult versenyzők száma: %d\n", listOfAll.size());
            System.out.println(listOfTop.stream().anyMatch(e -> "HUN".equals(e.orszag)) ? "A magyar versenyző bejutott a döntőbe\n" : "A magyar versenyző nem jutott be a döntőbe\n");
            float points = Osszpontszam(scanner.nextLine());
            if(points > 0f) System.out.println(String.valueOf(points));
            scanner.nextLine();
        }
    }

    private static float Osszpontszam(String name)
    {
        if(listOfAll.stream().anyMatch(e -> name.equals(e.name))){
            float value = listOfAll.stream().filter(e -> name.equals(e.name)).map(Versenyzo::AllPoint).findFirst().orElse(0f);
            value += listOfTop.stream().filter(e -> name.equals(e.name)).map(Versenyzo::AllPoint).findFirst().orElse(0f);
            return value;
        }
        else{
            System.out.println("Ilyen nevü versenyző nincs az adatbázisban");
            return 0f;
        }
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