
package gladiaattoripeli.domain;

public class Gladiaattori extends Liikutettava {
    private int osumapisteet;
    
    public Gladiaattori(int sijaintiX, int sijaintiY, int areenanLeveys, int areenanKorkeus) {
        super(sijaintiX, sijaintiY);
        this.koordinaatit.setRajat(areenanLeveys, areenanKorkeus);
        this.osumapisteet = 15;
    }

    public void hyokkaa(Hirvio h) {
        h.otaVahinkoa(5);
    }

    public void otaVahinkoa(int i) {
        this.osumapisteet -= i;
    }
    
    public int getOsumapisteet() {
        return osumapisteet;
    }
}
