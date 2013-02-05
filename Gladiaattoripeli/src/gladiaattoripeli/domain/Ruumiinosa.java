package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Vuororaportti;

/**
 * Luokka edustaa hahmojen yksittäisiä vartalon osia ja pitää kirjaa niiden 
 * vahingoittumisesta. Ruumiinosat esiintyvät aina Keho-olion osina.
 */
public class Ruumiinosa {

    private RuumiinosanNimi nimi;
    private String haavoittuminen;
    private String tuhoutuminen;
    private int osumapisteet;
    private Boolean kuollut;

    public Ruumiinosa(RuumiinosanNimi nimi, int osumapisteet) {
        this.nimi = nimi;
        this.osumapisteet = osumapisteet;
        this.kuollut = false;
    }

    public void otaVahinkoa(Vuororaportti v, int vahinko) {
        this.osumapisteet -= vahinko;
        String vahinkoraportti = this.nimi + this.haavoittuminen;
        if (this.osumapisteet <= 0) {
            this.kuollut = true;
            vahinkoraportti += "/n" + this.nimi + " " + this.tuhoutuminen;
        }
        v.lisaaTapahtuma(vahinkoraportti);
    }

    public void setHaavoittumisviesti(String s) {
        this.haavoittuminen = s;
    }

    public void setKuolinviesti(String s) {
        this.tuhoutuminen = s;
    }

    public Boolean onkoKuollut() {
        return this.kuollut;
    }

    public RuumiinosanNimi getNimi() {
        return this.nimi;
    }

    @Override
    public String toString() {
        return this.nimi.getNimi() + ", " + this.osumapisteet + " osumapistettä"; 
    }
}
