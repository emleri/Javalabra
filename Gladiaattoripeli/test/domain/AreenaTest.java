package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AreenaTest {

    Areena a;
    Hirvio h;
    Hirvio h2;

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
        a = new Areena(28, 28);
        h = new Hirvio(14, 13, 10);
        h2 = new Hirvio(0, 0, 5);
        a.lisaaHirvio(h);
        a.lisaaHirvio(h2);
        a.luoHahmot();
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
        for(int i = 0; i < 100; i++) {
        a.toimiHahmollaSuuntaan(Suunta.POHJOINEN, new Vuororaportti());
        }
        assertTrue(5 > h.getOsumapisteet());
    }

    @Test
    public void hahmonLiikeToimii() {
        a.toimiHahmollaSuuntaan(Suunta.ETELA, new Vuororaportti());
        assertEquals(14, a.getHahmo().getSijainti().getX());
        assertEquals(15, a.getHahmo().getSijainti().getY());
    }

    @Test
    public void liikutaHirvioitaToimiiHyokkayksena() {
        for (int i = 0; i < 100; i++) {
            a.liikutaHirvioita(new Vuororaportti());
        }
        assertTrue(a.getHahmo().getOsumapisteet()<15);
    }

    @Test
    public void liikutaHirvioitaToimiiLiikkeena() {
        a.liikutaHirvioita(new Vuororaportti());
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
        Hirvio koeElain = a.haeHirvioRuudusta(new Koordinaatit(0, 0));
        assertEquals(5, koeElain.getOsumapisteet());
        assertEquals(0, koeElain.getSijainti().getX());
        assertEquals(0, koeElain.getSijainti().getY());
    }
}
