package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Komennot;

/**
 * Abstrakti luokka, joka kuvaa koordinaatistossa sijaitsevaa objektia. 
 * Gladiaattori ja hirviö ovat tämän luokan toteutuksia.
 */
public abstract class Liikutettava {

    Koordinaatit koordinaatit;

    public Liikutettava(int sijaintiX, int sijaintiY) {
        this.koordinaatit = new Koordinaatit(sijaintiX, sijaintiY);
    }
    
    public Koordinaatit getSijainti() {
        return this.koordinaatit;
    }

    public int getSijaintiX() {
        return this.koordinaatit.getX();
    }

    public int getSijaintiY() {
        return this.koordinaatit.getY();
    }
    
    public void liikuSuuntaan(Komennot suunta) {
        this.koordinaatit = this.koordinaatit.getViereisetKoordinaatitSuunnassa(suunta);
    }
    
    public void siirry(Koordinaatit k) {
        this.koordinaatit = k;
    }
}
