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
public class Gladiaattori extends Hahmo {

    private int osumapisteet; // Gladiaattorin osumapisteet.
    private int puolustusArvo; // Gladiaattorin väistötodennäköisyysmuuttuja taistelussa.
    private int hyokkaysArvo; // Gladiaattorin osumistodennäköisyysmuuttuja taistelussa.
    private Ase ase; // Viite gladiaattorin aseeseen.
    private Keho keho; // Viite gladiaattorin kehoon, joka pitää kirjaa vammoista.
    private List<Hahmo> tapot; // Lista surmatuista hirviöistä, ei vielä toiminnallisuutta.

    /**
     * Konstruktori.
     *
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
        this.puolustusArvo = 10;
        this.hyokkaysArvo = 10;
        this.tapot = new ArrayList<Hahmo>();
    }

    /**
     * Gladiaattori suorittaa hyökkäyksen parametrina saamaansa hirviöön.
     *
     * @param h kohdehirviö
     * @param tilanne vuoron vuororaportti, johon hyökkäyksen tulos kirjataan
     */
    @Override
    public void hyokkaa(Hahmo h, Pelitilanne tilanne) {
        tilanne.lisaaTapahtuma(tilanne.viestit.lyo("Gladiaattori", "hirviötä"));
        if (h.puolusta(tilanne, this.ase.getVahinko(), hyokkaysArvo + new Random().nextInt(10))) {
            this.tapot.add(h);
        }
    }

    /**
     * Gladiaattori ottaa metodissa vastaan hyökkäyksen hirviöltä ja raportoi
     * sen tulokset vuororaporttiin.
     *
     * @param tilanne vuororaportti
     * @param vahinko vahingon määrä
     * @param osuma hirviön osumarolli
     */
    @Override
    public boolean puolusta(Pelitilanne tilanne, int vahinko, int osuma) {
        if (tilanne == null || vahinko < 0 || osuma < 0) {
            throw new IllegalArgumentException();
        }

        if (osuma > this.puolustusArvo) {
            this.osumapisteet -= vahinko;
            this.keho.otaVahinkoa(tilanne, vahinko);
            if (this.osumapisteet < 1) {
                tilanne.lisaaTapahtuma(tilanne.viestit.onKuollut("Gladiaattori"));
            }
            return true;
        } else {
            tilanne.lisaaTapahtuma(tilanne.viestit.vaistaa("Gladiaattori"));
            return false;
        }
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    public Keho getKeho() {
        return keho;
    }

    public List<Hahmo> getTapot() {
        return this.tapot;
    }

    public Efekti getHyokkaysEfekti(Koordinaatit k) {
        return this.ase.getHyokkaysefekti(k);
    }

    public void setAse(Ase ase) {
        this.ase = ase;
    }

    public int getHyokkaysarvo() {
        return this.hyokkaysArvo;
    }

    public int getPuolustusarvo() {
        return this.puolustusArvo;
    }

    public void muutaHyokkaysarvoa(int muutos) {
        if (this.hyokkaysArvo + muutos < 5) {
            muutaHyokkaysarvoa(muutos + 1);
        } else if (this.puolustusArvo - muutos < 5) {
            muutaHyokkaysarvoa(muutos - 1);
        } else {
            this.hyokkaysArvo += muutos;
            this.puolustusArvo -= muutos;
        }
    }
}
