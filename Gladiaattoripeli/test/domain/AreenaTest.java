package domain;

import gladiaattoripeli.domain.Areena;
import gladiaattoripeli.domain.Efekti;
import gladiaattoripeli.domain.Hahmo;
import gladiaattoripeli.domain.Hirvio;
import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AreenaTest {

    Areena a;
    Pelitilanne t;
    Hirvio h;
    Hirvio h2;
    Hahmogeneraattori hg;
    int hirvioidenOsumapisteet;

    public AreenaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        t = new Pelitilanne();
        a = new Areena(28, 28, t);
        t.setGladiaattori(a.getGladiaattori());
        hg = new Hahmogeneraattori();
        h = hg.luoHirvio();
        h.siirry(new Koordinaatit(14, 13));
        h2 = hg.luoHirvio();
        h2.siirry(new Koordinaatit(0, 0));
        a.lisaaHirvio(h);
        a.lisaaHirvio(h2);
        a.luoHahmot();
        this.hirvioidenOsumapisteet = this.laskeHirvioidenOsumapisteet(a.getHirviot());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void areenanKonstruktoriLuoOikeanKokoisenAlueen() {
        assertEquals(28, a.getLeveys());
        assertEquals(28, a.getKorkeus());
    }

    @Test
    public void areenanKonstruktoriEiHyvaksyNegatiivisiaArvoja() {
        a = new Areena(-5, -5, t);
        assertEquals(a.getLeveys(), 1);
        assertEquals(a.getKorkeus(), 1);
    }

    @Test
    public void luoHahmotTest() {
        assertTrue(a.getGladiaattori() != null);
        assertEquals(a.getHirviot().size(), 5);
    }

    @Test
    public void luoEsteetTest() {
        a.luoEsteet();
        Koordinaatit este = a.getEsteet().get(0);
        assertEquals(este.getX(), 7);
        assertEquals(este.getY(), 7);
        este = a.getEsteet().get(1);
        assertEquals(este.getX(), 7);
        assertEquals(este.getY(), 21);
        este = a.getEsteet().get(2);
        assertEquals(este.getX(), 21);
        assertEquals(este.getY(), 7);
        este = a.getEsteet().get(3);
        assertEquals(este.getX(), 21);
        assertEquals(este.getY(), 21);
        este = a.getEsteet().get(4);
        assertEquals(este.getX(), 7);
        assertEquals(este.getY(), 14);
        este = a.getEsteet().get(5);
        assertEquals(este.getX(), 21);
        assertEquals(este.getY(), 14);
        este = a.getEsteet().get(6);
        assertEquals(este.getX(), 14);
        assertEquals(este.getY(), 7);
        este = a.getEsteet().get(7);
        assertEquals(este.getX(), 14);
        assertEquals(este.getY(), 21);
    }

    @Test
    public void luoEsteetLiianPienellaAreenalla() {
        a = new Areena(2, 2, t);
        a.luoEsteet();
        assertTrue(a.getEsteet().isEmpty());
    }

    @Test
    public void luoEsteetMinimikokoisellaAreenalla() {
        a = new Areena(7, 7, t);
        a.luoEsteet();
        assertEquals(a.getEsteet().size(), 8);
    }
    
    @Test
    public void onkoRuudussaEstettaTest() {
        a.luoEsteet();
        assertTrue(a.onkoRuudussaEstetta(new Koordinaatit(7,7)));
    }

    @Test
    public void hirvionLisaysToimii() {
        List<Hirvio> hirviot = a.getHirviot();
        assertEquals(h, hirviot.get(0));
    }
    
    @Test
    public void seuraavaAaltoTest() {
        a = new Areena(5, 5, t);
        a.seuraavaAalto();
        assertEquals(a.getHirviot().size(), 3);
    }
    
    @Test
    public void arvoTyhjaSijaintiLaidaltaTest() {
        Boolean test = true;
        for (int i = 0; i < 100; i++) {
             a = new Areena(10, 10, t);
             a.luoHahmot();
             for(Hahmo hah : a.getHirviot()) {
                 if(hah.getSijainti().getX() != 0 
                         && hah.getSijainti().getX() != a.getLeveys() - 1 
                         && (hah.getSijainti().getY() != 0) 
                         &&(hah.getSijainti().getY() != a.getKorkeus() - 1)) {
                     test = false;
                 }
             }
             assertTrue(test);
        }
    }
    
    @Test
    public void liikutaHirvioitaToimiiHyokkayksena() {
        for (int i = 0; i < 100; i++) {
            a.liikutaHirvioita();
        }
        assertTrue(a.getGladiaattori().getOsumapisteet() < 15);
    }

    @Test
    public void liikutaHirvioitaToimiiLiikkeena() {
        a.liikutaHirvioita();
        assertEquals(1, a.getHirviot().get(1).getSijainti().getX());
        assertEquals(1, a.getHirviot().get(1).getSijainti().getY());
    }

    @Test
    public void hahmonLiikeToimii() {
        a.toimiHahmollaSuuntaan(Komennot.ETELA);
        assertEquals(14, a.getGladiaattori().getSijainti().getX());
        assertEquals(15, a.getGladiaattori().getSijainti().getY());
    }
    
    @Test
    public void hahmonHyokkaysHirvioonToimii() {
        for (int i = 0; i < 50; i++) {
            this.a.toimiHahmollaSuuntaan(Komennot.POHJOINEN);
        }
        for (int j = 0; j < 50; j++) {
            this.a.toimiHahmollaSuuntaan(Komennot.LANSI);
        }
        assertTrue(this.hirvioidenOsumapisteet > this.laskeHirvioidenOsumapisteet(this.a.getHirviot()));
    }

    @Test
    public void onkoRuudussaHirvioitaLoytaaHirvion() {
        assertTrue(a.onkoRuudussaHirviota(new Koordinaatit(0, 0)));
    }

    @Test
    public void onkoRuudussaHirviotaEiLoydaHirviota() {
        assertTrue(!a.onkoRuudussaHirviota(new Koordinaatit(4, 4)));
    }

    @Test
    public void getHirvioRuudustaToimii() {
        Hirvio koeElain = a.getHirvioRuudusta(new Koordinaatit(0, 0));
        assertEquals(10, koeElain.getOsumapisteet());
        assertEquals(0, koeElain.getSijainti().getX());
        assertEquals(0, koeElain.getSijainti().getY());
    }
    
    @Test
    public void getHirvioRuudustaToimiiKunEiHirvioita() {
        a = new Areena(10, 10, t);
        assertTrue(a.getHirvioRuudusta(new Koordinaatit(0, 0)) == null);
    }

    @Test
    public void paivitaTilanneTest() {
        for(int i = 0; i < 100; i++) {
            a.toimiHahmollaSuuntaan(Komennot.ETELA);
            a.liikutaHirvioita();
            a.toimiHahmollaSuuntaan(Komennot.POHJOINEN);
            a.liikutaHirvioita();
        }
        a.paivitaTilanne();
        assertTrue(t.isPeliOhi());
        assertTrue(a.getHirviot().size() < 5);
    }
    
    @Test
    public void lisaaEfektiTest() {
        a.lisaaEfekti(new Efekti('$'));
        assertEquals(a.getEfektit().toString(), "<font color=ff3300>$</font>");
        a.lisaaEfekti(new Efekti('a'));
        assertEquals(a.getEfektit().toString(), "<font color=ff3300>$</font>");
        assertEquals(a.getEfektit().getSeuraava().toString(), "<font color=ff3300>a</font>");
    }
    
    @Test
    public void tyhjennaEfektitTest() {
        a.lisaaEfekti(new Efekti('s'));
        a.lisaaEfekti(new Efekti('f'));
        assertEquals(a.getEfektit().getViimeinen().toString(), "<font color=ff3300>f</font>");
        a.tyhjennaEfektit();
        assertTrue(a.getEfektit() == null);
    }
    
    @Test
    public void tarkistaKoordinaatitTest() {
        Koordinaatit k = new Koordinaatit(50, 50);
        a.tarkistaKoordinaatit(k);
        assertEquals(k.getX(), 27);
        assertEquals(k.getY(), 27);
    }
    
    public int laskeHirvioidenOsumapisteet(List<Hirvio> hirviot) {
        int x = 0;
        for (Hirvio hirmu : hirviot) {
            x += hirmu.getOsumapisteet();
        }
        return x;
    }
}
