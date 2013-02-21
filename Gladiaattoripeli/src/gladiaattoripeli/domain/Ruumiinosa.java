package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Pelitilanne;

/**
 * Luokka edustaa hahmojen yksittäisiä vartalon osia ja pitää kirjaa niiden
 * vahingoittumisesta. Ruumiinosat esiintyvät aina Keho-olion osina.
 */
public class Ruumiinosa {

    private RuumiinosanNimi nimi;
    private String omistajanNimi;
    private int osumapisteet;
    private Boolean kuollut;

    public Ruumiinosa(RuumiinosanNimi nimi, String omistajanNimi, int osumapisteet) {
        this.nimi = nimi;
        this.omistajanNimi = omistajanNimi;
        this.osumapisteet = osumapisteet;
        this.kuollut = false;
    }

    public void otaVahinkoa(Pelitilanne tilanne, int vahinko) {
        this.osumapisteet -= vahinko;
        String vahinkoteksti = 
                (this.omistajanNimi.charAt(this.omistajanNimi.length()-1) == 'e') 
                ? this.omistajanNimi + "e" 
                : this.omistajanNimi;
        vahinkoteksti += "n " + this.nimi.getNimi();
        tilanne.lisaaTapahtuma(tilanne.viestit.onHaavoittunut(vahinkoteksti));
        if (this.osumapisteet <= 0) {
            this.kuollut = true;
            tilanne.lisaaTapahtuma(tilanne.viestit.onVammautunut(this.omistajanNimi));
        }
    }

    public Boolean isKuollut() {
        return this.kuollut;
    }

    public RuumiinosanNimi getNimi() {
        return this.nimi;
    }

    public String getOmistajanNimi() {
        return omistajanNimi;
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }
    
    

    @Override
    public String toString() {
        return this.nimi.getNimi() + ", " + this.osumapisteet + " osumapistettä";
    }
}
