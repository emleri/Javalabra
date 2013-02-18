package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Pelaajahahmoa kuvaava luokka, joka kokoaa yhteen vahingoittumis-, sijainti-
 * ja hyökkäysmekanismit. Perii Liikutettava-luokalta sijaintiominaisuutensa ja
 * sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Gladiaattori extends Liikutettava {

    private int osumapisteet; // Gladiaattorin osumapisteet.
    private int puolustusArvo; // Gladiaattorin väistötodennäköisyysmuuttuja taistelussa.
    private int hyokkaysArvo; // Gladiaattorin osumistodennäköisyysmuuttuja taistelussa.
    private Ase ase; // Viite gladiaattorin aseeseen.
    private Keho keho; // Viite gladiaattorin kehoon, joka pitää kirjaa vammoista.
    private List<Hirvio> tapot; // Lista surmatuista hirviöistä, ei vielä toiminnallisuutta.

    /**
     * Konstruktori.
     * @param sijaintiX sijainti areenalla x-akselilla
     * @param sijaintiY sijainti areenalla y-akselilla
     * @param areenanLeveys areenan leveys
     * @param areenanKorkeus areenan korkeus
     * @param keho gladiaattorin keho
     */
    public Gladiaattori(Koordinaatit k, Keho keho) {
        super(k);
        this.osumapisteet = 100;
        this.keho = keho;
        this.puolustusArvo = 11;
        this.hyokkaysArvo = 10;
        this.tapot = new ArrayList<Hirvio>();
    }

    /**
     * Gladiaattori suorittaa hyökkäyksen parametrina saamaansa hirviöön.
     * @param h kohdehirviö
     * @param tilanne vuoron vuororaportti, johon hyökkäyksen tulos kirjataan
     */
    public void hyokkaa(Hirvio h, Pelitilanne tilanne) {
        tilanne.lisaaTapahtuma(tilanne.viestit.lyo("Gladiaattori", "hirviötä"));
        if (h.puolusta(tilanne, this.ase.getVahinko(), hyokkaysArvo + new Random().nextInt(10))) {
            this.tapot.add(h);
        }
    }

    /**
     * Gladiaattori ottaa metodissa vastaan hyökkäyksen hirviöltä ja raportoi 
     * sen tulokset vuororaporttiin.
     * @param v vuororaportti
     * @param vahinko vahingon määrä
     * @param osuma hirviön osumarolli
     */
    public void puolusta(Pelitilanne v, int vahinko, int osuma) {
        if (v == null || vahinko < 0 || osuma < 0) {
            throw new IllegalArgumentException();
        }

        if (osuma > this.puolustusArvo) {
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

    public List<Hirvio> getTapot() {
        return this.tapot;
    }

    public Efekti getHyokkaysEfekti(Koordinaatit k) {
        return this.ase.getHyokkaysefekti(k);
    }

    public void setAse(Ase ase) {
        this.ase = ase;
    }
}
