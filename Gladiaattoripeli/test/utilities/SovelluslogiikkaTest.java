package utilities;

import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Sovelluslogiikka;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
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
    public void luoHirvioitaTest() {
        this.logiikka.luoHirvioita(23);
        List<Hirvio> hirviot = this.logiikka.getAreena().getHirviot();
        
        assertEquals(0, hirviot.get(hirviot.size()-1).getSijaintiX());
        assertEquals(1, hirviot.get(hirviot.size()-1).getSijaintiY());
        
        assertEquals(6, hirviot.get(6).getSijaintiX());
        assertEquals(0, hirviot.get(6).getSijaintiY());
    }
    
    @Test
    public void pelaaVuoroTest() {
        Vuororaportti v = this.logiikka.pelaaVuoro(Suunta.ITA);
        assertEquals(false, v.peliOhi());
        assertEquals(11, v.getHahmo().getSijaintiX());
        assertTrue(!v.getTapahtumat().isEmpty());
    }
}
