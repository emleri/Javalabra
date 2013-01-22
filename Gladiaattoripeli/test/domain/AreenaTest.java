package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Suunta;
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
    public void hirvionLisaysToimii() {
        List<Hirvio> hirviot = a.getHirviot();
        assertEquals(h, hirviot.get(0));
    }
    
    @Test
    public void hahmonHyokkaysHirvioonToimii() {
        a.toimiHahmollaSuuntaan(Suunta.POHJOINEN);
        assertEquals(5, h.getOsumaPisteet());
    }
    
    @Test
    public void hahmonLiikeToimii() {
        a.toimiHahmollaSuuntaan(Suunta.ETELA);
        assertEquals(14, a.getHahmo().getSijainti().getX());
        assertEquals(15, a.getHahmo().getSijainti().getY());
    }
    
    @Test
    public void liikutaHirvioitaToimiiHyokkayksena() {
        a.liikutaHirvioita();
        assertEquals(14, a.getHahmo().getOsumapisteet());
    }
    
    @Test
    public void liikutaHirvioitaToimiiLiikkeena() {
        a.liikutaHirvioita();
        assertEquals(1, a.getHirviot().get(1).getSijainti().getX());
        assertEquals(1, a.getHirviot().get(1).getSijainti().getY());
    }
    
    @Test
    public void onkoRuudussaHirvioitaToimii() {
        assertTrue(a.onkoRuudussaHirviota(new Koordinaatit(0, 0)));
        assertTrue(!a.onkoRuudussaHirviota(new Koordinaatit(4, 4)));
    }
    
    @Test
    public void haeHirvioRuudustaToimii() {
        Hirvio koeElain = a.haeHirvioRuudusta(new Koordinaatit(0, 0));
        assertEquals(5, koeElain.getOsumaPisteet());
        assertEquals(0, koeElain.getSijainti().getX());
        assertEquals(0, koeElain.getSijainti().getY());
    }
}
