/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import gladiaattoripeli.utilities.Viestigeneraattori;
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
public class ViestigeneraattoriTest {
    private Viestigeneraattori vg;
    
    public ViestigeneraattoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.vg = new Viestigeneraattori();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void onHaavoittunutToimii() {
        assertEquals("Pöljä on haavoittunut.", vg.onHaavoittunut("Pöljä"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void onHaavoittunutVirheellinenParametri() {
        vg.onHaavoittunut(null);
    }
    
    @Test
    public void onVammautunutToimii() {
        assertEquals("Pöljä on vammautunut vakavasti.", vg.onVammautunut("Pöljä"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void onVammautunutVirheellinenParametri() {
        vg.onVammautunut(null);
    }
    
    @Test
    public void onKuollutToimii() {
        assertEquals("Pöljä kaatuu kuolleena maahan.", vg.onKuollut("Pöljä"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void onKuollutVirheellinenParametri() {
        vg.onKuollut(null);
    }
    
    @Test
    public void vaistaaToimii() {
        assertEquals("Pöljä väistää hyökkäyksen.", vg.vaistaa("Pöljä"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void vaistaaVirheellinenParametri() {
        vg.vaistaa(null);
    }
    
    @Test
    public void lyoToimii() {
        assertEquals("Pöljä lyö Pelleä.", vg.lyo("Pöljä", "Pelleä"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void lyoVirheellinenParametri() {
        vg.lyo(null, "Pelle");
    }
}
