package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Luokka huolehtii hahmojen (sekä hirviöt että gladiaattori) vammautumisen
 * simuloinnista toimimalla kokoavana elementtinä Ruumiinosa-olioille ja
 * hallinnoimalla hyökkäysten vahinkoa niihin.
 */
public class Keho {

    private Ruumiinosa keskivartalo; // Kehon keskivartalo-ruumiinosa
    private Ruumiinosa paa; // Kehon pää-ruumiinosa
    private EnumMap<RuumiinosanNimi, Ruumiinosa> raajat; // Kehon raajat taulukossa, avaimena raajan nimi
    private Random arpoja; // Satunnaislukugeneraattori
    private String nimi; // Kehon omistajan nimi

    /**
     * Luo uuden, raajattoman kehon.
     *
     * @param nimi kehon omistajan nimi
     * @param osumapisteet kehon omistajan osumapisteet, joiden perusteella
     * raajojen osumapisteet määritellään
     */
    public Keho(String nimi, int osumapisteet) {
        this.nimi = nimi;
        this.keskivartalo = new Ruumiinosa(RuumiinosanNimi.KESKIVARTALO, this.nimi, osumapisteet);
        this.paa = new Ruumiinosa(RuumiinosanNimi.PAA, this.nimi, osumapisteet / 4);
        this.arpoja = new Random();
        this.raajat = new EnumMap<RuumiinosanNimi, Ruumiinosa>(RuumiinosanNimi.class);
    }

    /**
     * Lisää uuden ruumiinosan kehoon. Tallennusavaimena käytetään raajan nimeä.
     *
     * @param r lisättävä osa
     */
    public void lisaaRaaja(Ruumiinosa r) {
        this.raajat.put(r.getNimi(), r);
    }

    /**
     * Jakaa parametrina saadun vahingon satunnaiselle ruumiinosalle.
     *
     * @param tilanne viite pelitilanteeseen tapahtuman kirjaamista varten
     * @param vahinko vahingon määrä
     */
    public void otaVahinkoa(Pelitilanne tilanne, int vahinko) {
        int osumakohta = arpoja.nextInt(12);
        if (osumakohta < 2) {
            this.paa.otaVahinkoa(tilanne, vahinko);
        } else if (osumakohta < 7 && !this.raajat.isEmpty()) {
            Iterator<RuumiinosanNimi> raajaIteraattori = raajat.keySet().iterator();
            int satunnainen = arpoja.nextInt(RuumiinosanNimi.getSize());
            RuumiinosanNimi osa = raajaIteraattori.next();

            for (int i = 1; i < satunnainen; i++) {
                if (raajaIteraattori.hasNext()) {
                    osa = raajaIteraattori.next();
                }
            }
            this.raajat.get(osa).otaVahinkoa(tilanne, vahinko);
        } else {
            this.keskivartalo.otaVahinkoa(tilanne, vahinko);
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

    /**
     * Kapseloi raajataulukon get-metodin ja varmistaa, että haettu osa on
     * olemassa ennen hakua.
     *
     * @param osa haettavan osan nimi
     * @return haettu osa
     */
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
