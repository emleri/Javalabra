
package domain;

import gladiaattoripeli.domain.Keho;
import gladiaattoripeli.domain.Ruumiinosa;
import gladiaattoripeli.utilities.RuumiinosanNimi;
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
        this.keho = new Keho(10);
        this.osa = new Ruumiinosa(RuumiinosanNimi.OIKEAJALKA, 4);
        this.keho.lisaaRaaja(osa);
        this.osa = new Ruumiinosa(RuumiinosanNimi.VASENKASI, 2);
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
     
     
}
