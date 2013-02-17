package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Efekti;
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

    private List<String> tapahtumat;
    private List<List<Efekti>> efektit;
    private List<Hirvio> surmatut;
    private List<Hirvio> hirviot;
    private Gladiaattori hahmo;
    private Boolean peliOhi;
    public Viestigeneraattori viestit;

    /**
     * Luokan konstruktori. Luo tyhjän tapahtumalokin ja surmattujen hirviöiden 
     * listan, merkitsee oletusarvoisesti pelin olevan käynnissä ja kutsuu 
     * viestigeneraattori-alaluokan konstruktoria.
     */
    public Pelitilanne() {
        tapahtumat = new ArrayList<String>();
        efektit = new ArrayList<List<Efekti>>();
        surmatut = new ArrayList<Hirvio>();
        peliOhi = false;
        this.viestit = new Viestigeneraattori();
    }

    /**
     * Palauttaa vuororaportin tapahtumalokin
     * @return tapahtumaloki
     */
    public List<String> getTapahtumat() {
        return tapahtumat;
    }

    /**
     * Lisää vuororaportin tapahtumalokiin uuden tapahtuman.
     * 
     * @param tapahtuma lisättävä tapahtuma parametrina
     */
    public void lisaaTapahtuma(String tapahtuma) {
        this.tapahtumat.add(tapahtuma);
    }

    /**
     * Lisää voitettuja vihollisia listalle, jolla vuororaportti pitää niistä 
     * kirjaa.
     * @param surmatut parametrina annetaan lista uusista surmatuista hirviöistä
     */
    public void lisaaSurmattuja(List<Hirvio> surmatut) {
        this.surmatut.addAll(surmatut);
    }

    /**
     * Getteri surmattujen hirviöiden listalle.
     * @return surmatut hirviöt
     */
    public List<Hirvio> getSurmatut() {
        return surmatut;
    }

    /**
     * Getteri vuororaportin kuljettamalle viitteelle areenan hirviölistaan.
     * @return hirviölista
     */
    public List<Hirvio> getHirviot() {
        return this.hirviot;
    }

    /**
     * Setteri, jolla areena tallentaa viitteen hirviöihinsä viestiraporttiin.
     * @param hirviot tallennettavat hirviöt
     */
    public void setHirviot(List<Hirvio> hirviot) {
        this.hirviot = hirviot;
    }

    /**
     * Areena asettaa tällä metodilla vuororaporttiin viitteen, jolla 
     * käyttöliittymä pääsee raportin kautta käsiksi gladiaattorin tietoihin.
     * @param hahmo
     */
    public void setHahmo(Gladiaattori hahmo) {
        this.hahmo = hahmo;
    }

    /**
     * Palauttaa viitteen areenan gladiaattori-olentoon.
     * @return areenan gladiaattori
     */
    public Gladiaattori getHahmo() {
        return hahmo;
    }

    /**
     * Metodi merkitsee vuororaporttiin pelin loppuneen.
     */
    public void lopetaPeli() {
        this.peliOhi = true;
    }

    /**
     * Metodi tarkistaa, onko peli ohi.
     * @return true/false - peli ohi/peli jatkuu
     */
    public Boolean onkoPeliOhi() {
        return this.peliOhi;
    }
    
    public void uusiVuoro() {
        tapahtumat.clear();
    }
}
