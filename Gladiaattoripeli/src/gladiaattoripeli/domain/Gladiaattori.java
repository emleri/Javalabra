
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Vuororaportti;

/**
 * Pelaajahahmoa kuvaava luokka, joka kokoaa yhteen vahingoittumis-, 
 * sijainti- ja hyökkäysmekanismit. Perii Liikutettava-luokalta 
 * sijaintiominaisuutensa ja sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Gladiaattori extends Liikutettava {
    private int osumapisteet;
    
    public Gladiaattori(int sijaintiX, int sijaintiY, int areenanLeveys, int areenanKorkeus) {
        super(sijaintiX, sijaintiY);
        this.koordinaatit.setRajat(areenanLeveys, areenanKorkeus);
        this.osumapisteet = 15;
    }

    public void hyokkaa(Hirvio h, Vuororaportti v) {
        h.otaVahinkoa(v, 5);
    }

    public void otaVahinkoa(int i) {
        this.osumapisteet -= i;
    }
    
    public int getOsumapisteet() {
        return osumapisteet;
    }
}
