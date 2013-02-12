
package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Sovelluslogiikka;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
import javax.swing.SwingUtilities;

/**
 * Käyttöliittymän ohjaaja on käyttöliittymän kokoava luokka, joka hallitsee eri osia ja pitää 
 * yhteyttä sovelluslogiikkaan.
 */
public class KayttoliittymanOhjaaja {
    private Sovelluslogiikka logiikka;
    private KartanPiirtaja piirtaja;
    private TapahtumanRaportoija raportoija;
    private Vuororaportti raportti;
    private Kayttoliittyma kayttoliittyma;
    private GraafinenKayttoliittyma graafinen;

    public KayttoliittymanOhjaaja() {
        this.logiikka = new Sovelluslogiikka();
        this.piirtaja = new KartanPiirtaja();
        this.raportoija = new TapahtumanRaportoija();
        this.kayttoliittyma = new Kayttoliittyma(logiikka, piirtaja, raportoija, this);
        this.graafinen = new GraafinenKayttoliittyma(this);
    }
    
    public void aloitaPeli() {
        this.logiikka.luoHirvioita(5);
        
        this.piirtaja.setPiirtoalue(graafinen.getKartta());
        this.raportoija.setRaporttikentta(graafinen.getRaporttikentta());
        this.raportoija.setStattikentta(graafinen.getStattikentta());
        
        this.graafinen.getJFrame().addKeyListener(new NappaimistonKuuntelija(this));
        this.piirtaja.piirra(logiikka.getAreena());
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                graafinen.setVisible(true);
            }
        });
       //invokeLateriin kayttoliittyma 
        
    }
    
    public void pelaajanLiike(Suunta s) {
        this.raportti = logiikka.pelaaVuoro(s);
        this.piirtaja.piirra(logiikka.getAreena());
        this.raportoija.raportoi(raportti);
    }
}
