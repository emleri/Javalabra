package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.Random;

/**
 * Pelin vastustajia kuvaava luokka, joka kokoaa yhteen vahingoittumis-,
 * sijainti- ja hyökkäysmekanismit. Perii Liikutettava-luokalta
 * sijaintiominaisuutensa ja sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Hirvio extends Hahmo {

    private int osumapisteet;
    private Keho keho;
    private Ase ase;
    private String nimi;
    private int hyokkays;
    private int puolustus;

    /**
     * Konstruktori. Osa toteutuksesta siirtyy pian hahmogeneraattorin vastuulle,
     * JavaD päivitetään sitten.
     * @param sijaintiX
     * @param sijaintiY
     * @param osumapisteet
     */
    public Hirvio(int osumapisteet, Keho keho) {
        super(new Koordinaatit(0, 0));
        this.osumapisteet = osumapisteet;
        this.keho = keho;
        this.hyokkays = 7;
        this.puolustus = 13;
        this.nimi = "Hirviö";
    }

    public void setAse(Ase ase) {
        this.ase = ase;
    }
    
    /**
     * Hirviö ottaa metodissa vastaan hyökkäyksen gladiaattorilta ja raportoi 
     * sen tulokset vuororaporttiin.
     * @param tilanne vuororaportti
     * @param vahinko vahingon määrä
     * @param hyokkaysarvo gladiaattorin osumarolli
     */
    @Override
    public boolean puolusta(Pelitilanne tilanne, int vahinko, int hyokkaysarvo) {
        if (tilanne == null || vahinko < 0 || hyokkaysarvo < 0) {
            throw new IllegalArgumentException();
        }

        if (hyokkaysarvo > this.puolustus) {
            this.osumapisteet -= vahinko;
            this.keho.otaVahinkoa(tilanne, vahinko);
            if (this.osumapisteet < 1) {
                tilanne.lisaaTapahtuma(tilanne.viestit.onKuollut(this.nimi));
                return true;
            }
        } else {
            tilanne.lisaaTapahtuma(tilanne.viestit.vaistaa(this.nimi));
        }
        return false;
    }
    
    public void liiku(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
            if (this.koordinaatit.onVieressa(gladiaattori.getSijainti())) {
                areena.lisaaEfekti(this.getHyokkaysefekti(gladiaattori.getSijainti()));
                this.hyokkaa(gladiaattori, tilanne);
            } else {
                Koordinaatit kohderuutu = this.koordinaatit.getViereinenRuutuKohtiKoordinaatteja(gladiaattori.getSijainti());
                if (!areena.onkoRuudussaHirviota(kohderuutu) && !areena.onkoRuudussaEstetta(kohderuutu)) {
                    this.siirry(kohderuutu);
                } else {
                    while (true) {
                        kohderuutu = this.getSijainti().getSatunnainenRuutuVieressa();
                        if (!areena.onkoRuudussaHirviota(kohderuutu) && !areena.onkoRuudussaEstetta(kohderuutu)) {
                            this.siirry(kohderuutu);
                            break;
                        }
                    }
                }
            }
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    /**
     * Hirviö suorittaa hyökkäyksen parametrina saamaansa gladiaattoriin.
     *
     * @param hahmo gladiaattori
     * @param tilanne vuoron vuororaportti, johon hyökkäyksen tulos kirjataan
     */
    @Override
    public void hyokkaa(Hahmo hahmo, Pelitilanne tilanne) {
        tilanne.lisaaTapahtuma(tilanne.viestit.lyo("Hirviö", "gladiaattoria"));
        hahmo.puolusta(tilanne, this.ase.getVahinko(), hyokkays + new Random().nextInt(10));
    }

    @Override
    public String toString() {
        return this.nimi + ", " + this.osumapisteet + " osumapistettä";
    }

    public Efekti getHyokkaysefekti(Koordinaatit k) {
        return this.ase.getHyokkaysefekti(k);
    }

    protected Keho getKeho() {
        return this.keho;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public String getNimi() {
        return this.nimi;
    }
}
