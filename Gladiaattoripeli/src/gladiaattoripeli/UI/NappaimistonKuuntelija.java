package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Komennot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Luokka lukee näppäinkomentoja ja välittää ne käyttöliittymän ohjaajan kautta 
 * sovelluslogiikalle.
 */
public class NappaimistonKuuntelija implements KeyListener {

    private KayttoliittymanOhjaaja ohjaaja;

    public NappaimistonKuuntelija(KayttoliittymanOhjaaja ohjaaja) {
        this.ohjaaja = ohjaaja;
    }

    /**
     * Lukee näppäinkomentoja ja välittää ne KayttoliittymanOhjaajan kautta 
     * Sovelluslogiikalle.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // kokeilua
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT: 
                this.ohjaaja.pelaajanToiminto(Komennot.LANSI);
                break;
            case KeyEvent.VK_RIGHT:
                this.ohjaaja.pelaajanToiminto(Komennot.ITA);
                break;
            case KeyEvent.VK_UP:
                this.ohjaaja.pelaajanToiminto(Komennot.POHJOINEN);
                break;
            case KeyEvent.VK_DOWN:
                this.ohjaaja.pelaajanToiminto(Komennot.ETELA);
                break;
            case KeyEvent.VK_PERIOD:
                this.ohjaaja.pelaajanToiminto(Komennot.ODOTA);
                break;
            case KeyEvent.VK_PLUS:
                this.ohjaaja.pelaajanToiminto(Komennot.HYOKKAAVAMMIN);
                break;
            case KeyEvent.VK_MINUS:
                this.ohjaaja.pelaajanToiminto(Komennot.PUOLUSTAVAMMIN);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
