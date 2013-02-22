package gladiaattoripeli.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Esittää animoituja efektejä linkitettynä listana koordinaattijoukkoja ja piirto-
 * asuja. Jokainen listan solmu esittää yhtä efektin animaatioruutua, listalla 
 * eteenpäin liikkuessa siirrytään ruudusta toiseen.
 * @author Emleri
 */
public class Efekti {
    private char ulkoasu; // Merkki, jolla tämä efektiruutu piirretään
    private Efekti seuraava; // Listan seuraava solmu
    private List<Koordinaatit> piirtoalue; // Koordinaatit, joihin ruutu piirretään

    /**
     * Efektisolmun konstruktori. Parametrina saadaan piirtomerkki, piirtokohtia
     * ruudulla ei aluksi ole.
     * @param ulkoasu
     */
    public Efekti(char ulkoasu) {
        this.ulkoasu = ulkoasu;
        this.piirtoalue = new ArrayList<Koordinaatit>();
    }
    
    /**
     * Lisää uuden piirtokohdan tälle efektiruudulle.
     * @param k
     */
    public void lisaaPiirtokohta(Koordinaatit k) {
        this.piirtoalue.add(k);
    }
    
    /**
     * Tarkistaa, kuuluvatko parametreina saadut koordinaatit efektiruudun piirto-
     * alueelle.
     * @param k tarkistettavat koordinaatit
     * @return boolean true/false kuuluu/ei kuulu
     */
    public Boolean piirretaanko(Koordinaatit k) {
        for (Koordinaatit l : this.piirtoalue) {
            if (k.equals(l)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Lisää seuraavan solmun listaan.
     * @param e solmuksi lisättävä efekti
     */
    public void lisaaSeuraavaRuutu(Efekti e) {
        if(e == null) {
            throw new IllegalArgumentException();
        }
        this.seuraava = e;
    }
    
    /**
     * Palauttaa efektin ulkoasun suljettuna efektin värin määrääviin HTML-tageihin.
     * @return värjätty piirtomerkki
     */
    @Override
    public String toString() {
        return "<font color=ff3300>" + this.ulkoasu + "</font>";
    }

    /**
     * Palauttaa listan seuraavan solmun.
     * @return seuraava efekti
     */
    public Efekti getSeuraava() {
        return this.seuraava;
    }
    
    /**
     * Käy läpi listan solmuja kunnes löytää viimeisen ja palauttaa viitteen siihen.
     * @return viimeinen solmu
     */
    public Efekti getViimeinen() {
        Efekti e = this;
        while(e.getSeuraava() != null) {
            e = e.getSeuraava();
        }
        return e;
    }
}
