/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Efekti;
import gladiaattoripeli.utilities.Koordinaatit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Emleri
 */
public class Piirtaja implements ActionListener {

    private Timer ajastin;
    private JLabel piirtoalue;
    private Areena areena;
    private Efekti efektit;
    Boolean piirraEfektit;

    public Piirtaja(Timer ajastin, JLabel piirtoalue, Areena areena) {
        this.ajastin = ajastin;
        this.piirtoalue = piirtoalue;
        this.areena = areena;
        this.piirraEfektit = true;
    }
    
    public void haeEfektit() {
        this.efektit = areena.getEfektit();
    }

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
                    kartta.append('h');
                } else if (areena.getHahmo().getSijainti().equals(ruutu)) {
                    kartta.append('@');
                } else {
                    kartta.append('_');
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
    
    private Boolean voikoPiirtaaEfekteja(Koordinaatit k) {
        if(this.efektit == null) {
            return false;
        } else if (this.efektit.piirretaanko(k)) {
            return true;
        }
        return false;
    }
}
