package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.Sovelluslogiikka;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Käyttöliittymän ohjaaja on käyttöliittymän kokoava luokka, joka hallitsee eri
 * osia ja pitää yhteyttä sovelluslogiikkaan.
 */
public class KayttoliittymanOhjaaja {

    private Sovelluslogiikka logiikka;
    private KartanPiirtaja piirtaja;
    private TapahtumanRaportoija raportoija;
    private Pelitilanne tilanne;
    private Kayttoliittyma kayttoliittyma;
    private GraafinenKayttoliittyma graafinen;

    public KayttoliittymanOhjaaja() {
        this.logiikka = new Sovelluslogiikka();
        this.piirtaja = new KartanPiirtaja();
        this.tilanne = logiikka.getPelitilanne();
        this.raportoija = new TapahtumanRaportoija();
        this.graafinen = new GraafinenKayttoliittyma(this);
    }

    /**
     * Metodi valmistelee pelitilanteen ja käynnistää käyttöliittymän.
     */
    public void aloitaPeli() {
        this.piirtaja.setPiirtoalue(graafinen.getKartta());
        this.raportoija.setRaporttikentta(graafinen.getRaporttikentta());
        this.raportoija.setStattikentta(graafinen.getStattikentta());

        this.graafinen.getJFrame().addKeyListener(new NappaimistonKuuntelija(this));
        this.piirtaja.piirra(logiikka.getAreena());
        this.piirtaja.piirra(logiikka.getAreena());
        this.raportoija.alkutervehdys(this.tilanne);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                graafinen.setVisible(true);
            }
        });
        //invokeLateriin kayttoliittyma 

    }

    /**
     * Metodi välittää NappaimistonKuuntelijalta saadun komennon eteenpäin
     * sovelluslogiikan suoritettavaksi ja päivittää näyttökentät.
     *
     * @param k parametrina eteenpäin välitettävä suunta, johon gladiaattorin
     * tulee toimia
     */
    public void pelaajanToiminto(Komennot k) {
        if (tilanne.onkoPeliOhi()) {
            this.kysyHighScorea();
        } else {
            if (k.onSuunta(k)) {
                logiikka.pelaaVuoro(k);
                this.paivita();
            } else if (k.equals(Komennot.ODOTA)) {
                logiikka.pelaaHirvioidenVuoro();
                this.paivita();
            }
        }
    }

    public void paivita() {
        this.piirtaja.piirra(logiikka.getAreena());
        this.piirtaja.piirra(logiikka.getAreena());
        this.raportoija.raportoi(this.tilanne);
    }

    public void kysyHighScorea() {
        // Dialogi-ikkuna kysymään nimeä high scoreen.
        String nimi = (String) JOptionPane.showInputDialog(
                null,
                "Pelaajan nimi:",
                "High score",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "nimi");
        List<String> pisteet = this.logiikka.tallennaHighScore(nimi);
        this.raportoija.tulostaPisteet(pisteet);
    }
}
