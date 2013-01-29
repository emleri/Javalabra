
package gladiaattoripeli;

import gladiaattoripeli.UI.Kayttoliittyma;
import gladiaattoripeli.UI.KayttoliittymanOhjaaja;
import gladiaattoripeli.utilities.Sovelluslogiikka;
import javax.swing.SwingUtilities;

public class Gladiaattoripeli {
    public static void main(String[] args) {
        KayttoliittymanOhjaaja ohjaaja = new KayttoliittymanOhjaaja();
        ohjaaja.aloitaPeli();
//        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(s);
//        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
