package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Suunta;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NappaimistonKuuntelija implements KeyListener {

    private KayttoliittymanOhjaaja ohjaaja;

    public NappaimistonKuuntelija(KayttoliittymanOhjaaja ohjaaja) {
        this.ohjaaja = ohjaaja;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.ohjaaja.pelaajanLiike(Suunta.LANSI);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.ohjaaja.pelaajanLiike(Suunta.ITA);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.ohjaaja.pelaajanLiike(Suunta.POHJOINEN);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.ohjaaja.pelaajanLiike(Suunta.ETELA);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
