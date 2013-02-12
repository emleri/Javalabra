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
public class HirvioTest {
    private Hahmogeneraattori hg;
    private Hirvio h;
    private Vuororaportti v;
    private Gladiaattori g;

    public HirvioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        h = new Hirvio(5, 5, 5);
        v = new Vuororaportti();
        hg = new Hahmogeneraattori();
        g = hg.luoGladiaattori(new Areena(5, 5));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void puolustaOsuma() {
        h.puolusta(v, 2, 100);
        assertTrue(h.getOsumapisteet() == 3);
    }
    
    @Test
    public void puolustaHuti() {
        h.puolusta(v, 2, 0);
        assertTrue(h.getOsumapisteet() == 5);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void puolustaVirheellisillaParametreilla() {
        h.puolusta(v, -5, 15);
    }
    
    @Test
    public void hyokkaaToimii() {
        for (int i = 0; i < 100; i++) {
            h.hyokkaa(g, v);
        }
        assertEquals(v.getTapahtumat().get(0), "Hirviö lyö gladiaattoria.");
        assertTrue(g.getOsumapisteet() < 15);
    }
}
