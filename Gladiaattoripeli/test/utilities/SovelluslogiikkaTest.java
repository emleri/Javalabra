package utilities;

import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Sovelluslogiikka;
import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        Pelitilanne v = this.logiikka.pelaaVuoro(Komennot.ITA);
        assertEquals(false, v.onkoPeliOhi());
        assertEquals(11, v.getHahmo().getSijaintiX());
        assertTrue(!v.getTapahtumat().isEmpty());
    }
}
