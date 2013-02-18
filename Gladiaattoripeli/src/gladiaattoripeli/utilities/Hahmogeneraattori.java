/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Lohikaarme;
import gladiaattoripeli.domain.Ruumiinosa;

/**
 *
 * @author Emleri
 */
public class Hahmogeneraattori {

    private Keho keho;
    private Asegeneraattori agen;

    public Hahmogeneraattori() {
        this.agen = new Asegeneraattori();
    }

    private void ihmiskeho(String nimi, int osumapisteet) {
        if (osumapisteet < 0) {
            throw new IllegalArgumentException();
        } else {
            this.keho = new Keho(nimi, osumapisteet);
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAKASI, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENKASI, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENJALKA, nimi, osumapisteet / 2));
        }
    }

    private void lohikaarmeenKeho() {
        String nimi = "Lohikäärme";
        int osumapisteet = 20;
        this.keho = new Keho(nimi, osumapisteet);
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAKASI, nimi, osumapisteet / 2));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENKASI, nimi, osumapisteet / 2));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, nimi, osumapisteet / 2));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENJALKA, nimi, osumapisteet / 2));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEASIIPI, nimi, osumapisteet / 4));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENSIIPI, nimi, osumapisteet / 4));
        keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.HANTA, nimi, osumapisteet / 4));
    }

    public Gladiaattori luoGladiaattori(Areena a) {
        this.ihmiskeho("Gladiaattori", 100);
        Gladiaattori g = new Gladiaattori(new Koordinaatit(a.getLeveys() / 2, a.getKorkeus() / 2), keho);
        g.setAse(agen.getMiekka());
        return g;
    }

    public Hirvio luoHirvio() {
        int osumapisteet = 10;
        this.ihmiskeho("Hirviö", osumapisteet);
        Hirvio hirvio = new Hirvio(osumapisteet, keho);
        hirvio.setAse(agen.getNuija());

        return hirvio;
    }

    public Hirvio luoLohikaarme() {
        this.lohikaarmeenKeho();
        Lohikaarme d = new Lohikaarme(20, this.keho);
        d.setNimi("Lohikäärme");
        d.setAse(agen.getKynnet());
        
        return d;
    }

    public Hirvio luoKustomoituHirvio(String nimi, int osumapisteet) {
        this.ihmiskeho(nimi, osumapisteet);
        return new Hirvio(osumapisteet, keho);
    }
}
