
package utilities;

import gladiaattoripeli.utilities.Pelitilanne;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Emleri
 */
public class PelitilanneTest {
    private Pelitilanne tilanne;
    
    public PelitilanneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tilanne = new Pelitilanne();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(tilanne.getTapahtumat() != null);
        assertTrue(!tilanne.isPeliOhi());
        assertTrue(tilanne.viestit != null);
    }
    
    @Test
    public void alustaTilanneTest() {
        tilanne.lisaaTapahtuma("asdf");
        tilanne.lisaaTapahtuma("ping pong");
        tilanne.lisaaTapahtuma("nöönöö");
        tilanne.alustaTilanne();
        assertTrue(tilanne.getTapahtumat().isEmpty());
        assertTrue(!tilanne.isPeliOhi());
    }
    
    @Test
    public void uusiVuoroTest() {
        tilanne.lisaaTapahtuma("Böö!");
        tilanne.lopetaPeli();
        tilanne.uusiVuoro();
        assertTrue(tilanne.getTapahtumat().isEmpty());
        assertTrue(tilanne.isPeliOhi());
    }
}
