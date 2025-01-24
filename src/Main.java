import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            System.out.printf("2.feladat\n\t A rövidprogramba elindult versenyzők száma: %d\n", listOfAll.size());
            
            System.out.println(listOfTop.stream().anyMatch(e -> "HUN".equals(e.orszag)) ? "3.feladat\n\tA magyar versenyző bejutott a döntőbe" : "3.feladat\n\tA magyar versenyző nem jutott be a döntőbe");
            
            System.out.println("5.feladat\n\tKérem a versenyző nevét:");

            float points = Osszpontszam(scanner.nextLine());
            if(points > 0f) System.out.println(String.valueOf(points)+"\n");

            Map<String, Long> countryCount = listOfTop.stream().collect(Collectors.groupingBy(v -> v.orszag,Collectors.counting()));
            System.out.println("7.feladat");
            countryCount.forEach((country, count) -> {
                if(count >= 2) System.out.println("\t"+country + ": " + count + " versenyző");
            });

            Write(listOfTop, "vegeredmeny.csv");
        }
    }

    private static float Osszpontszam(String name)
    {
        String nameLower = name.toLowerCase();
        if(listOfAll.stream().anyMatch(e -> nameLower.equals(e.name.toLowerCase()))){
            float value = listOfAll.stream().filter(e -> nameLower.equals(e.name.toLowerCase())).map(Versenyzo::AllPoint).findFirst().orElse(0f);
            value += listOfTop.stream().filter(e -> nameLower.equals(e.name.toLowerCase())).map(Versenyzo::AllPoint).findFirst().orElse(0f);
            return value;
        }
        else{
            System.out.println("Ilyen nevü versenyző nincs az adatbázisban\n");
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

    private static void Write(List<Versenyzo> list, String file)
    {
        list.sort((v1, v2) -> Float.compare(Osszpontszam(v2.name), Osszpontszam(v1.name)));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) 
        {
            bw.write("Helyezet;Név;Ország;Pontszám");
            bw.newLine();
            for (int i = 0; i < 10; i++) {
                Versenyzo versenyzo = list.get(i);
                String line = String.join(";",
                        String.valueOf(i+1),
                        versenyzo.name,
                        versenyzo.orszag,
                        String.valueOf(Osszpontszam(versenyzo.name))
                );
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}