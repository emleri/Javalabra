/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Hahmogeneraattori;
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
public class GladiaattoriTest {

    private Areena a;
    private Gladiaattori g;
    private Hirvio h;
    private Hahmogeneraattori hg;
    private Vuororaportti v;
    
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
        a = new Areena(10, 10);
        hg = new Hahmogeneraattori();
        g = hg.luoGladiaattori(a);
        h = new Hirvio(3, 3, 3);
        v = new Vuororaportti();
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
            g.hyokkaa(h, v);
        }
        assertEquals(v.getTapahtumat().get(0), "Gladiaattori lyö hirviötä.");
        assertTrue(h.getOsumapisteet() < 3);
    }
    
    @Test
    public void puolustaKunOsuu() {
        g.puolusta(v, 5, 20);
        assertTrue(g.getOsumapisteet() == 10);
    }
    
    @Test
    public void puolustaKunHuti() {
        g.puolusta(v, 5, 1); 
        assertTrue(g.getOsumapisteet() == 15);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void puolustaVirheellisillaParametreilla() {
        g.puolusta(v, -5, 15);
    }
}
