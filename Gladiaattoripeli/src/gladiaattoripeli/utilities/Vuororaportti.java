
package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Gladiaattori;
import gladiaattoripeli.domain.Hirvio;
import java.util.ArrayList;
import java.util.List;

/**
 * Tiedonkuljettajaluokka, joka välittää pelitapahtumat ja -tilanteen ylöspäin 
 * käyttöliittymälle.
 */
public class Vuororaportti {
    private List<String> tapahtumat;
    private List<Hirvio> hirviot;
    private Gladiaattori hahmo;
    private Boolean peliOhi;

    public Vuororaportti() {
        tapahtumat = new ArrayList<String>();
        peliOhi = false;
    }

    public List<String> getTapahtumat() {
        return tapahtumat;
    }
    
    public void lisaaTapahtuma(String tapahtuma) {
        this.tapahtumat.add(tapahtuma);
    }

    public List<Hirvio> getHirviot() {
        return this.hirviot;
    }
    
    public void setHirviot(List<Hirvio> hirviot) {
        this.hirviot = hirviot;
    }

    public void setHahmo(Gladiaattori hahmo) {
        this.hahmo = hahmo;
    }

    public Gladiaattori getHahmo() {
        return hahmo;
    }

    public void lopetaPeli() {
        this.peliOhi = true;
    }
    
    public Boolean peliOhi() {
        return this.peliOhi;
    }
}
