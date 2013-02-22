package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;
import java.util.Random;

/**
 * Abstrakti luokka, joka kuvaa kaikkia hahmoja, joita pelissä käytetään.
 * Sisältää kaikkien hahmojen jakamat yleiset liike- ja taisteluominaisuudet.
 */
public abstract class Hahmo {

    private String nimi; // Hahmon nimi
    private Koordinaatit koordinaatit; // Hahmon sijainti
    private Keho keho; // Hahmon vartalo
    private Ase ase; // Hahmon ase
    private int osumapisteet; // Hahmon osumapisteet
    private int hyokkaysarvo; // Hahmon hyökkäysarvo
    private int puolustusarvo; // Hahmon puolustusarvo
    private Random arpoja; // Satunnaislukugeneraattori

    /**
     * Hahmon konstruktori.
     *
     * @param nimi hahmon nimi
     * @param k hahmon sijainti koordinaatteina
     * @param keho hahmolle annettava keho
     * @param ase hahmolle annettava ase
     * @param osumapisteet hahmon osumapistearvo
     * @param hyokkaysarvo hahmon hyökkäysarvo
     * @param puolustusarvo hahmon puolustusarvo
     */
    public Hahmo(String nimi, Koordinaatit k, Keho keho, Ase ase, int osumapisteet, int hyokkaysarvo, int puolustusarvo) {
        this.nimi = nimi;
        this.koordinaatit = k;
        this.keho = keho;
        this.ase = ase;
        this.osumapisteet = osumapisteet;
        this.hyokkaysarvo = hyokkaysarvo;
        this.puolustusarvo = puolustusarvo;
        this.arpoja = new Random();
    }

    public Koordinaatit getSijainti() {
        return this.koordinaatit;
    }

    /**
     * Muuttaa hahmon sijainniksi parametreina saadun.
     *
     * @param k uusi sijainti
     */
    public void siirry(Koordinaatit k) {
        this.koordinaatit = k;
    }

    /**
     * Hahmo ottaa metodissa vastaan hyökkäyksen ja raportoi sen tulokset
     * pelitilanteeseen. Hyökkäystä vastaan puolustaudutaan vertaamalla
     * hyökkäys- arvoa hahmon puolustusarvoon. Mikäli puolustus on yhtä suuri
     * tai suurempi, hyökkäys menee ohi. Muussa tapauksessa tehdään hyökkäyksen
     * verran vahinkoa osumapistepooliin ja kutsutaan kehon otaVahinkoa-metodia
     * samalla vahinko- arvolla.
     *
     * @param tilanne pelitilanne tulosten kirjaamiseen
     * @param vahinko hyökkäyksen aiheuttaman vahingon määrä
     * @param hyokkaysarvo vastustajan hyökkäysarvo
     * @return boolean onko hahmo kuollut hyökkäyksen jälkeen
     */
    public boolean puolusta(Pelitilanne tilanne, int vahinko, int hyokkaysarvo) {
        if (tilanne == null || vahinko < 0 || hyokkaysarvo < 0) {
            throw new IllegalArgumentException();
        }

        if (hyokkaysarvo > this.puolustusarvo) {
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

    /**
     * Hahmo suorittaa hyökkäyksen parametrina saamaansa toiseen hahmoon.
     * Hyökkäys suoritetaan arpomalla satunnaisluku väliltä 0-9, lisäämällä
     * siihen hahmon hyökkäysarvo, arpomalla aseen vahinko, ja välittämällä nämä
     * tiedot lopulta kohteena olevan hahmon puolusta-metodille.
     *
     * @param hahmo kohdehahmo
     * @param tilanne pelitilanne, johon hyökkäyksen tulos kirjataan
     * @return boolean kuoliko kohde k/e
     */
    public boolean hyokkaa(Hahmo hahmo, Pelitilanne tilanne) {
        if(hahmo.isElossa()) {
        tilanne.lisaaTapahtuma(tilanne.viestit.lyo(this.getNimi(), hahmo.getKohdeNimi()));
        boolean b = hahmo.puolusta(
                tilanne,
                this.getAse().getVahinko(),
                this.getHyokkaysarvo() + this.arpoja.nextInt(10));
        return b;
        }
        return false;
    }

    public int getPuolustusarvo() {
        return puolustusarvo;
    }

    public void setPuolustusarvo(int puolustusarvo) {
        this.puolustusarvo = puolustusarvo;
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    public void setOsumapisteet(int osumapisteet) {
        this.osumapisteet = osumapisteet;
    }

    public int getHyokkaysarvo() {
        return hyokkaysarvo;
    }

    public void setHyokkaysarvo(int hyokkaysarvo) {
        this.hyokkaysarvo = hyokkaysarvo;
    }

    public Keho getKeho() {
        return keho;
    }

    public void setKeho(Keho keho) {
        this.keho = keho;
    }

    public Ase getAse() {
        return ase;
    }

    public void setAse(Ase ase) {
        this.ase = ase;
    }

    /**
     * Palauttaa hahmon hyökkäysefektin, joka tämänhetkisessä toteutuksessa on
     * hahmon aseen hyökkäysefekti, sijoitettuna saatuihin koordinaatteihin.
     *
     * @param k kohdekoordinaatit
     * @return efekti saaduissa koordinaateissa
     */
    public Efekti getHyokkaysefekti(Koordinaatit k) {
        return this.getAse().getHyokkaysefekti(k);
    }

    public String getNimi() {
        return nimi;
    }

    /**
     * Palauttaa hahmon nimen kohdemuodossa, esim.
     * "gladiaattori"->"gladiaattoria"
     *
     * @return nimi
     */
    public String getKohdeNimi() {
        if (this.getNimi().equals("Gladiaattori")) {
            return "gladiaattoria";
        } else if (this.getNimi().equals("Hirviö")) {
            return "hirviötä";
        } else if (this.getNimi().equals("Lohikäärme")) {
            return "lohikäärmettä";
        }
        return "";
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Random getArpoja() {
        return arpoja;
    }

    /**
     * Tarkistaa, onko hahmo elossa (osumapisteet yli 0).
     *
     * @return boolean true/false elossa/kuollut
     */
    public boolean isElossa() {
        boolean b = (this.osumapisteet > 0) ? true : false;
        return b;
    }

    /**
     * Palauttaa muotoa "nimi, X osumapistettä" olevan tekstimuuttujan.
     *
     * @return String teksti
     */
    @Override
    public String toString() {
        return this.getNimi() + ", " + this.getOsumapisteet() + " osumapistettä";
    }
}
