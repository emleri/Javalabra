/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import gladiaattoripeli.domain.Ruumiinosa;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.RuumiinosanNimi;
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
public class RuumiinosaTest {

    private Ruumiinosa osa;
    private Pelitilanne tilanne;

    public RuumiinosaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        osa = new Ruumiinosa(RuumiinosanNimi.HANTA, "örkki", 5);
        tilanne = new Pelitilanne();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void otaVahinkoaTest1() {
        int pisteetEnnen = osa.getOsumapisteet();
        osa.otaVahinkoa(tilanne, 3);
        assertEquals(osa.getOsumapisteet(), pisteetEnnen - 3);
        assertTrue(tilanne.getTapahtumat().get(0).contains("häntä"));
    }
    
    @Test
    public void otaVahinkoaTest2() {
        osa = new Ruumiinosa(RuumiinosanNimi.VASENJALKA, "käärme", 5);
        int pisteetEnnen = osa.getOsumapisteet();
        osa.otaVahinkoa(tilanne, 6);
        assertEquals(osa.getOsumapisteet(), pisteetEnnen -6);
        assertTrue(tilanne.getTapahtumat().get(0).contains("käärmeen"));
        assertTrue(osa.isKuollut());
    }
    
    @Test
    public void toStringTest() {
        assertEquals(osa.toString(), "häntä, 5 osumapistettä");
    }
}
