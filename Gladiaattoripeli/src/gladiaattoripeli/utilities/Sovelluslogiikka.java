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
    private Boolean peliOhi;
    private HighScorenKasittelija pisteyttaja;
    private Pelitilanne tilanne;

    public Sovelluslogiikka() {
        this.tilanne = new Pelitilanne();
        this.areena = new Areena(21, 21, tilanne);
        this.areena.luoHahmot();
        this.areena.luoHirvioita(10);
        tilanne.setHahmo(this.areena.getHahmo());
        tilanne.setHirviot(this.areena.getHirviot());
        this.pisteyttaja = new HighScorenKasittelija();
    }

    public void pelaaVuoro(Komennot s) {
        tilanne.uusiVuoro();
        this.areena.tyhjennaEfektit();
        
        if (!this.tilanne.onkoPeliOhi()) {
            areena.toimiHahmollaSuuntaan(s);
            areena.paivitaTilanne();
            areena.liikutaHirvioita();
            areena.paivitaTilanne();
        }
    }
    
    public void pelaaHirvioidenVuoro() {
        tilanne.uusiVuoro();
        this.areena.tyhjennaEfektit();
        
        if (!this.tilanne.onkoPeliOhi()) {
            areena.liikutaHirvioita();
            areena.paivitaTilanne();
        }
    }
    
    public List<String> tallennaHighScore(String nimi) {
        this.pisteyttaja.lisaaHighScore(this.areena.getHahmo().getTapot().size() + " pistettä, " + nimi);
        return this.pisteyttaja.getHighScore();
    }

    public Pelitilanne getPelitilanne() {
        return this.tilanne;
    }    
    
    public Areena getAreena() {
        return this.areena;
    }
}
