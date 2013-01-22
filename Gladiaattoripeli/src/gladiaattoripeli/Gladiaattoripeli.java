
package gladiaattoripeli;

import gladiaattoripeli.UI.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Gladiaattoripeli {
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
