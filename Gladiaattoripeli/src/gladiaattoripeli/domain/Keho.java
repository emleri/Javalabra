
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Vuororaportti;
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

    public Keho(int osumapisteet) {
        this.keskivartalo = new Ruumiinosa(RuumiinosanNimi.KESKIVARTALO, osumapisteet);
        this.paa = new Ruumiinosa(RuumiinosanNimi.PAA, osumapisteet / 4);
        this.arpoja = new Random();
        this.raajat = new ArrayList<Ruumiinosa>();
    }
    
    public void lisaaRaaja(Ruumiinosa r) {
        this.raajat.add(r);
    }
    
    public void otaVahinkoa(Vuororaportti v, int vahinko) {
        int osumakohta = arpoja.nextInt(12);
        if (osumakohta < 5) {
            this.keskivartalo.otaVahinkoa(v, vahinko);
        } else if (osumakohta < 10) {
            this.raajat.get(arpoja.nextInt(this.raajat.size())).otaVahinkoa(v, vahinko);
        } else {
            this.paa.otaVahinkoa(v, vahinko);
        }
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
