/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Ruumiinosa;

/**
 *
 * @author Emleri
 */
public class Hahmogeneraattori {

    private Keho keho;

    public Hahmogeneraattori() {
    }

    private Keho ihmiskeho(String nimi, int osumapisteet) {
        if (osumapisteet < 0) {
            throw new IllegalArgumentException();
        } else {
            this.keho = new Keho(nimi, osumapisteet);
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAKASI, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENKASI, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, nimi, osumapisteet / 2));
            keho.lisaaRaaja(new Ruumiinosa(RuumiinosanNimi.VASENJALKA, nimi, osumapisteet / 2));

            return keho;
        }
    }

    public Gladiaattori luoGladiaattori(Areena a) {
        keho = this.ihmiskeho("Gladiaattori", 15);
        Gladiaattori g = new Gladiaattori(a.getLeveys() / 2, a.getKorkeus() / 2, a.getLeveys(), a.getKorkeus(), keho);
        return g;
    }
}
