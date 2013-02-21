package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import java.util.ArrayList;
import java.util.List;

/**
 * Tiedonkuljettajaluokka, joka välittää pelitapahtumat ja -tilanteen ylöspäin
 * käyttöliittymälle. Sisältää myös viestigeneraattori-apuluokan tapahtumien
 * kirjaamista varten.
 */
public class Pelitilanne {

    private List<String> tapahtumat; // vuoron tapahtumaraportit
    private Gladiaattori gladiaattori; // viite Areenan gladiaattoriin
    private Boolean peliOhi;
    public Viestigeneraattori viestit;

    /**
     * Luokan konstruktori. Luo tyhjän tapahtumalokin ja surmattujen hirviöiden
     * listan, merkitsee oletusarvoisesti pelin olevan käynnissä ja kutsuu
     * viestigeneraattori-alaluokan konstruktoria.
     */
    public Pelitilanne() {
        tapahtumat = new ArrayList<String>();
        peliOhi = false;
        this.viestit = new Viestigeneraattori();
    }

    public void alustaTilanne() {
        this.uusiVuoro();
        this.peliOhi = false;
    }

    /**
     * Palauttaa tapahtumalokin
     *
     * @return tapahtumaloki
     */
    public List<String> getTapahtumat() {
        return tapahtumat;
    }

    /**
     * Lisää tapahtumalokiin uuden tapahtuman.
     *
     * @param tapahtuma lisättävä tapahtuma parametrina
     */
    public void lisaaTapahtuma(String tapahtuma) {
        this.tapahtumat.add(tapahtuma);
    }
    /**
     * Areena asettaa tällä metodilla pelitilanteeseen viitteen, jolla
     * käyttöliittymä pääsee gladiaattorin tietoihin.
     *
     * @param hahmo
     */
    public void setGladiaattori(Gladiaattori hahmo) {
        this.gladiaattori = hahmo;
    }

    /**
     * Palauttaa viitteen areenan gladiaattori-olentoon.
     *
     * @return areenan gladiaattori
     */
    public Gladiaattori getGladiaattori() {
        return gladiaattori;
    }

    /**
     * Metodi merkitsee tilanteeseen pelin loppuneen.
     */
    public void lopetaPeli() {
        this.peliOhi = true;
    }

    /**
     * Metodi tarkistaa, onko peli ohi.
     *
     * @return true/false - peli ohi/peli jatkuu
     */
    public Boolean isPeliOhi() {
        return this.peliOhi;
    }

    /**
     * Metodi tyhjentää tapahtumahistorian. Kutsutaan vuorojen alussa, jotta
     * edellisten vuorojen tapahtumat eivät tulostu uudelleen.
     */
    public void uusiVuoro() {
        tapahtumat.clear();
    }
}
