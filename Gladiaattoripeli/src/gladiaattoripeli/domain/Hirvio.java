
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Vuororaportti;

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

    public void hyokkaa(Gladiaattori hahmo, Vuororaportti v) {
        hahmo.otaVahinkoa(1);
        v.lisaaTapahtuma("Hirviö lyö pelaajaa, 1 vahinko");
    }
    
    
}
