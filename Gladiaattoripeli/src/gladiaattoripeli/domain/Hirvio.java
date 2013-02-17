package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Pelitilanne;
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

    /**
     * Konstruktori. Osa toteutuksesta siirtyy pian hahmogeneraattorin vastuulle,
     * JavaD päivitetään sitten.
     * @param sijaintiX
     * @param sijaintiY
     * @param osumaPisteet
     */
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
    
    /**
     * Hirviö ottaa metodissa vastaan hyökkäyksen gladiaattorilta ja raportoi 
     * sen tulokset vuororaporttiin.
     * @param v vuororaportti
     * @param vahinko vahingon määrä
     * @param osuma gladiaattorin osumarolli
     */
    public boolean puolusta(Pelitilanne v, int vahinko, int osuma) {
        if (v == null || vahinko < 0 || osuma < 0) {
            throw new IllegalArgumentException();
        }

        if (osuma > this.puolustus) {
            this.osumapisteet -= vahinko;
            this.keho.otaVahinkoa(v, vahinko);
            if (this.osumapisteet < 1) {
                v.lisaaTapahtuma(v.viestit.onKuollut(this.nimi));
                return true;
            }
        } else {
            v.lisaaTapahtuma(v.viestit.vaistaa(this.nimi));
        }
        return false;
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    /**
     * Hirviö suorittaa hyökkäyksen parametrina saamaansa gladiaattoriin.
     *
     * @param hahmo gladiaattori
     * @param v vuoron vuororaportti, johon hyökkäyksen tulos kirjataan
     */
    public void hyokkaa(Gladiaattori hahmo, Pelitilanne v) {
        v.lisaaTapahtuma(v.viestit.lyo("Hirviö", "gladiaattoria"));
        hahmo.puolusta(v, 1, hyokkays + new Random().nextInt(10));
    }

    @Override
    public String toString() {
        return this.nimi + ", " + this.osumapisteet + " osumapistettä";
    }

    Efekti getHyokkaysefekti(Koordinaatit k) {
        Efekti e = new Efekti('*');
        e.lisaaPiirtokohta(k);
        
        return e;
    }
}
