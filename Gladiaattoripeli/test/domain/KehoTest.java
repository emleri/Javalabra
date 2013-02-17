
package domain;

import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Ruumiinosa;
import gladiaattoripeli.utilities.RuumiinosanNimi;
import gladiaattoripeli.utilities.Pelitilanne;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
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
         assertEquals("jee", keho.getRaajat().get(0).getOmistajanNimi());
     }
     
     @Test
     public void otaVahinkoaTest() {
         keho.otaVahinkoa(new Pelitilanne(), 5);
         
         int pisteet = 0;
         for (Ruumiinosa raaja : keho.getRaajat()) {
             pisteet += raaja.getOsumapisteet();
         }
         pisteet += keho.getPaa().getOsumapisteet();
         pisteet += keho.getKeskivartalo().getOsumapisteet();
         
         assertEquals(pisteet, 13);
     }
}
