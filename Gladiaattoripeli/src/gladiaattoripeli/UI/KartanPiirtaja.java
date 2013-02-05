package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.utilities.Koordinaatit;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Pelialueen piirtävä luokka. Lukee pelitilanteen vuororaportista ja piirtää sen 
 * käyttöliittymäikkunaan.
 */
public class KartanPiirtaja {

    private JLabel piirtoalue;

    public KartanPiirtaja() {
    }

    public void setPiirtoalue(JLabel piirtoalue) {
        this.piirtoalue = piirtoalue;
    }

    public void piirra(Areena a) {
        StringBuilder kartta = new StringBuilder("");
        kartta.append("<html>");
        for (int y = 0; y < a.getKorkeus(); y++) {
            if (y != 0) {
                kartta.append("<br>");
            }
            for (int x = 0; x < a.getLeveys(); x++) {
                Koordinaatit ruutu = new Koordinaatit(x, y);
                if (a.onkoRuudussaHirviota(ruutu)) {
                    kartta.append('h');
                } else if (a.getHahmo().getSijainti().equals(ruutu)) {
                    kartta.append('@');
                } else {
                    kartta.append('_');
                }
            }
        }
        kartta.append("</html>");
        this.piirtoalue.setText(kartta.toString());
        this.piirtoalue.getParent().repaint();
    }
}
