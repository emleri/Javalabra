package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka hallinnoi hirviöiden ja gladiaattorin sijaintia ja niiden muutoksia.
 */
public class Areena {

    private int leveys;
    private int korkeus;
    private Gladiaattori hahmo;
    private List<Hirvio> hirviot;

    public Areena(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.hahmo = new Gladiaattori(leveys / 2, korkeus / 2, leveys, korkeus);
        this.hirviot = new ArrayList<Hirvio>();
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public Gladiaattori getHahmo() {
        return hahmo;
    }

    public List<Hirvio> getHirviot() {
        return this.hirviot;
    }

    public void lisaaHirvio(Hirvio h) {
        this.hirviot.add(h);
    }
    
    /** 
     * Metodi komentaa jokaista areenan hirviötä yksi kerrallaan. Mikäli hirviö 
     * on gladiaattorin viereisissä koordinaateissa, se lyö gladiaattoria. Jos 
     * seuraavat koordinaatit kohti gladiaattoria ovat vapaana, hirviö liikkuu 
     * niihin. Muussa tapauksessa hirviö pysyy paikoillaan.
     * Jokaisen hirviön toiminta kirjataan vuororaporttiin.
     * 
     * @param v viite kyseisen vuoron vuororaporttiin
     */
    public void liikutaHirvioita(Vuororaportti v) {
        Koordinaatit hahmonKoordinaatit = this.hahmo.getSijainti();

        for (Hirvio h : this.hirviot) {
            Koordinaatit hirvionKoordinaatit = h.getSijainti();

            if (hirvionKoordinaatit.onVieressa(hahmonKoordinaatit)) {
                h.hyokkaa(this.hahmo, v);
            } else {
                Koordinaatit kohderuutu = hirvionKoordinaatit.getViereinenRuutuKohtiKoordinaatteja(hahmonKoordinaatit);
                if (!this.onkoRuudussaHirviota(kohderuutu)) {
                    h.siirry(kohderuutu);
                }
            }
        }
    }

    /**
     * Metodi ohjaa gladiaattoria suorittamaan toiminnon syötteenä saatuun suuntaan.
     * Mikäli viereisissä koordinaateissa valittuun suuntaan on hirviö, kutsutaan
     * gladiaattorin hyökkää-metodia kyseistä hirviötä kohtaan. Mikäli ruutu on 
     * vapaa, gladiaattori siirtyy siihen.
     * Toteutettu toiminto kirjataan vuororaporttiin.
     * 
     * @param suunta pelaajalta näppäimistösyötteenä saatu toimintasuunta
     * @param v viite kyseisen vuoron vuororaporttiin
     */
    public void toimiHahmollaSuuntaan(Suunta suunta, Vuororaportti v) {
        Koordinaatit kohdeRuutu = this.hahmo.getSijainti().getViereisetKoordinaatitSuunnassa(suunta);

        if (this.onkoRuudussaHirviota(kohdeRuutu)) {
            Hirvio h = this.haeHirvioRuudusta(kohdeRuutu);
            hahmo.hyokkaa(h, v);
        } else {
            hahmo.liikuSuuntaan(suunta);
            v.lisaaTapahtuma("Hahmo liikkuu");
        }
    }

    /**
     * Metodi tarkistaa, sijaitseeko yksikään hirviöistä syötteenä saaduissa 
     * koordinaateissa.
     * @param k tarkistettavat koordinaatit
     * @return totuusarvo hirviö/ei hirviötä - true/false
     */
    public boolean onkoRuudussaHirviota(Koordinaatit k) {
        for (Hirvio h : this.hirviot) {
            if (h.getSijainti().equals(k)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi etsii ja palauttaa hirviön syötteenä saaduista koordinaateista. 
     * Mikäli koordinaateissa ei ole hirviötä, palautetaan null.
     * @param k koordinaatit, joista hirviö etsitään
     * @return Hirvio palauttaa hirviön, mikäli sellainen löydetään.
     */
    public Hirvio haeHirvioRuudusta(Koordinaatit k) {
        for (Hirvio h : this.hirviot) {
            if (h.getSijainti().equals(k)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Metodi käy läpi kaikki hirviö-olennot ja poistaa kuolleet (0 tai vähemmän 
     * osumapistettä).
     */
    public void poistaKuolleet() {
        List<Hirvio> poistettavat = new ArrayList<Hirvio>();
        for (Hirvio h : this.hirviot) {
            if (h.getOsumaPisteet() < 1) {
                poistettavat.add(h);
            }
        }
        this.hirviot.removeAll(poistettavat);
    }

    /**
     * Metodi tarkistaa, onko gladiaattori kuollut (0 tai vähemmän osumapistettä).
     * @return Boolean true/false - kuollut/elossa
     */
    public Boolean onkoHahmoKuollut() {
        if (this.hahmo.getOsumapisteet() < 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodi käyttää luokan kahta muuta metodia poistaakseen kuolleet hirviöt
     * areenalta ja tarkistaakseen gladiaattorin tilan. Mikäli gladiaattori on 
     * kuollut, metodi kirjaa vuororaporttiin ilmoituksen pelin loppumisesta.
     * @param v kyseisen vuoron vuororaportti
     */
    public void paivitaTilanne(Vuororaportti v) {
        this.poistaKuolleet();
        if (this.onkoHahmoKuollut()) {
            v.lisaaTapahtuma("Hirviöt lyövät gladiaattorin kuoliaaksi. Peli on ohi.");
            v.lopetaPeli();
        }
    }
}
