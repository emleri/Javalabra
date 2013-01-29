
package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Sovelluslogiikka;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
import javax.swing.SwingUtilities;

public class KayttoliittymanOhjaaja {
    private Sovelluslogiikka logiikka;
    private KartanPiirtaja piirtaja;
    private TapahtumanRaportoija raportoija;
    private Vuororaportti raportti;
    private Kayttoliittyma kayttoliittyma;

    public KayttoliittymanOhjaaja() {
        this.logiikka = new Sovelluslogiikka();
        this.piirtaja = new KartanPiirtaja();
        this.raportoija = new TapahtumanRaportoija();
        this.kayttoliittyma = new Kayttoliittyma(logiikka, piirtaja, raportoija, this);
    }
    
    public void aloitaPeli() {
        this.logiikka.luoHirvioita(100);
        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
    public void pelaajanLiike(Suunta s) {
        this.raportti = logiikka.pelaaVuoro(s);
        this.piirtaja.piirra(logiikka.getAreena());
        this.raportoija.raportoi(raportti);
    }
}
