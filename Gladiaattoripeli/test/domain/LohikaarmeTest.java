/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.domain.Lohikaarme;
import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.domain.Koordinaatit;
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
public class LohikaarmeTest {

    private Lohikaarme l;
    private Gladiaattori g;
    private Hirvio h;
    private Hahmogeneraattori hg;
    private Areena a;
    private Pelitilanne t;

    public LohikaarmeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hg = new Hahmogeneraattori();
        a = new Areena(25, 25, t);
        t = new Pelitilanne();
        l = (Lohikaarme) hg.luoLohikaarme();
        l.siirry(new Koordinaatit(10, 10));
        g = hg.luoGladiaattori(a);
        h = hg.luoHirvio();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void liikuTestTuliLento() {
        for (int i = 0; i < 9; i++) {
            l.liiku(g, t, a);

            switch (i) {
                case 0:
                    assertTrue(a.getEfektit().piirretaanko(g.getSijainti()));
                    for (int k = -1; k < 2; k++) {
                        for (int j = -1; j < 2; j++) {
                            assertTrue(a.getEfektit().piirretaanko(
                                    new Koordinaatit(g.getSijainti().getX() + k,
                                    g.getSijainti().getY() + j)));
                        }
                    }

                    break;
                case 1:
                    assertTrue(l.getSijainti().equals(new Koordinaatit(11, 11)));
                    break;
                case 7:
                    assertTrue(l.isIlmassa());
                    assertTrue(l.getSijainti().getX() > a.getLeveys()
                            && l.getSijainti().getY() > a.getKorkeus());

                    break;
                case 9:
                    assertTrue(!l.isIlmassa());
                    assertTrue(l.getSijainti().getX() > -1
                            && l.getSijainti().getX() < a.getLeveys()
                            && l.getSijainti().getY() > -1
                            && l.getSijainti().getY() < a.getKorkeus());
                    break;
                default:
                    break;
            }
        }
    }
}
