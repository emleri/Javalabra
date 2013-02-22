package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Koordinaatit;
import gladiaattoripeli.domain.Lohikaarme;
import gladiaattoripeli.domain.Ruumiinosa;
import gladiaattoripeli.domain.RuumiinosanNimi;

/**
 * Apuluokka, joka kapseloi hahmojen monimutkaiset konstruktorit. Luo valmiita
 * hahmoja ennaltamääriteltyjen parametrien mukaan.
 *
 * @author Emleri
 */
public class Hahmogeneraattori {

    private Asegeneraattori agen; // Asegeneraattori hahmojen aseiden luomista varten.

    /**
     * Konstruktori.
     */
    public Hahmogeneraattori() {
        this.agen = new Asegeneraattori();
    }

    /**
     * Luo neliraajaisen humanoidin kehon.
     *
     * @param nimi kehon omistavan hahmon nimi
     * @param osumapisteet kehon omistavan hahmon osumapisteet
     * @return luotu keho
     */
    private Keho ihmiskeho(String nimi, int osumapisteet) {
        Keho keho;
        if (osumapisteet < 0) {
            throw new IllegalArgumentException();
        } else {
            keho = new Keho(nimi, osumapisteet);
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAKASI, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENKASI, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENJALKA, nimi, osumapisteet / 2));
        }
        return keho;
    }

    /**
     * Luo lohikäärmeen kehon. Käyttää ihmiskeho-metodia ja lisää sen luomaan
     * kehoon siivet ja hännän.
     *
     * @return luotu keho
     */
    private Keho lohikaarmeenKeho() {
        String nimi = "Lohikäärme";
        int osumapisteet = 20;
        Keho keho = this.ihmiskeho(nimi, osumapisteet);
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEASIIPI, nimi, osumapisteet / 4));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENSIIPI, nimi, osumapisteet / 4));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.HANTA, nimi, osumapisteet / 4));
        return keho;
    }

    /**
     * Luo gladiaattorin. Käyttää ihmiskeho-metodia luomaan gladiaattorin
     * vartalon ja antaa tälle asegeneraattorin luoman miekan. Gladiaattorin
     * sijainniksi asetetaan areenan keskikohta.
     *
     * @param a viite areenaan
     * @return luotu gladiaattori
     */
    public Gladiaattori luoGladiaattori(Areena a) {
        Gladiaattori g = new Gladiaattori(
                new Koordinaatit(a.getLeveys() / 2, a.getKorkeus() / 2),
                this.ihmiskeho("Gladiaattori", 100),
                agen.getMiekka());
        return g;
    }

    /**
     * Luo hirviön. Käyttää ihmiskeho-metodia luomana hirviön vartalon ja antaa
     * tälle asegeneraattorin luoman nuijan.
     *
     * @return luotu hirviö
     */
    public Hirvio luoHirvio() {
        int osumapisteet = 10;
        Hirvio hirvio = new Hirvio(
                osumapisteet,
                this.ihmiskeho("Hirviö", osumapisteet),
                agen.getNuija());
        return hirvio;
    }

    /**
     * Luo lohikäärmeen. Käyttää lohikaarmeenKeho-metodia luomaan vartalon,
     * antaa lohikäärmeelle aseiksi asegeneraattorin luomat kynnet.
     *
     * @return luotu lohikäärme
     */
    public Hirvio luoLohikaarme() {
        int osumapisteet = 20;
        Lohikaarme d = new Lohikaarme(
                osumapisteet,
                this.lohikaarmeenKeho(),
                agen.getKynnet());
        d.setNimi("Lohikäärme");
        return d;
    }

    /**
     * Luo vapaammin määritellyn tyyppisen hirviön. Ei pelinsisäistä käyttöä
     * tällä hetkellä, metodi olemassa testausta ja tulevaa laajentamista
     * varten.
     *
     * @param nimi luotavan hirviön nimi
     * @param osumapisteet luotavan hirviön osumapisteet
     * @return luotu hirviö
     */
    public Hirvio luoKustomoituHirvio(String nimi, int osumapisteet) {
        return new Hirvio(
                osumapisteet,
                this.ihmiskeho(nimi, osumapisteet),
                agen.getNuija());
    }
}
