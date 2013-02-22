package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Komennot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Luokka lukee näppäinkomentoja ja välittää ne käyttöliittymän ohjaajan kautta
 * sovelluslogiikalle.
 */
public class NappaimistonKuuntelija implements KeyListener {

    private KayttoliittymanOhjaaja ohjaaja; // Viite käyttöliittymän ohjaajaan

    public NappaimistonKuuntelija(KayttoliittymanOhjaaja ohjaaja) {
        this.ohjaaja = ohjaaja;
    }

    /**
     * Lukee näppäinkomennon ja välittää sen KayttoliittymanOhjaajan kautta
     * Sovelluslogiikalle.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.ohjaaja.suoritaToiminto(Komennot.LANSI);
                break;
            case KeyEvent.VK_NUMPAD4:
                this.ohjaaja.suoritaToiminto(Komennot.LANSI);
                break;
            case KeyEvent.VK_RIGHT:
                this.ohjaaja.suoritaToiminto(Komennot.ITA);
                break;
            case KeyEvent.VK_NUMPAD6:
                this.ohjaaja.suoritaToiminto(Komennot.ITA);
                break;
            case KeyEvent.VK_UP:
                this.ohjaaja.suoritaToiminto(Komennot.POHJOINEN);
                break;
            case KeyEvent.VK_NUMPAD8:
                this.ohjaaja.suoritaToiminto(Komennot.POHJOINEN);
                break;
            case KeyEvent.VK_DOWN:
                this.ohjaaja.suoritaToiminto(Komennot.ETELA);
                break;
            case KeyEvent.VK_NUMPAD2:
                this.ohjaaja.suoritaToiminto(Komennot.ETELA);
                break;
            case KeyEvent.VK_NUMPAD1:
                this.ohjaaja.suoritaToiminto(Komennot.LOUNAS);
                break;
            case KeyEvent.VK_NUMPAD3:
                this.ohjaaja.suoritaToiminto(Komennot.KAAKKO);
                break;
            case KeyEvent.VK_NUMPAD7:
                this.ohjaaja.suoritaToiminto(Komennot.LUODE);
                break;
            case KeyEvent.VK_NUMPAD9:
                this.ohjaaja.suoritaToiminto(Komennot.KOILLINEN);
            case KeyEvent.VK_PERIOD:
                this.ohjaaja.suoritaToiminto(Komennot.ODOTA);
                break;
            case KeyEvent.VK_NUMPAD5:
                this.ohjaaja.suoritaToiminto(Komennot.ODOTA);
                break;
            case KeyEvent.VK_PLUS:
                this.ohjaaja.suoritaToiminto(Komennot.HYOKKAAVAMMIN);
                break;
            case KeyEvent.VK_ADD:
                this.ohjaaja.suoritaToiminto(Komennot.HYOKKAAVAMMIN);
                break;
            case KeyEvent.VK_SUBTRACT:
                this.ohjaaja.suoritaToiminto(Komennot.PUOLUSTAVAMMIN);
                break;
            case KeyEvent.VK_MINUS:
                this.ohjaaja.suoritaToiminto(Komennot.PUOLUSTAVAMMIN);
                break;
            case KeyEvent.VK_ENTER:
                this.ohjaaja.suoritaToiminto(Komennot.HYVAKSY);
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
