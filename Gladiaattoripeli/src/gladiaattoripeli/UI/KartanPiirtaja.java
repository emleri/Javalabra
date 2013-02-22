package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Areena;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Pelialueen piirtämistä hallitseva luokka. Käynnistää yksittäiset
 * Piirtaja-luokan piirto-operaatiot.
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
     * Käynnistää piirto-operaation ja asettaa piirtoruutujen väliseksi ajaksi
     * puoli sekuntia.
     *
     * BUGI: Kutsu tätä metodia kahdesti, tai se ei toimi oikein.
     *
     * @param a viite areenaan
     */
    public void piirra(Areena a) {
        this.ajastin = new Timer(500, this.piirtaja);
        this.ajastin.setInitialDelay(0);
        this.piirtaja = new Piirtaja(this.ajastin, this.piirtoalue, a);
        this.piirtaja.haeEfektit();
        ajastin.start();
    }
}
