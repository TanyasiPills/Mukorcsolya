public class Versenyzo {
    String name;
    String orszag;
    float technika;
    float komponens;
    int vonas;

    public Versenyzo(String[] data) {
        this.name = data[0];
        this.orszag = data[1];
        this.technika = Float.parseFloat(data[2]);
        this.komponens = Float.parseFloat(data[3]);
        this.vonas = Integer.parseInt(data[4]);
    }

    public Float AllPoint(){
        return (technika + komponens) - vonas;
    }

    @Override
    public String toString() {
        return "Versenyzo{" +
                "name: " + name +
                ", orszag: " + orszag +
                ", technika: " + technika +
                ", komponens: " + komponens +
                ", vonas: " + vonas + '\'' +
                '}';
    }
}
