
package gladiaattoripeli.domain;

public class Hirvio extends Liikutettava {
    private int osumaPisteet;

    public Hirvio(int sijaintiX, int sijaintiY, int osumaPisteet) {
        super(sijaintiX, sijaintiY);
        this.osumaPisteet = osumaPisteet;
    }
    
    public void otaVahinkoa(int vahinko) {
        this.osumaPisteet -= vahinko;
    }

    public int getOsumaPisteet() {
        return osumaPisteet;
    }

    public void hyokkaa(Gladiaattori hahmo) {
        hahmo.otaVahinkoa(1);
    }
    
    
}
