package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Hirvio;

/**
 * Sovelluslogiikka ohjaa ohjelman perustoimintoja: pelin aloittamista, 
 * lopettamista ja vuorojen pelaamista. Luokka on keskeinen, kokoava osa 
 * pelimekaniikalle ja linkki käyttöliittymään.
 */
public class Sovelluslogiikka {

    private Areena areena;
    private Boolean peliOhi;

    public Sovelluslogiikka() {
        this.areena = new Areena(21, 21);
        this.peliOhi = false;
    }

    public void luoHirvioita(int maara) {
        for (int i = 0; i < maara; i++) {
            if (i < areena.getLeveys()) {
                this.areena.lisaaHirvio(new Hirvio(i, 0, 5));
            } else if (i < areena.getKorkeus() + areena.getLeveys()) {
                this.areena.lisaaHirvio(new Hirvio(0, i - areena.getLeveys(), 5));
            } else {
                this.areena.lisaaHirvio(new Hirvio(0, 0, 5));
            }
        }
    }
    
    public Vuororaportti pelaaVuoro(Suunta s) {
        Vuororaportti v = new Vuororaportti();
        v.setHahmo(this.areena.getHahmo());
        v.setHirviot(this.areena.getHirviot());
        if (!this.peliOhi) {
            areena.toimiHahmollaSuuntaan(s, v);
            areena.paivitaTilanne(v);
            areena.liikutaHirvioita(v);
            areena.paivitaTilanne(v);

            if (v.peliOhi()) {
                this.peliOhi = true;
            }
            return v;
        } else {
            v.lopetaPeli();
            return v;
        }
    }

    public Areena getAreena() {
        return this.areena;
    }
}
