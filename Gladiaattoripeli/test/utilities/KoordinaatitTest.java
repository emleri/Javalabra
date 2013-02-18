package utilities;

import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Koordinaatit;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KoordinaatitTest {

    Koordinaatit k;
    Koordinaatit t;

    public KoordinaatitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        k = new Koordinaatit(5, 5);
        t = null;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getViereisetKoordinaatitSuunnassaToimiiYAkselilla() {
        t = k.getViereisetKoordinaatitSuunnassa(Komennot.ETELA);
        assertEquals(5, t.getX());
        assertEquals(6, t.getY());

        t = k.getViereisetKoordinaatitSuunnassa(Komennot.POHJOINEN);
        assertEquals(5, t.getX());
        assertEquals(4, t.getY());
    }

    @Test
    public void getViereisetKoordinaatitSuunnassaToimiiXAkselilla() {
        t = k.getViereisetKoordinaatitSuunnassa(Komennot.ITA);
        assertEquals(6, t.getX());
        assertEquals(5, t.getY());

        t = k.getViereisetKoordinaatitSuunnassa(Komennot.LANSI);
        assertEquals(4, t.getX());
        assertEquals(5, t.getY());
    }

    @Test
    public void getSatunnainenRuutuVieressaTest() {
        for (int i = 0; i < 20; i++) {
            t = k.getSatunnainenRuutuVieressa();
            assertTrue(k.onVieressa(t));
        }
    }

    @Test
    public void onVieressaEiVieressa() {
        t = new Koordinaatit(0, 0);
        assertTrue(!k.onVieressa(t));
    }

    @Test
    public void onVieressaItsellaan() {
        assertTrue(!k.onVieressa(k));
    }

    @Test
    public void onVieressaOsittain() {
        t = new Koordinaatit(4, 3);
        assertTrue(!k.onVieressa(t));

        t = new Koordinaatit(3, 4);
        assertTrue(!k.onVieressa(t));
    }

    @Test
    public void onVieressaTrue() {
        t = new Koordinaatit(4, 4);
        assertTrue(k.onVieressa(t));
        t = new Koordinaatit(5, 4);
        assertTrue(k.onVieressa(t));
        t = new Koordinaatit(4, 5);
        assertTrue(k.onVieressa(t));
        t = new Koordinaatit(6, 6);
        assertTrue(k.onVieressa(t));
    }

    @Test
    public void equalsTest() {
        t = new Koordinaatit(5, 5);
        assertTrue(k.equals(t));
        t = new Koordinaatit(4, 5);
        assertTrue(!k.equals(t));
        assertTrue(!k.equals(null));
        assertTrue(!k.equals(new ArrayList<String>()));
    }

    @Test
    public void koordinaattienSummaPositiivisilla() {
        t = k.koordinaattienSumma(new Koordinaatit(2, 1));
        assertEquals(t.getX(), 7);
        assertEquals(t.getY(), 6);
    }

    @Test
    public void koordinaattienSummaNegatiivisilla() {
        t = k.koordinaattienSumma(new Koordinaatit(-2, -1));
        assertEquals(t.getX(), 3);
        assertEquals(t.getY(), 4);

        t = k.koordinaattienSumma(new Koordinaatit(-7, -8));
        assertEquals(t.getX(), -2);
        assertEquals(t.getY(), -3);
    }
    
    @Test
    public void koordinaattienSummaNollalla() {
        t = k.koordinaattienSumma(new Koordinaatit(0, 0));
        assertEquals(t.getX(), 5);
        assertEquals(t.getY(), 5);
    }
    
    @Test
    public void lisaaKoordinaatitPositiivisilla() {
        k.lisaaKoordinaatit(new Koordinaatit(2,1));
        assertEquals(k.getX(), 7);
        assertEquals(k.getY(), 6);
    }
    
    @Test
    public void lisaaKoordinaatitNegatiivisilla() {
        k.lisaaKoordinaatit(new Koordinaatit(-2, -1));
        assertEquals(k.getX(), 3);
        assertEquals(k.getY(), 4);
    }
    
    @Test
    public void lisaaKoordinaatitNollalla() {
        k.lisaaKoordinaatit(new Koordinaatit(0, 0));
        assertEquals(k.getX(), 5);
        assertEquals(k.getY(), 5);
    }
    
    @Test
    public void getViereinenRuutuKohtiKoordinaattejaTest() {
        int tavoiteX;
        int tavoiteY;
        for (int x = 2; x < 8; x++) {
            for (int y = 2; y < 8; y++) {
                t = k.getViereinenRuutuKohtiKoordinaatteja(new Koordinaatit(x, y));
                tavoiteX = k.getX();
                tavoiteX = (x < k.getX()) ? k.getX() - 1 : tavoiteX;
                tavoiteX = (x > k.getX()) ? k.getX() + 1 : tavoiteX;
                tavoiteY = k.getY();
                tavoiteY = (y < k.getY()) ? k.getY() - 1 : tavoiteY;
                tavoiteY = (y > k.getY()) ? k.getY() + 1 : tavoiteY;
                assertEquals(t.getX(), tavoiteX);
                assertEquals(t.getY(), tavoiteY);
            }
        }
    }
}
