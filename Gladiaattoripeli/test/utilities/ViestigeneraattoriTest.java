/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import gladiaattoripeli.utilities.Viestigeneraattori;
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
        assertTrue(vg.onHaavoittunut("Pöljä").contains("Pöljä on haavoittunut."));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void onHaavoittunutVirheellinenParametri() {
        vg.onHaavoittunut(null);
    }
    
    @Test
    public void onVammautunutToimii() {
        assertTrue(vg.onVammautunut("Pöljä").contains("Pöljä on vammautunut vakavasti."));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void onVammautunutVirheellinenParametri() {
        vg.onVammautunut(null);
    }
    
    @Test
    public void onKuollutToimii() {
        assertTrue(vg.onKuollut("Pöljä").contains("Pöljä kaatuu kuolleena maahan."));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void onKuollutVirheellinenParametri() {
        vg.onKuollut(null);
    }
    
    @Test
    public void vaistaaToimii() {
        assertTrue(vg.vaistaa("Pöljä").contains("Pöljä väistää hyökkäyksen."));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void vaistaaVirheellinenParametri() {
        vg.vaistaa(null);
    }
    
    @Test
    public void lyoToimii() {
        assertTrue(vg.lyo("Pöljä", "Pelleä").contains("Pöljä lyö Pelleä."));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void lyoVirheellinenParametri() {
        vg.lyo(null, "Pelle");
    }
}
