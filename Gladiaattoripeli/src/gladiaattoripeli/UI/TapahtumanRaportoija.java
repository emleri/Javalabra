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

    private JLabel raporttiKentta; // Viite peli-ikkunan raporttikenttään
    private JLabel stattiKentta; // Viite peli-ikkunan stattiriviin

    /**
     * Konstruktori.
     */
    public TapahtumanRaportoija() {
    }

    public void setRaporttikentta(JLabel raporttiKentta) {
        this.raporttiKentta = raporttiKentta;
    }

    public void setStattikentta(JLabel stattiKentta) {
        this.stattiKentta = stattiKentta;
    }

    /**
     * Käy läpi pelitapahtumia lisäten niitä yksi kerrallaan tulostettavaan
     * tekstiin. Mikäli tekstin pituus nousee liian suureksi mahtuakseen
     * raporttikenttään, metodi tulostaa ensimmäisen osan ja muuttaa
     * käyttöliittymän tilan odottamaan seuraavaa osaa kunnes kaikki tapahtumat
     * on tulostettu.
     *
     * @param tilanne viite pelitilanteeseen, josta tapahtumat haetaan
     * @return mahduttiinko kaikki tulostamaan
     */
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
                lisaaTekstia = true;
                break;
            }
        }

        tapahtumat.removeAll(poistettavat);

        if (tilanne.isPeliOhi()) {
            raportit.append(tilanne.viestit.loppuviesti());
            this.tapot(tilanne.getGladiaattori(), raportit);
        }

        if (lisaaTekstia) {
            raportit.append("<br>[Lisää...]");
        }
        raportit.append("</html>");

        raporttiKentta.setText(raportit.toString());
        raporttiKentta.repaint();

        this.paivitaStatit(tilanne.getGladiaattori());
        return lisaaTekstia;
    }

    /**
     * Päivittää stattirivin gladiaattorin tietoja vastaavaksi.
     *
     * @param gladiaattori jolta statit haetaan
     */
    public void paivitaStatit(Gladiaattori gladiaattori) {
        stattiKentta.setText("HP: " + gladiaattori.getOsumapisteet() + "   " + "Hyökkäys: " + gladiaattori.getHyokkaysarvo() + "   " + "Puolustus: " + gladiaattori.getPuolustusarvo());
        stattiKentta.repaint();
    }

    /**
     * Tulostaa pelinalkuviestin.
     *
     * @param tilanne viite pelitilanteeseen, jonka viestigeneraattorilta viesti
     * haetaan.
     */
    public void alkutervehdys(Pelitilanne tilanne) {
        raporttiKentta.setText(tilanne.viestit.alkutervehdys());
        raporttiKentta.repaint();
    }

    /**
     * Gladiaattorin surmaamien hirviöiden määrän raportoiva metodi.
     *
     * @param gladiaattori jonka tapot tulostetaan
     * @param raportti teksti, jonka loppuun raportti lisätään
     */
    public void tapot(Gladiaattori gladiaattori, StringBuilder raportti) {
        List<Hahmo> tapot = gladiaattori.getTapot();

        raportti.append("<br><br>Ennen kuolemaansa gladiaattori surmasi ");
        raportti.append(tapot.size());
        raportti.append(" hirviötä.");
    }

    /**
     * Tulostaa high score -listan rivi riviltä. Lisää loppuun myös ohjeviestin
     * uuden pelin aloittamisesta, mikäli kyseessä on pelin lopussa tapahtuva
     * tulostus.
     *
     * @param pisteet pistelista
     * @param loppupisteet tulostetaanko ohjeviestiä
     */
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
