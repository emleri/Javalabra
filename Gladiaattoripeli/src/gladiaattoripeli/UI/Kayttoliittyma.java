
package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Sovelluslogiikka;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

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
        frame.setPreferredSize(new Dimension(800, 600));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);
        
        JLabel kartta = new JLabel();
        JLabel raportit = new JLabel();
        
        container.add(kartta, BorderLayout.CENTER);
        container.add(raportit, BorderLayout.SOUTH);
        
        this.piirtaja.setPiirtoalue(kartta);
        this.raportoija.setRaporttikentta(raportit);
        
        this.piirtaja.piirra(logiikka.getAreena());
        
        frame.addKeyListener(new NappaimistonKuuntelija(ohjaaja));
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
