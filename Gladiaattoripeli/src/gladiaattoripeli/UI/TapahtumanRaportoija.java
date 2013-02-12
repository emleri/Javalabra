package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Vuororaportti;
import java.util.List;
import javax.swing.JLabel;

/**
 * Pelitapahtumat tekstinä tulostava luokka. Purkaa vuororaportista saadut 
 * tapahtumat ja käskee käyttöliittymäikkunaa tulostamaan ne.
 */
public class TapahtumanRaportoija {

    private JLabel raporttiKentta;
    private JLabel stattiKentta;

    public TapahtumanRaportoija() {
    }

    public void setRaporttikentta(JLabel raporttiKentta) {
        this.raporttiKentta = raporttiKentta;
    }
    
    public void setStattikentta(JLabel stattiKentta) {
        this.stattiKentta = stattiKentta;
    }

    public void raportoi(Vuororaportti raportti) {
        List<String> tapahtumat = raportti.getTapahtumat();
        StringBuilder raportit = new StringBuilder("<html>");
        for (String s : tapahtumat) {
            raportit.append(s);
            raportit.append("<br>");
        }
        raportit.append("</html>");
        raporttiKentta.setText(raportit.toString());
        raporttiKentta.getParent().repaint();
        
        this.paivitaStatit(raportti);
    }
    
    public void paivitaStatit(Vuororaportti raportti) {
        stattiKentta.setText("HP: " + raportti.getHahmo().getOsumapisteet());
        stattiKentta.getParent().repaint();
    }
}
