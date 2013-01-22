
package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Areena;

public class Sovelluslogiikka {
    private Areena areena;

    public Sovelluslogiikka() {
        this.areena = new Areena(21, 21);
    }
    
    public Vuororaportti pelaaVuoro(Suunta s) {
        areena.toimiHahmollaSuuntaan(s);
        areena.liikutaHirvioita();
        
        return null;
    }
}
