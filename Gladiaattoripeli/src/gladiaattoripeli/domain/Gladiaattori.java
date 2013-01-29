
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Vuororaportti;

public class Gladiaattori extends Liikutettava {
    private int osumapisteet;
    
    public Gladiaattori(int sijaintiX, int sijaintiY, int areenanLeveys, int areenanKorkeus) {
        super(sijaintiX, sijaintiY);
        this.koordinaatit.setRajat(areenanLeveys, areenanKorkeus);
        this.osumapisteet = 15;
    }

    public void hyokkaa(Hirvio h, Vuororaportti v) {
        h.otaVahinkoa(5);
        v.lisaaTapahtuma("Gladiaattori lyö hirviötä, 5 vahinkoa");
    }

    public void otaVahinkoa(int i) {
        this.osumapisteet -= i;
    }
    
    public int getOsumapisteet() {
        return osumapisteet;
    }
}
