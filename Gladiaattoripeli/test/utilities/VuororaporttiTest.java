
package utilities;

import gladiaattoripeli.utilities.Vuororaportti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emleri
 */
public class VuororaporttiTest {
    private Vuororaportti v;
    
    public VuororaporttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        v = new Vuororaportti();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(v.getTapahtumat() != null);
        assertTrue(v.getSurmatut() != null);
        assertTrue(!v.onkoPeliOhi());
        assertTrue(v.viestit != null);
    }
}
