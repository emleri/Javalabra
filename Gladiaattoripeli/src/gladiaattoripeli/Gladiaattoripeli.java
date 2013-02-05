
package gladiaattoripeli;

import gladiaattoripeli.UI.Kayttoliittyma;
import gladiaattoripeli.UI.KayttoliittymanOhjaaja;
import gladiaattoripeli.utilities.Sovelluslogiikka;
import javax.swing.SwingUtilities;

/**
 * Ohjelman käynnistävä main-metodin luokka. Ei muuta toiminnallisuutta.
 * @author Emleri
 */
public class Gladiaattoripeli {
    public static void main(String[] args) {
        KayttoliittymanOhjaaja ohjaaja = new KayttoliittymanOhjaaja();
        ohjaaja.aloitaPeli();
//        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(s);
//        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
