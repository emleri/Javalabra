/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Ase;
import gladiaattoripeli.domain.Efekti;
import gladiaattoripeli.domain.Koordinaatit;
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
public class AseTest {

    private Ase ase;

    public AseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void virheellinenKonstruktorisyote() {
        this.ase = new Ase(-5, 0);
    }
    
    @Test
    public void hyokkaysEfektiTest() {
        this.ase = new Ase(2, 2);
        Efekti e = this.ase.getHyokkaysefekti(new Koordinaatit(0,0));
        assertTrue(e != null);
        assertEquals(e.toString(), "<font color=ff3300>*</font>");
        assertTrue(e.piirretaanko(new Koordinaatit(0, 0)));
    }
}
