package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.RuumiinosanNimi;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Luokka huolehtii hahmojen (sekä hirviöt että gladiaattori) vammautumisen
 * simuloinnista toimimalla kokoavana elementtinä Ruumiinosa-olioille ja
 * hallinnoimalla hyökkäysten vahinkoa niihin.
 */
public class Keho {

    private Ruumiinosa keskivartalo;
    private Ruumiinosa paa;
    private EnumMap<RuumiinosanNimi, Ruumiinosa> raajat;
    private Random arpoja;
    private String nimi;

    public Keho(String nimi, int osumapisteet) {
        this.nimi = nimi;
        this.keskivartalo = new Ruumiinosa(RuumiinosanNimi.KESKIVARTALO, this.nimi, osumapisteet);
        this.paa = new Ruumiinosa(RuumiinosanNimi.PAA, this.nimi, osumapisteet / 4);
        this.arpoja = new Random();
        this.raajat = new EnumMap<RuumiinosanNimi, Ruumiinosa>(RuumiinosanNimi.class);
    }

    public void lisaaRaaja(Ruumiinosa r) {
        this.raajat.put(r.getNimi(), r);
    }

    public void otaVahinkoa(Pelitilanne v, int vahinko) {
        int osumakohta = arpoja.nextInt(12);
        if (osumakohta < 2) {
            this.paa.otaVahinkoa(v, vahinko);
        } else if (osumakohta < 7 && !this.raajat.isEmpty()) {
            Iterator<RuumiinosanNimi> raajaIteraattori = raajat.keySet().iterator();
            int satunnainen = arpoja.nextInt(RuumiinosanNimi.getSize());
            RuumiinosanNimi osa = raajaIteraattori.next();

            for (int i = 1; i < satunnainen; i++) {
                if (raajaIteraattori.hasNext()) {
                    osa = raajaIteraattori.next();
                }
            }
            this.raajat.get(osa).otaVahinkoa(v, vahinko);
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

    public EnumMap<RuumiinosanNimi, Ruumiinosa> getRaajat() {
        return raajat;
    }

    public Ruumiinosa getRaaja(RuumiinosanNimi osa) {
        if (this.raajat.containsKey(osa)) {
            return this.raajat.get(osa);
        }
        return null;
    }

    @Override
    public String toString() {
        String s = this.paa + "\n" + this.keskivartalo;
        for (Ruumiinosa o : this.raajat.values()) {
            s += "\n" + o;
        }
        return s;
    }
}
