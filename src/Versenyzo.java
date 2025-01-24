public class Versenyzo {
    String name;
    String orszag;
    Float technika;
    Float komponens;
    int vonas;

    public Versenyzo(String[] data) {
        this.name = data[0];
        this.orszag = data[1];
        this.technika = Float.valueOf(data[2]);
        this.komponens = Float.valueOf(data[3]);
        this.vonas = Integer.parseInt(data[4]);
    }

    @Override
    public String toString() {
        return "Fuvar{" +
                "name: " + name +
                ", orszag: " + orszag +
                ", technika: " + technika +
                ", komponens: " + komponens +
                ", vonas: " + vonas + '\'' +
                '}';
    }
}
