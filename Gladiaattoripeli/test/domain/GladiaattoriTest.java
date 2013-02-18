/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Hahmogeneraattori;
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
public class GladiaattoriTest {

    private Areena a;
    private Pelitilanne t;
    private Gladiaattori g;
    private Hirvio h;
    private Hahmogeneraattori hg;
    
    public GladiaattoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new Pelitilanne();
        a = new Areena(10, 10, t);
        t.setHahmo(a.getHahmo());
        t.setHirviot(a.getHirviot());
        hg = new Hahmogeneraattori();
        g = hg.luoGladiaattori(a);
        h = hg.luoHirvio();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void hyokkaaToimii() {
        for (int i = 0; i < 100; i++) {
            g.hyokkaa(h, t);
        }
        assertEquals(t.getTapahtumat().get(0), "Gladiaattori lyö hirviötä.");
        assertTrue(h.getOsumapisteet() < 3);
    }
    
    @Test
    public void puolustaKunOsuu() {
        g.puolusta(t, 5, 20);
        assertTrue(g.getOsumapisteet() == 10);
    }
    
    @Test
    public void puolustaKunHuti() {
        g.puolusta(t, 5, 1); 
        assertTrue(g.getOsumapisteet() == 15);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void puolustaVirheellisillaParametreilla() {
        g.puolusta(t, -5, 15);
    }
}
