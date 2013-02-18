/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Ruumiinosa;
import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.utilities.Pelitilanne;
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
public class HahmogeneraattoriTest {
    private Hahmogeneraattori hg;
    
    public HahmogeneraattoriTest() {
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
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void ihmiskehoToimii() {
        Gladiaattori g = hg.luoGladiaattori(new Areena(15, 15, new Pelitilanne()));
        for (Ruumiinosa o : g.getKeho().getRaajat()) {
            assertTrue(tarkistaRuumiinosa(o));
        }
        assertTrue(tarkistaRuumiinosa(g.getKeho().getKeskivartalo()));
        assertTrue(tarkistaRuumiinosa(g.getKeho().getPaa()));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void ihmiskehoEiToimiVirheellisillaParameterilla() throws Exception {
        hg.luoKustomoituHirvio(null, -5);
    }
    
    public Boolean tarkistaRuumiinosa(Ruumiinosa o) {
        if (o.getOmistajanNimi().equals("Gladiaattori") &&
                o.getNimi() != null
                ) {
            return true;
        } else {
            return false;
        }
    }
}
