package domain;


import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Suunta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LiikutettavaTest {
    Gladiaattori kalle;

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
        kalle = new Gladiaattori(0, 0, 10, 10);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaSijainninOikein() {
        assertEquals(0, kalle.getSijaintiX());
        assertEquals(0, kalle.getSijaintiY());
    }

    @Test
    public void liikuSuuntaanToimii() {
        kalle.liikuSuuntaan(Suunta.ETELA);
        assertEquals(0, kalle.getSijaintiX());
        assertEquals(1, kalle.getSijaintiY());
    }
}
