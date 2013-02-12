package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Vuororaportti;
import java.util.Random;

/**
 * Pelaajahahmoa kuvaava luokka, joka kokoaa yhteen vahingoittumis-, sijainti-
 * ja hyökkäysmekanismit. Perii Liikutettava-luokalta sijaintiominaisuutensa ja
 * sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Gladiaattori extends Liikutettava {

    private int osumapisteet;
    private int puolustus;
    private int hyokkays;
    private Keho keho;

    /**
     * Konstruktori.
     * @param sijaintiX sijainti areenalla x-akselilla
     * @param sijaintiY sijainti areenalla y-akselilla
     * @param areenanLeveys areenan leveys
     * @param areenanKorkeus areenan korkeus
     * @param keho gladiaattorin keho
     */
    public Gladiaattori(int sijaintiX, int sijaintiY, int areenanLeveys, int areenanKorkeus, Keho keho) {
        super(sijaintiX, sijaintiY);
        this.koordinaatit.setRajat(areenanLeveys, areenanKorkeus);
        this.osumapisteet = 15;
        this.keho = keho;
        this.puolustus = 11;
        this.hyokkays = 7;
    }

    /**
     * Gladiaattori suorittaa hyökkäyksen parametrina saamaansa hirviöön.
     * @param h kohdehirviö
     * @param v vuoron vuororaportti, johon hyökkäyksen tulos kirjataan
     */
    public void hyokkaa(Hirvio h, Vuororaportti v) {
        v.lisaaTapahtuma(v.viestit.lyo("Gladiaattori", "hirviötä"));
        h.puolusta(v, 5, hyokkays + new Random().nextInt(10));
    }

    /**
     * Gladiaattori ottaa metodissa vastaan hyökkäyksen hirviöltä ja raportoi 
     * sen tulokset vuororaporttiin.
     * @param v vuororaportti
     * @param vahinko vahingon määrä
     * @param osuma hirviön osumarolli
     */
    public void puolusta(Vuororaportti v, int vahinko, int osuma) {
        if (v == null || vahinko < 0 || osuma < 0) {
            throw new IllegalArgumentException();
        }

        if (osuma > this.puolustus) {
            this.osumapisteet -= vahinko;
            this.keho.otaVahinkoa(v, vahinko);
            if (this.osumapisteet < 1) {
                v.lisaaTapahtuma(v.viestit.onKuollut("Gladiaattori"));
            }
        } else {
            v.lisaaTapahtuma(v.viestit.vaistaa("Gladiaattori"));
        }
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    public Keho getKeho() {
        return keho;
    }
}
