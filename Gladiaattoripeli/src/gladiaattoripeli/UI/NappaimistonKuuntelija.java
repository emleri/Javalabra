
package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Sovelluslogiikka;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NappaimistonKuuntelija implements KeyListener {
    private Sovelluslogiikka sovelluslogiikka;
    private Vuororaportti vuororaportti;

    public NappaimistonKuuntelija(Sovelluslogiikka sovelluslogiikka) {
        this.sovelluslogiikka = sovelluslogiikka;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.vuororaportti = sovelluslogiikka.pelaaVuoro(Suunta.LANSI);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.vuororaportti = sovelluslogiikka.pelaaVuoro(Suunta.ITA);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.vuororaportti = sovelluslogiikka.pelaaVuoro(Suunta.POHJOINEN);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.vuororaportti = sovelluslogiikka.pelaaVuoro(Suunta.ETELA);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
