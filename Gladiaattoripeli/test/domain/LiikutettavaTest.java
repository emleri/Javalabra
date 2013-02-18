package domain;


import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Pelitilanne;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LiikutettavaTest {
    Gladiaattori g;
    Hahmogeneraattori hg;

    public LiikutettavaTest() {
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
        g = hg.luoGladiaattori(new Areena(10, 10, new Pelitilanne()));
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaSijainninOikein() {
        assertEquals(5, g.getSijainti().getX());
        assertEquals(5, g.getSijainti().getX());
    }
    
    @Test
    public void siirryTest() {
        g.siirry(new Koordinaatit(5, 5));
        assertEquals(g.getSijainti().getX(), 5);
        assertEquals(g.getSijainti().getY(), 5);
    }
}
