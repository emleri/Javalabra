package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Komennot;

/**
 * Abstrakti luokka, joka kuvaa koordinaatistossa sijaitsevaa objektia. 
 * Gladiaattori ja hirviö ovat tämän luokan toteutuksia.
 */
public abstract class Liikutettava {

    Koordinaatit koordinaatit;

    public Liikutettava(Koordinaatit k) {
        this.koordinaatit = k;
    }
    
    public Koordinaatit getSijainti() {
        return this.koordinaatit;
    }
    
    public void siirry(Koordinaatit k) {
        this.koordinaatit = k;
    }
}
