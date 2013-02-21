package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Areena;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Pelialueen piirtävä luokka. Lukee pelitilanteen vuororaportista ja piirtää
 * sen käyttöliittymäikkunaan.
 */
public class KartanPiirtaja {

    private JLabel piirtoalue;
    private Piirtaja piirtaja;
    private Timer ajastin;

    /**
     * Konstruktori.
     */
    public KartanPiirtaja() {
    }

    /**
     * Tallentaa viitteen uuteen piirtoalueeseen korvaten vanhan.
     *
     * @param piirtoalue uusi piirtoalue
     */
    public void setPiirtoalue(JLabel piirtoalue) {
        this.piirtoalue = piirtoalue;
    }

    /**
     * Piirtää areenalta saadun pelitilanteen piirtoalueelle. Metodi luo ensin
     * html-muotoillun String-olion, jonka sitten asettaa piirtoalue-JLabelin
     * tekstiksi ja päivittää sen.
     *
     * @param a viite areenaan parametrina
     */
    public void piirra(Areena a) {
        this.ajastin = new Timer(500, this.piirtaja);
        this.ajastin.setInitialDelay(0);
        this.piirtaja = new Piirtaja(this.ajastin, this.piirtoalue, a);
        this.piirtaja.haeEfektit();
        ajastin.start();
        
//        Boolean piirrettiin = false;
//        Efekti efektit = new Efekti(' ');
//        StringBuilder kartta;
//        if (piirraEfektit) {
//            efektit = a.getEfektit();
//        }
//
//        while (true) {
//            kartta = new StringBuilder("");
//            kartta.append("<html>");
//
//            // Piirtää ylälaidan seinän
//            for (int i = -1; i <= a.getLeveys(); i++) {
//                kartta.append("#");
//            }
//            kartta.append("<br>#");
//
//            // Piirtää areenan ja sen olennot
//
//            for (int y = 0; y < a.getKorkeus(); y++) {
//                if (y != 0) {
//                    kartta.append("#<br>#");
//                }
//                for (int x = 0; x < a.getLeveys(); x++) {
//                    Koordinaatit ruutu = new Koordinaatit(x, y);
//
//                    if (piirraEfektit && efektit.piirretaanko(ruutu)) {
//                        kartta.append(efektit);
//                        piirrettiin = true;
//                    } else if (a.onkoRuudussaHirviota(ruutu)) {
//                        kartta.append('h');
//                    } else if (a.getHahmo().getSijainti().equals(ruutu)) {
//                        kartta.append('@');
//                    } else {
//                        kartta.append('_');
//                    }
//                }
//
//            }
//
//            kartta.append(
//                    "#<br>");
//
//            // Piirtää alalaidan seinän
//            for (int i = -1;
//                    i
//                    <= a.getLeveys();
//                    i++) {
//                kartta.append("#");
//            }
//
//            kartta.append(
//                    "</html>");
//            this.piirtoalue.setText(kartta.toString());
//            this.piirtoalue.repaint();
//
//            // Tarkistuksia
//
//            try {
//                if (piirrettiin) {
//                    piirrettiin = false;
//                }
//            } catch (Exception e) {
//            }
//
//            if (!piirraEfektit) {
//                break;
//            } else if (efektit.getSeuraava()
//                    == null) {
//                piirraEfektit = false;
//            } else {
//                efektit = efektit.getSeuraava();
//            }
//        }
    }
}
