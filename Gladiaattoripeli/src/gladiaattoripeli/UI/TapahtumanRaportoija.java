package gladiaattoripeli.UI;

import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hahmo;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.Pisteet;
import java.util.ArrayList;
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

    public boolean raportoi(Pelitilanne tilanne) {
        int riveja = 0;
        boolean lisaaTekstia = false;
        List<String> tapahtumat = tilanne.getTapahtumat();
        List<String> poistettavat = new ArrayList<String>();
        StringBuilder raportit = new StringBuilder("<html>");
        for (int i = 0; i < tapahtumat.size(); i++) {
            raportit.append(tapahtumat.get(i));
            poistettavat.add(tapahtumat.get(i));
            raportit.append("<br>");
            riveja++;
            if (riveja > 9 && i < tapahtumat.size() - 1) {
                break;
            }
        }

        tapahtumat.removeAll(poistettavat);

        if (tilanne.isPeliOhi()) {
            raportit.append(tilanne.viestit.loppuviesti());
            this.tapot(tilanne.getGladiaattori(), raportit);
        }

        if (riveja > 9) {
            raportit.append("<br>[Lisää...]");
            lisaaTekstia = true;
        }

        raportit.append("</html>");
        raporttiKentta.setText(raportit.toString());
        raporttiKentta.repaint();

        this.paivitaStatit(tilanne.getGladiaattori());
        return lisaaTekstia;
    }

    public void paivitaStatit(Gladiaattori gladiaattori) {
        stattiKentta.setText("HP: " + gladiaattori.getOsumapisteet() + "   " + "Hyökkäys: " + gladiaattori.getHyokkaysarvo() + "   " + "Puolustus: " + gladiaattori.getPuolustusarvo());
        stattiKentta.repaint();
    }

    public void alkutervehdys(Pelitilanne v) {
        raporttiKentta.setText(v.viestit.alkutervehdys());
    }

    public void tapot(Gladiaattori gladiaattori, StringBuilder raportti) {
        List<Hahmo> tapot = gladiaattori.getTapot();

        raportti.append("<br><br>Ennen kuolemaansa gladiaattori surmasi ");
        raportti.append(tapot.size());
        raportti.append(" hirviötä.");

//        for (Hahmo h : tapot) {
//            raportti.append("<br>- ").append(h.toString());
//        }
    }

    public void tulostaPisteet(List<Pisteet> pisteet, Boolean loppupisteet) {
        StringBuilder teksti = new StringBuilder("");
        teksti.append("<html>");

        for (int i = 0; i < 10; i++) {
            if (pisteet.size() > i) {
                teksti.append(pisteet.get(i)).append("<br>");
            }
        }

        if (loppupisteet) {
            teksti.append("<br>Aloita uusi peli painamalla [Enter]");
        }

        teksti.append("</html>");
        this.raporttiKentta.setText(teksti.toString());
        this.raporttiKentta.repaint();


    }
}
