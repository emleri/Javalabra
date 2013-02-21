/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Efekti;
import gladiaattoripeli.utilities.Koordinaatit;
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
public class EfektiTest {

    private Efekti e;

    public EfektiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        e = new Efekti('x');
        e.lisaaPiirtokohta(new Koordinaatit(0, 0));
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void piirretaankoTest() {
        assertTrue(e.piirretaanko(new Koordinaatit(0, 0)));
        assertTrue(!e.piirretaanko(new Koordinaatit(0, 1)));
        assertTrue(!e.piirretaanko(new Koordinaatit(1, 0)));
        assertTrue(!e.piirretaanko(new Koordinaatit(5, 5)));
    }
    
    @Test
    public void lisaaSeuraavaFrameTest() {
        Efekti b = new Efekti('y');
        e.lisaaSeuraavaFrame(b);
        assertEquals(e.getSeuraava(), b);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void lisaaSeuraavaFrameNull() {
        e.lisaaSeuraavaFrame(null);
    }
    
    @Test
    public void getViimeinenKunUseampia() {
        e.lisaaSeuraavaFrame(new Efekti('r'));
        e.getSeuraava().lisaaSeuraavaFrame(new Efekti('z'));
        Efekti y = new Efekti('y');
        e.getSeuraava().getSeuraava().lisaaSeuraavaFrame(y);
        assertEquals(e.getViimeinen(), y);
    }
    
    @Test
    public void getViimeinenKunYksin() {
        assertEquals(e.getViimeinen(), e);
    }
}
