package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Hahmo;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.Pisteet;
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

    public void raportoi(Pelitilanne raportti) {
        List<String> tapahtumat = raportti.getTapahtumat();
        StringBuilder raportit = new StringBuilder("<html>");
        for (String s : tapahtumat) {
            raportit.append(s);
            raportit.append("<br>");
        }
        
        if (raportti.onkoPeliOhi()) {
            raportit.append(raportti.viestit.loppuviesti());
            this.tapot(raportti, raportit);
        }
        
        raportit.append("</html>");
        raporttiKentta.setText(raportit.toString());
        raporttiKentta.getParent().repaint();
        
        this.paivitaStatit(raportti);
    }
    
    public void paivitaStatit(Pelitilanne tilanne) {
        stattiKentta.setText("HP: " + tilanne.getHahmo().getOsumapisteet() +"   " + "Hyökkäys: " + tilanne.getHahmo().getHyokkaysarvo() + "   " + "Puolustus: " + tilanne.getHahmo().getPuolustusarvo());
        stattiKentta.getParent().repaint();
    }
    
    public void alkutervehdys(Pelitilanne v) {
        raporttiKentta.setText(v.viestit.alkutervehdys());
    }
    
    public void tapot(Pelitilanne v, StringBuilder raportti) {
        List<Hahmo> tapot = v.getHahmo().getTapot();
        
        raportti.append("<br><br>Ennen kuolemaansa gladiaattori surmasi ");
        raportti.append(tapot.size());
        raportti.append(" hirviötä.");
        
//        for (Hahmo h : tapot) {
//            raportti.append("<br>- ").append(h.toString());
//        }
    }

    public void tulostaPisteet(List<Pisteet> pisteet) {
        StringBuilder teksti = new StringBuilder("");
        teksti.append("<html>");
        
        for(int i=0; i < 10; i++) {
            if (pisteet.size() > i) {
                teksti.append(pisteet.get(i)).append("<br>");
            }
        }
        
        teksti.append("</html>");
        this.raporttiKentta.setText(teksti.toString());
        this.raporttiKentta.repaint();
    }
}
