package gladiaattoripeli.UI;

import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.Pisteet;
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
    private GraafinenKayttoliittyma graafinen;
    private Tila tila;

    /**
     * Konstruktori.
     */
    public KayttoliittymanOhjaaja() {
        this.logiikka = new Sovelluslogiikka();
        this.piirtaja = new KartanPiirtaja();
        this.tilanne = logiikka.getPelitilanne();
        this.raportoija = new TapahtumanRaportoija();
        this.graafinen = new GraafinenKayttoliittyma(this);
        this.tila = Tila.PELI;
    }

    /**
     * Metodi valmistelee pelitilanteen ja käynnistää käyttöliittymän.
     */
    public void aloitaPeli() {
        this.piirtaja.setPiirtoalue(this.graafinen.getKartta());
        this.raportoija.setRaporttikentta(this.graafinen.getRaporttikentta());
        this.raportoija.setStattikentta(this.graafinen.getStattikentta());

        this.graafinen.getJFrame().addKeyListener(new NappaimistonKuuntelija(this));
        this.piirtaja.piirra(logiikka.getAreena());
        this.piirtaja.piirra(logiikka.getAreena());
        this.raportoija.paivitaStatit(logiikka.getAreena().getGladiaattori());
        this.raportoija.alkutervehdys(this.tilanne);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                graafinen.setVisible(true);
            }
        });
    }

    /**
     * Metodi välittää NappaimistonKuuntelijalta saadun komennon eteenpäin
     * sovelluslogiikan suoritettavaksi ja päivittää näyttökentät.
     *
     * @param k parametrina eteenpäin välitettävä komento, jonka mukaan
     * gladiaattorin tulee toimia
     */
    public void suoritaToiminto(Komennot k) {
        if (this.tila == Tila.PELI) {
            if (k.onSuunta(k)) {
                this.logiikka.pelaaVuoro(k);
                this.paivita();
            } else if (k.equals(Komennot.ODOTA)) {
                this.logiikka.pelaaHirvioidenVuoro();
                this.paivita();
            } else {
                this.logiikka.annaPelaajalleKomento(k);
                this.raportoija.paivitaStatit(this.logiikka.getAreena().getGladiaattori());
            }
        } else if (this.tila == Tila.LISAATEKSTIA) {
            this.paivita();
        } else if (this.tila == Tila.HIGHSCORE) {
            this.kysyHighScorea();
            this.tila = Tila.VALIKKO;
        } else if (this.tila == Tila.VALIKKO) {
            if (k == Komennot.HYVAKSY) {
                this.logiikka.uusiPeli();
                this.tila = Tila.PELI;
                this.piirtaja.piirra(this.logiikka.getAreena());
                this.raportoija.alkutervehdys(this.tilanne);
                this.raportoija.paivitaStatit(this.logiikka.getAreena().getGladiaattori());
            }
        }

        if (this.tilanne.isPeliOhi() && this.tila == Tila.PELI) {
            this.tila = Tila.HIGHSCORE;
        }

    }

    /**
     * Päivittää näyttökentät.
     */
    public void paivita() {
        this.piirtaja.piirra(logiikka.getAreena());
        this.piirtaja.piirra(logiikka.getAreena());
        boolean jatkuu = this.raportoija.raportoi(this.tilanne);
        if (jatkuu) {
            this.tila = Tila.LISAATEKSTIA;
        } else {
            this.tila = Tila.PELI;
        }
    }

    /**
     * Luo uuden ikkunan, jossa kysytään pelaajan nimeä syötettäväksi
     * pelipisteiden kanssa high scoreen. Lähettää sitten tuloksena saadut tiedot
     * sovelluslogiikalle tallennettavaksi.
     */
    public void kysyHighScorea() {
        String nimi = (String) JOptionPane.showInputDialog(
                null,
                "Pelaajan nimi:",
                "High score",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "nimi");
        List<Pisteet> pisteet = this.logiikka.tallennaHighScore(nimi);
        this.raportoija.tulostaPisteet(pisteet, true);
    }
}
