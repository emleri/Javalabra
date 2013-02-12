
package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Sovelluslogiikka;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymäikkunan luova luokka. Ikkuna sisältää kaksi kenttää: kartta- ja 
 * tekstikentän, joista toiseen piirretään pelitilanne ja toiseen tulostetaan 
 * tekstimuotoisena tapahtumaraportteja.
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Sovelluslogiikka logiikka;
    private KartanPiirtaja piirtaja;
    private TapahtumanRaportoija raportoija;
    private KayttoliittymanOhjaaja ohjaaja;

    public Kayttoliittyma(Sovelluslogiikka s, KartanPiirtaja k, 
            TapahtumanRaportoija r, KayttoliittymanOhjaaja o) {
        this.logiikka = s;
        this.piirtaja = k;
        this.raportoija = r;
        this.ohjaaja = o;
    }
    

    @Override
    public void run() {
        frame = new JFrame("Gladiaattoripeli");
        frame.setPreferredSize(new Dimension(300, 800));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        BorderLayout layout = new BorderLayout(10, 10);
        container.setLayout(bagLayout);
        
        
        JLabel kartta = new JLabel();
        kartta.setMaximumSize(new Dimension(130, 300));
        JLabel raportit = new JLabel();
        raportit.setMaximumSize(new Dimension(130, 300));
        JLabel statit = new JLabel();
        statit.setMaximumSize(new Dimension(40, 300));
        c.gridx = 1;
        
        c.gridy = 0;
        container.add(raportit, c);
        c.gridx = 0;
        c.gridy = 1;
        container.add(statit, c);
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        container.add(kartta, c);
        
        
        this.piirtaja.setPiirtoalue(kartta);
        this.raportoija.setRaporttikentta(raportit);
        this.raportoija.setStattikentta(statit);
        
        this.piirtaja.piirra(logiikka.getAreena());
        
        frame.addKeyListener(new NappaimistonKuuntelija(ohjaaja));
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
