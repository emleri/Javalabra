/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import gladiaattoripeli.utilities.Pisteet;
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
public class PisteetTest {
    private Pisteet p1;
    private Pisteet p2;
    private Pisteet p3;
    
    public PisteetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p1 = new Pisteet(5, "Avaruuskadetti Nuppineulapallo");
        p2 = new Pisteet(2, "Martti Ahtisaari");
        p3 = new Pisteet(2, "Jake the Dog");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void compareToTest() {
        assertEquals(p1.compareTo(p2), -1);
        assertEquals(p2.compareTo(p3), 0);
        assertEquals(p3.compareTo(p1), 1);
    }
    
    @Test
    public void setNimiTest() {
        p1.setNimi("!#¤(:::!");
        assertEquals(p1.getNimi(), "!#¤(...!");
    }
    
    @Test
    public void toStringTest() {
        assertEquals(p1.toString(), "5 pistettä, Avaruuskadetti Nuppineulapallo");
    }
}
