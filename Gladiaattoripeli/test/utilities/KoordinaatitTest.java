
package utilities;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Suunta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KoordinaatitTest {
    Koordinaatit k;
    
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
        k = new Koordinaatit(5, 5, 6, 6);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void setXMaxXToimii() {
        k.setX(8);
        assertEquals(6, k.getX());
    }
    
    @Test
    public void setXToimiiRajaArvollaAlas() {
        k.setX(0);
        assertEquals(0, k.getX());
    }
    
    @Test
    public void setXToimiiRajaArvollaYlos() {
        k.setX(6);
        assertEquals(6, k.getX());
    }
    
    @Test
    public void setXMinXToimii() {
        k.setX(-4);
        assertEquals(0, k.getX());
    }
    
    @Test
    public void setYMaxYToimii() {
        k.setY(8);
        assertEquals(6, k.getY());
    }
    
    @Test
    public void setYToimiiRajaArvollaAlas() {
        k.setY(0);
        assertEquals(0, k.getY());
    }
    
    @Test
    public void setYToimiiRajaArvollaYlos() {
        k.setY(6);
        assertEquals(6, k.getY());
    }
    
    @Test
    public void setYMinYToimii() {
        k.setY(-4);
        assertEquals(0, k.getY());
    }
    
    @Test
    public void getViereisetKoordinaatitSuunnassaToimiiYAkselilla() {
        Koordinaatit viereiset = k.getViereisetKoordinaatitSuunnassa(Suunta.ETELA);
        assertEquals(5, viereiset.getX());
        assertEquals(6, viereiset.getY());
        
        viereiset = k.getViereisetKoordinaatitSuunnassa(Suunta.POHJOINEN);
        assertEquals(5, viereiset.getX());
        assertEquals(4, viereiset.getY());
    }
    
    @Test
    public void getViereisetKoordinaatitSuunnassaToimiiXAkselilla() {
        Koordinaatit viereiset = k.getViereisetKoordinaatitSuunnassa(Suunta.ITA);
        assertEquals(6, viereiset.getX());
        assertEquals(5, viereiset.getY());
        
        viereiset = k.getViereisetKoordinaatitSuunnassa(Suunta.LANSI);
        assertEquals(4, viereiset.getX());
        assertEquals(5, viereiset.getY());
    }
}
