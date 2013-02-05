
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Vuororaportti;

/**
 * Pelin vastustajia kuvaava luokka, joka kokoaa yhteen vahingoittumis-, 
 * sijainti- ja hyökkäysmekanismit. Perii Liikutettava-luokalta 
 * sijaintiominaisuutensa ja sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Hirvio extends Liikutettava {
    private int osumaPisteet;
    private Keho keho;

    public Hirvio(int sijaintiX, int sijaintiY, int osumaPisteet) {
        super(sijaintiX, sijaintiY);
        this.osumaPisteet = osumaPisteet;
        this.keho = new Keho(osumaPisteet);
    }
    
    public void otaVahinkoa(Vuororaportti v, int vahinko) {
        this.osumaPisteet -= vahinko;
        this.keho.otaVahinkoa(v, vahinko);
    }
    
    

    public int getOsumaPisteet() {
        return osumaPisteet;
    }

    public void hyokkaa(Gladiaattori hahmo, Vuororaportti v) {
        hahmo.otaVahinkoa(1);
        v.lisaaTapahtuma("Hirviö lyö pelaajaa, 1 vahinko");
    }
    
    
}
