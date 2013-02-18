package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AreenaTest {

    Areena a;
    Pelitilanne t;
    Hirvio h;
    Hirvio h2;
    Hahmogeneraattori hg;
    int hirvioidenOsumapisteet;

    public AreenaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        t = new Pelitilanne();
        a = new Areena(28, 28, t);
        t.setHahmo(a.getHahmo());
        t.setHirviot(a.getHirviot());
        hg = new Hahmogeneraattori();
        h = hg.luoHirvio();
        h.siirry(new Koordinaatit(14, 13));
        h2 = hg.luoHirvio();
        h2.siirry(new Koordinaatit(0, 0));
        a.lisaaHirvio(h);
        a.lisaaHirvio(h2);
        a.luoHahmot();
        this.hirvioidenOsumapisteet = this.laskeHirvioidenOsumapisteet(a.getHirviot());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void areenanKonstruktoriLuoOikeanKokoisenAlueen() {
        assertEquals(28, a.getLeveys());
        assertEquals(28, a.getKorkeus());
    }

    @Test
    public void areenanKonstruktoriEiHyvaksyNegatiivisiaArvoja() {
    }

    @Test
    public void hirvionLisaysToimii() {
        List<Hirvio> hirviot = a.getHirviot();
        assertEquals(h, hirviot.get(0));
    }

    @Test
    public void hahmonHyokkaysHirvioonToimii() {
        for(int i = 0; i < 50; i++) {
            this.a.toimiHahmollaSuuntaan(Komennot.POHJOINEN);
        }
        for(int j = 0; j < 50; j++) {
            this.a.toimiHahmollaSuuntaan(Komennot.LANSI);
        }
        assertTrue(this.hirvioidenOsumapisteet > this.laskeHirvioidenOsumapisteet(this.a.getHirviot()));
    }

    @Test
    public void hahmonLiikeToimii() {
        a.toimiHahmollaSuuntaan(Komennot.ETELA);
        assertEquals(14, a.getHahmo().getSijainti().getX());
        assertEquals(15, a.getHahmo().getSijainti().getY());
    }

    @Test
    public void liikutaHirvioitaToimiiHyokkayksena() {
        for (int i = 0; i < 100; i++) {
            a.liikutaHirvioita();
        }
        assertTrue(a.getHahmo().getOsumapisteet() < 15);
    }

    @Test
    public void liikutaHirvioitaToimiiLiikkeena() {
        a.liikutaHirvioita();
        assertEquals(1, a.getHirviot().get(1).getSijainti().getX());
        assertEquals(1, a.getHirviot().get(1).getSijainti().getY());
    }

    @Test
    public void onkoRuudussaHirvioitaLoytaaHirvion() {
        assertTrue(a.onkoRuudussaHirviota(new Koordinaatit(0, 0)));
    }

    @Test
    public void onkoRuudussaHirviotaEiLoydaHirviota() {
        assertTrue(!a.onkoRuudussaHirviota(new Koordinaatit(4, 4)));
    }

    @Test
    public void haeHirvioRuudustaToimii() {
        Hirvio koeElain = a.getHirvioRuudusta(new Koordinaatit(0, 0));
        assertEquals(5, koeElain.getOsumapisteet());
        assertEquals(0, koeElain.getSijainti().getX());
        assertEquals(0, koeElain.getSijainti().getY());
    }
    
    public int laskeHirvioidenOsumapisteet(List<Hirvio> hirviot) {
        int x = 0;
        for (Hirvio hirmu : hirviot) {
            x += hirmu.getOsumapisteet();
        }
        return x;
    }
}
