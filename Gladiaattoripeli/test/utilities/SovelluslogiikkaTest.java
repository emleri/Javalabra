package utilities;

import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Sovelluslogiikka;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SovelluslogiikkaTest {
    private Sovelluslogiikka logiikka;

    public SovelluslogiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.logiikka = new Sovelluslogiikka();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void pelaaVuoroTest() {
        this.logiikka.pelaaVuoro(Komennot.ITA);
        assertEquals(false, logiikka.getPelitilanne().onkoPeliOhi());
        assertEquals(11, logiikka.getPelitilanne().getGladiaattori().getSijainti().getX());
        assertTrue(!logiikka.getPelitilanne().getTapahtumat().isEmpty());
    }
}
