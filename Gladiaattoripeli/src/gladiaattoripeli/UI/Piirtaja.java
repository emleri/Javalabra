package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Efekti;
import gladiaattoripeli.domain.Koordinaatit;
import gladiaattoripeli.domain.Lohikaarme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Kartanpiirtäjäluokka. Toimii Swing Timerin ajastamana, piirtäen joka tickillä
 * uuden ruudun pelianimaatiota kunnes kokonaisuus on piirretty ja ajastin
 * pysäytetään.
 *
 * BUGI: Piirtoa täytyy kutsua kahdesti tai se ei toimi oikein.
 */
public class Piirtaja implements ActionListener {

    private Timer ajastin;
    private JLabel piirtoalue;
    private Areena areena;
    private Efekti efektit;
    private Boolean piirraEfektit;

    /**
     * Konstruktori.
     *
     * @param ajastin Swing Timer, johon Piirtaja on kytketty
     * @param piirtoalue alue, jolle kartta piirretään
     * @param areena areena, joka alueelle piirretään
     */
    public Piirtaja(Timer ajastin, JLabel piirtoalue, Areena areena) {
        this.ajastin = ajastin;
        this.piirtoalue = piirtoalue;
        this.areena = areena;
        this.piirraEfektit = true;
    }

    /**
     * Hakee areenan efektiketjun aloitussolmun ja tallentaa sen.
     */
    public void haeEfektit() {
        this.efektit = areena.getEfektit();
    }

    /**
     * Joka kellon tickillä tapahtuva piirtokomento. Piirtää areenan ja efektit,
     * jonka jälkeen etenee efektiketjussa askeleen. Mikäli efektiketju loppuu,
     * pysäyttää ajastimen ja siten itsensä.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (efektit == null) {
            this.piirraEfektit = false;
        }

        StringBuilder kartta = new StringBuilder("");
        kartta.append("<html>");

        // Piirtää ylälaidan seinän
        for (int i = -1; i <= areena.getLeveys(); i++) {
            kartta.append("#");
        }
        kartta.append("<br>#");

        // Piirtää areenan ja sen olennot
        for (int y = 0; y < areena.getKorkeus(); y++) {
            if (y != 0) {
                kartta.append("#<br>#");
            }
            for (int x = 0; x < areena.getLeveys(); x++) {
                Koordinaatit ruutu = new Koordinaatit(x, y);

                if (piirraEfektit && voikoPiirtaaEfekteja(ruutu)) {
                    kartta.append(efektit);
                } else if (areena.onkoRuudussaHirviota(ruutu)) {
                    if (areena.getHirvioRuudusta(ruutu).getClass().equals(Lohikaarme.class)) {
                        kartta.append("<font color=ff3300>D</font>");
                    } else {
                        kartta.append("<font color=00ff00>h</font>");
                    }
                } else if (areena.getGladiaattori().getSijainti().equals(ruutu)) {
                    kartta.append("<font color=0000ff>@</font>");
                } else if (areena.onkoRuudussaEstetta(ruutu)) {
                    kartta.append('#');
                } else {
                    kartta.append('.');
                }
            }
        }

        kartta.append("#<br>");

        // Piirtää alalaidan seinän
        for (int i = -1; i <= areena.getLeveys(); i++) {
            kartta.append("#");
        }

        kartta.append("</html>");
        this.piirtoalue.setText(kartta.toString());
        this.piirtoalue.repaint();

        // Tarkistuksia
        if (!piirraEfektit) {
            ajastin.stop();
            piirraEfektit = true;
        } else {
            efektit = efektit.getSeuraava();
        }
    }

    /**
     * Tarkistaa, onko kyseisiin koordinaatteihin piirrettäviä efektejä.
     *
     * @param k koordinaatit
     * @return on/ei
     */
    private Boolean voikoPiirtaaEfekteja(Koordinaatit k) {
        if (this.efektit == null) {
            return false;
        } else if (this.efektit.piirretaanko(k)) {
            return true;
        }
        return false;
    }
}
