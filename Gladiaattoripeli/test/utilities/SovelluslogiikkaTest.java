package utilities;

import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.domain.Koordinaatit;
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
        int x = logiikka.getPelitilanne().getGladiaattori().getSijainti().getX();
        this.logiikka.pelaaVuoro(Komennot.ITA);
        assertEquals(false, logiikka.getPelitilanne().isPeliOhi());
        assertEquals(x + 1, logiikka.getPelitilanne().getGladiaattori().getSijainti().getX());
        assertTrue(!logiikka.getPelitilanne().getTapahtumat().isEmpty());
    }

    @Test
    public void pelaaVuoroPeliOhi() {
        int x = logiikka.getPelitilanne().getGladiaattori().getSijainti().getX();
        this.logiikka.getPelitilanne().lopetaPeli();
        this.logiikka.pelaaVuoro(Komennot.ITA);
        assertEquals(x, logiikka.getPelitilanne().getGladiaattori().getSijainti().getX());
        assertTrue(logiikka.getPelitilanne().getTapahtumat().isEmpty());
    }

    @Test
    public void annaPelaajalleKomentoTest() {
        int h = logiikka.getPelitilanne().getGladiaattori().getHyokkaysarvo();
        int p = logiikka.getPelitilanne().getGladiaattori().getPuolustusarvo();
        logiikka.annaPelaajalleKomento(Komennot.HYOKKAAVAMMIN);
        assertEquals(logiikka.getPelitilanne().getGladiaattori().getHyokkaysarvo(), h + 1);
        assertEquals(logiikka.getPelitilanne().getGladiaattori().getPuolustusarvo(), h - 1);
        
        logiikka.annaPelaajalleKomento(Komennot.PUOLUSTAVAMMIN);
        logiikka.annaPelaajalleKomento(Komennot.PUOLUSTAVAMMIN);
        assertEquals(logiikka.getPelitilanne().getGladiaattori().getHyokkaysarvo(), h - 1);
        assertEquals(logiikka.getPelitilanne().getGladiaattori().getPuolustusarvo(), h + 1);
    }
    
    @Test
    public void pelaaHirvioidenVuoroTest() {
        Koordinaatit k = logiikka.getAreena().getHirviot().get(0).getSijainti();
        logiikka.pelaaHirvioidenVuoro();
        assertEquals(false, logiikka.getPelitilanne().isPeliOhi());
        assertTrue(!logiikka.getPelitilanne().getTapahtumat().isEmpty());
        assertTrue(!k.equals(logiikka.getAreena().getHirviot().get(0).getSijainti()));
    }
}
