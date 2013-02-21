/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.utilities.Koordinaatit;
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
public class HirvioTest {

    private Hahmogeneraattori hg;
    private Hirvio h;
    private Pelitilanne t;
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
        hg = new Hahmogeneraattori();
        h = hg.luoHirvio();
        t = new Pelitilanne();
        g = hg.luoGladiaattori(new Areena(5, 5, t));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void puolustaOsuma() {
        int x = h.getOsumapisteet();
        h.puolusta(t, 2, h.getPuolustusarvo() + 5);
        assertTrue(h.getOsumapisteet() == x - 2);
    }

    @Test
    public void puolustaHuti() {
        int x = h.getOsumapisteet();
        h.puolusta(t, 2, 1);
        assertEquals(h.getOsumapisteet(), x);
    }

    @Test(expected = IllegalArgumentException.class)
    public void puolustaVirheellisillaParametreilla() {
        h.puolusta(t, -5, 15);
    }

    @Test
    public void puolustaRajaArvolla() {
        int x = h.getOsumapisteet();
        h.puolusta(t, 2, h.getPuolustusarvo() + 1);
        assertEquals(h.getOsumapisteet(), x - 2);
        h.puolusta(t, 2, h.getPuolustusarvo());
        assertEquals(h.getOsumapisteet(), x - 2);
    }

    @Test
    public void hyokkaaToimii() {
        for (int i = 0; i < 100; i++) {
            h.hyokkaa(g, t);
        }
        assertEquals(t.getTapahtumat().get(0), "Hirviö lyö gladiaattoria.");
        assertTrue(g.getOsumapisteet() < 15);
    }

    @Test
    public void liikuKunTilaa() {
        Areena a = new Areena(10, 10, t);
        h.siirry(new Koordinaatit(0, 0));
        a.lisaaHirvio(h);
        a.luoHahmot();
        h.liiku(g, t, a);
        assertEquals(h.getSijainti().getX(), 1);
        assertEquals(h.getSijainti().getY(), 1);


    }

    @Test
    public void liikuKunGladiaattorinVieressa() {
        Areena a = new Areena(25, 25, t);
        h.siirry(new Koordinaatit(0, 0));
        a.lisaaHirvio(h);
        a.luoHahmot();
        int x = g.getOsumapisteet();
        for (int i = 0; i < 200; i++) {
            h.liiku(g, t, a);
        }
        assertTrue(g.getOsumapisteet() < x);
    }

    @Test
    public void liikuKunEiTilaa() {
        Areena a = new Areena(10, 10, t);
        Hirvio h1 = new Hirvio(5, new Keho("öö", 1));
        h1.siirry(new Koordinaatit(0, 1));
        Hirvio h2 = new Hirvio(5, new Keho("ää", 1));
        h2.siirry(new Koordinaatit(1, 1));
        Hirvio h3 = new Hirvio(5, new Keho("aa", 1));
        h3.siirry(new Koordinaatit(1, 0));
        Hirvio h4 = new Hirvio(5, new Keho("aa", 1));
        h4.siirry(new Koordinaatit(0, -1));
        Hirvio h5 = new Hirvio(5, new Keho("aa", 1));
        h5.siirry(new Koordinaatit(-1, -1));
        Hirvio h6 = new Hirvio(5, new Keho("aa", 1));
        h6.siirry(new Koordinaatit(-1, 0));
        Hirvio h7 = new Hirvio(5, new Keho("aa", 1));
        h7.siirry(new Koordinaatit(-1, 1));
        Hirvio h8 = new Hirvio(5, new Keho("aa", 1));
        h8.siirry(new Koordinaatit(1, -1));
        h.siirry(new Koordinaatit(0, 0));
        a.lisaaHirvio(h);
        a.lisaaHirvio(h1);
        a.lisaaHirvio(h2);
        a.lisaaHirvio(h3);
        a.lisaaHirvio(h4);
        a.lisaaHirvio(h5);
        a.lisaaHirvio(h6);
        a.lisaaHirvio(h7);
        a.lisaaHirvio(h8);
        a.luoHahmot();

        h.liiku(g, t, a);
        assertTrue(h.getSijainti().getX() == 0 && h.getSijainti().getY() == 0);
    }
}
