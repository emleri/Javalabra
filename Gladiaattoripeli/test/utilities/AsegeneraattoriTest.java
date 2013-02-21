/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import gladiaattoripeli.domain.Ase;
import gladiaattoripeli.utilities.Asegeneraattori;
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
public class AsegeneraattoriTest {

    private Asegeneraattori ag;

    public AsegeneraattoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ag = new Asegeneraattori();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAseTest() {
        this.tarkistaAse(ag.getMiekka(), 4, 0);
        this.tarkistaAse(ag.getNuija(), 1, 1);
        this.tarkistaAse(ag.getKynnet(), 2, 4);
    }

    private void tarkistaAse(Ase a, int x, int y) {
        assertEquals(a.getA(), x);
        assertEquals(a.getB(), y);
    }
}
