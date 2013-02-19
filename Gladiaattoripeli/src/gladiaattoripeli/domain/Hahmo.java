package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Pelitilanne;

/**
 * Abstrakti luokka, joka kuvaa koordinaatistossa sijaitsevaa objektia. 
 * Gladiaattori ja hirviö ovat tämän luokan toteutuksia.
 */
public abstract class Hahmo {

    Koordinaatit koordinaatit;

    public Hahmo(Koordinaatit k) {
        this.koordinaatit = k;
    }
    
    public Koordinaatit getSijainti() {
        return this.koordinaatit;
    }
    
    public void siirry(Koordinaatit k) {
        this.koordinaatit = k;
    }
    
    abstract boolean puolusta(Pelitilanne tilanne, int vahinko, int hyokkaysarvo);
    
    abstract void hyokkaa(Hahmo hahmo, Pelitilanne tilanne);
}
