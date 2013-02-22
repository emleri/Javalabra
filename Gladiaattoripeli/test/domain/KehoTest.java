package domain;

import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Ruumiinosa;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.domain.RuumiinosanNimi;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KehoTest {

    public Keho keho;
    public Ruumiinosa osa;

    public KehoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.keho = new Keho("jee", 10);
        this.osa = new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, "jee", 4);
        this.keho.lisaaRaaja(osa);
        this.osa = new Ruumiinosa(RuumiinosanNimi.VASENKASI, "jee", 2);
        this.keho.lisaaRaaja(osa);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriTest() {
        assertEquals("pää, 2 osumapistettä\nrintakehä, 10 osumapistettä\n"
                + "oikea jalka, 4 osumapistettä\nvasen käsi, 2 osumapistettä",
                this.keho.toString());
    }

    @Test
    public void konstruktoriNimiTest() {
        assertEquals("jee", keho.getNimi());
        assertEquals("jee", keho.getKeskivartalo().getOmistajanNimi());
        assertEquals("jee", keho.getPaa().getOmistajanNimi());
        assertEquals("jee", keho.getRaajat().get(RuumiinosanNimi.OIKEAJALKA).getOmistajanNimi());
    }

    @Test
    public void otaVahinkoaTest() {
        keho.otaVahinkoa(new Pelitilanne(), 5);

        int pisteet = 0;
        for (Ruumiinosa raaja : keho.getRaajat().values()) {
            pisteet += raaja.getOsumapisteet();
        }
        pisteet += keho.getPaa().getOsumapisteet();
        pisteet += keho.getKeskivartalo().getOsumapisteet();

        assertEquals(pisteet, 13);
    }
}
