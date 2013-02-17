package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Luokka huolehtii hahmojen (sekä hirviöt että gladiaattori) vammautumisen
 * simuloinnista toimimalla kokoavana elementtinä Ruumiinosa-olioille ja
 * hallinnoimalla hyökkäysten vahinkoa niihin.
 */
public class Keho {

    private Ruumiinosa keskivartalo;
    private Ruumiinosa paa;
    private List<Ruumiinosa> raajat;
    private Random arpoja;
    private String nimi;


    public Keho(String nimi, int osumapisteet) {
        this.nimi = nimi;
        this.keskivartalo = new Ruumiinosa(RuumiinosanNimi.KESKIVARTALO, this.nimi, osumapisteet);
        this.paa = new Ruumiinosa(RuumiinosanNimi.PAA, this.nimi, osumapisteet / 4);
        this.arpoja = new Random();
        this.raajat = new ArrayList<Ruumiinosa>();
    }

    public void lisaaRaaja(Ruumiinosa r) {
        this.raajat.add(r);
    }

    public void otaVahinkoa(Pelitilanne v, int vahinko) {
        int osumakohta = arpoja.nextInt(12);
        if (osumakohta < 2) {
            this.paa.otaVahinkoa(v, vahinko);
        } else if (osumakohta < 7 && this.raajat.size() > 0) {
            this.raajat.get(arpoja.nextInt(this.raajat.size())).otaVahinkoa(v, vahinko);
        } else {
            this.keskivartalo.otaVahinkoa(v, vahinko);
        }
    }

    public String getNimi() {
        return nimi;
    }

    public Ruumiinosa getPaa() {
        return paa;
    }

    public Ruumiinosa getKeskivartalo() {
        return keskivartalo;
    }

    public List<Ruumiinosa> getRaajat() {
        return raajat;
    }

    @Override
    public String toString() {
        String s = this.paa + "\n" + this.keskivartalo;
        for (Ruumiinosa o : this.raajat) {
            s += "\n" + o;
        }
        return s;
    }
}
