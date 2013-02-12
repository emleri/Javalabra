package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Vuororaportti;

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

    public void otaVahinkoa(Vuororaportti v, int vahinko) {
        this.osumapisteet -= vahinko;
        v.lisaaTapahtuma(v.viestit.onHaavoittunut(this.omistajanNimi + "n " + this.nimi.getNimi()));
        if (this.osumapisteet <= 0) {
            this.kuollut = true;
            v.lisaaTapahtuma(v.viestit.onVammautunut(this.omistajanNimi));
        }
    }

    public Boolean onkoKuollut() {
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
