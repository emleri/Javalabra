package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;
import java.util.List;

/**
 * Sovelluslogiikka ohjaa ohjelman perustoimintoja: pelin aloittamista,
 * lopettamista ja vuorojen pelaamista. Luokka on keskeinen, kokoava osa
 * pelimekaniikalle ja linkki käyttöliittymään.
 */
public class Sovelluslogiikka {

    private Areena areena;
    private HighScorenKasittelija pisteyttaja;
    private Pelitilanne tilanne;

    public Sovelluslogiikka() {
        this.tilanne = new Pelitilanne();
        this.areena = new Areena(23, 21, tilanne);
        this.areena.alustaPeli();
        tilanne.setHahmo(this.areena.getHahmo());
        tilanne.setHirviot(this.areena.getHirviot());
        this.pisteyttaja = new HighScorenKasittelija();
    }

    /**
     * Metodi suorittaa peliä vuoron verran eteenpäin. Ensin alustetaan tilanne 
     * vuoron alkua vastaavaksi ja tarkistetaan, että peli on yhä käynnissä, 
     * sitten toimitaan ensin hahmolla, päivitetään pelitila, toimitaan 
     * hirviöillä ja päivitetään jälleen pelitila vuoron loppua vastaavaksi.
     * @param s pelaajan komento gladiaattorille
     */
    public void pelaaVuoro(Komennot s) {
        tilanne.uusiVuoro();
        this.areena.tyhjennaEfektit();

        if (!this.tilanne.onkoPeliOhi()) {
            areena.toimiHahmollaSuuntaan(s);
            areena.paivitaTilanne();
            areena.liikutaHirvioita();
            areena.paivitaTilanne();
            areena.seuraavaAalto();
        }
    }

    /**
     * Metodi suorittaa peliä vuoron verran eteenpäin hypäten pelaajan toiminnon
     * yli. Toimii muuten kuten metodi pelaaVuoro.
     */
    public void pelaaHirvioidenVuoro() {
        tilanne.uusiVuoro();
        this.areena.tyhjennaEfektit();

        if (!this.tilanne.onkoPeliOhi()) {
            areena.liikutaHirvioita();
            areena.paivitaTilanne();
            areena.seuraavaAalto();
        }
    }

    /**
     * Välittää pelaajan nimen HighScorenKasittelijalle, joka tallentaa sen 
     * pistetilastoon. Palauttaa sitten pistetilaston käyttöliittymän 
     * tulostettavaksi.
     * @param nimi pelaajan nimi
     * @return pistetilasto
     */
    public List<Pisteet> tallennaHighScore(String nimi) {
        this.pisteyttaja.lisaaHighScore(new Pisteet(this.areena.getHahmo().getTapot().size(), nimi));
        return this.pisteyttaja.getHighScore();
    }

    public Pelitilanne getPelitilanne() {
        return this.tilanne;
    }

    public Areena getAreena() {
        return this.areena;
    }
}
