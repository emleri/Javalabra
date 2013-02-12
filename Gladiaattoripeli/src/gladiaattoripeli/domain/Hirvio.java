package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Vuororaportti;
import java.util.Random;

/**
 * Pelin vastustajia kuvaava luokka, joka kokoaa yhteen vahingoittumis-,
 * sijainti- ja hyökkäysmekanismit. Perii Liikutettava-luokalta
 * sijaintiominaisuutensa ja sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Hirvio extends Liikutettava {

    private int osumapisteet;
    private Keho keho;
    private String nimi;
    private int hyokkays;
    private int puolustus;

    public Hirvio(int sijaintiX, int sijaintiY, int osumaPisteet) {
        super(sijaintiX, sijaintiY);
        this.osumapisteet = osumaPisteet;
        this.hyokkays = 5;
        this.puolustus = 12;
        this.nimi = "Hirviö";
        
        // Siirrä hahmogeneraattoriin...
        this.keho = new Keho("Hirviö", osumaPisteet);
        this.keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAKASI, "Hirviö", osumaPisteet / 2));
        this.keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENKASI, "Hirviö", osumaPisteet / 2));
        this.keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, "Hirviö", osumaPisteet / 2));
        this.keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENJALKA, "Hirviö", osumaPisteet / 2));
    }

    public void puolusta(Vuororaportti v, int vahinko, int osuma) {
        if(v==null || vahinko < 0 || osuma < 0) {
            throw new IllegalArgumentException();
        }
        
        if (osuma > this.puolustus) {
            this.osumapisteet -= vahinko;
            this.keho.otaVahinkoa(v, vahinko);
            if (this.osumapisteet < 1) {
                v.lisaaTapahtuma(v.viestit.onKuollut(this.nimi));
            }
        } else {
            v.lisaaTapahtuma(v.viestit.vaistaa(this.nimi));
        }
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    public void hyokkaa(Gladiaattori hahmo, Vuororaportti v) {
        v.lisaaTapahtuma(v.viestit.lyo("Hirviö", "gladiaattoria"));
        hahmo.puolusta(v, 1, hyokkays + new Random().nextInt(10));
    }
}
