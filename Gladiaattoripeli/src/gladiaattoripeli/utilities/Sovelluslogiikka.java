package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;
import java.util.List;

/**
 * Sovelluslogiikka ohjaa ohjelman perustoimintoja: pelin aloittamista,
 * lopettamista ja vuorojen pelaamista. Luokka on keskeinen, kokoava osa
 * pelimekaniikalle ja linkki käyttöliittymään.
 */
public class Sovelluslogiikka {

    private Areena areena; // Pelialue ja hahmojen liikkeiden koordinoija
    private HighScorenKasittelija pisteyttaja; // Pisteidentallentaja
    private Pelitilanne tilanne; // Pelitilanne

    /**
     * Konstruktori. Luo pelimekaniikan päärungon: sovelluslogiikan, areenan ja
     * pelitilanteen. Käskee myös areenaa luomaan pelin alkutilan hahmoineen ja 
     * tallentaa gladiaattori-viitteen pelitilanteeseen.
     */
    public Sovelluslogiikka() {
        this.tilanne = new Pelitilanne();
        this.areena = new Areena(23, 21, tilanne);
        this.areena.alustaPeli();
        tilanne.setGladiaattori(this.areena.getGladiaattori());
        this.pisteyttaja = new HighScorenKasittelija();
    }

    /**
     * Metodi suorittaa peliä vuoron verran eteenpäin. Ensin alustetaan tilanne
     * vuoron alkua vastaavaksi ja tarkistetaan, että peli on yhä käynnissä,
     * sitten toimitaan ensin hahmolla, päivitetään pelitila, toimitaan
     * hirviöillä ja päivitetään jälleen pelitila vuoron loppua vastaavaksi.
     *
     * @param s pelaajan komento gladiaattorille
     */
    public void pelaaVuoro(Komennot s) {
        tilanne.uusiVuoro();
        this.areena.tyhjennaEfektit();

        if (!this.tilanne.isPeliOhi()) {
            areena.toimiHahmollaSuuntaan(s);
            areena.paivitaTilanne();
            areena.liikutaHirvioita();
            areena.paivitaTilanne();
            areena.seuraavaAalto();
        }
    }

    /**
     * Välittää pelaajalle komennon, joka ei johda vuoron toiminnon käyttämiseen.
     * @param k komento
     */
    public void annaPelaajalleKomento(Komennot k) {
        if (k.equals(Komennot.HYOKKAAVAMMIN)) {
            this.areena.getGladiaattori().muutaHyokkaysarvoa(+1);
        } else if (k.equals(Komennot.PUOLUSTAVAMMIN)) {
            this.areena.getGladiaattori().muutaHyokkaysarvoa(-1);
        }
    }

    /**
     * Metodi suorittaa peliä vuoron verran eteenpäin hypäten pelaajan toiminnon
     * yli. Toimii muuten kuten metodi pelaaVuoro.
     */
    public void pelaaHirvioidenVuoro() {
        tilanne.uusiVuoro();
        this.areena.tyhjennaEfektit();

        if (!this.tilanne.isPeliOhi()) {
            areena.liikutaHirvioita();
            areena.paivitaTilanne();
            areena.seuraavaAalto();
        }
    }

    /**
     * Välittää pelaajan nimen HighScorenKasittelijalle, joka tallentaa sen
     * pistetilastoon. Palauttaa sitten pistetilaston käyttöliittymän
     * tulostettavaksi.
     *
     * @param nimi pelaajan nimi
     * @return pistetilasto
     */
    public List<Pisteet> tallennaHighScore(String nimi) {
        if (nimi != null) {
            this.pisteyttaja.lisaaHighScore(new Pisteet(this.areena.getGladiaattori().getTapot().size(), nimi));
        }
        return this.pisteyttaja.getHighScore();
    }

    public Pelitilanne getPelitilanne() {
        return this.tilanne;
    }

    public Areena getAreena() {
        return this.areena;
    }

    /**
     * Alustaa kaikki kolme pääluokkaa ja aloittaa pelin alusta.
     */
    public void uusiPeli() {
        this.areena.alustaPeli();
        this.tilanne.alustaTilanne();
        this.tilanne.setGladiaattori(areena.getGladiaattori());
    }
}
