package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Hahmogeneraattori;
import gladiaattoripeli.utilities.Komennot;
import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Pelitilanne;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Luokka hallinnoi hirviöiden ja gladiaattorin sijaintia ja niiden muutoksia.
 */
public class Areena {

    private int leveys;
    private int korkeus;
    private Hahmogeneraattori hahmogeneraattori;
    private Gladiaattori hahmo;
    private List<Hirvio> hirviot;
    private Efekti efektit;
    private Random arpoja;
    private Pelitilanne tilanne;

    public Areena(int leveys, int korkeus, Pelitilanne tilanne) {
        if (leveys > 0) {
            this.leveys = leveys;
        } else {
            this.leveys = 1;
        }
        if (korkeus > 0) {
            this.korkeus = korkeus;
        } else {
            this.korkeus = 1;
        }
        this.hahmogeneraattori = new Hahmogeneraattori();
        this.hirviot = new ArrayList<Hirvio>();
        this.arpoja = new Random();
        this.tilanne = tilanne;
        this.efektit = null;
    }

    public void luoHahmot() {
        this.hahmo = hahmogeneraattori.luoGladiaattori(this);
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

    /**
     * Metodi luo hirviöitä satunnaisille paikoille areenan laidalle.
     * @param maara luotavien hirviöiden määrä
     */
    public void luoHirvioita(int maara) {
        if (maara > this.leveys * 2 + this.korkeus*2 - 4) {
            throw new IllegalArgumentException("Liian monta hirviötä.");
        }
        
        Koordinaatit k;
        int x;
        int y;

        for (int i = 0; i < maara; i++) {
                k = this.arvoTyhjaSijaintiLaidalta();
                x = k.getX();
                y = k.getY();
                this.lisaaHirvio(new Hirvio(x, y, 5));
        }
    }

    public void lisaaHirvio(Hirvio h) {
        this.hirviot.add(h);
    }

    /**
     * Metodi käy läpi satunnaisia ruutuja areenan reunoilta kunnes löytää 
     * sijainnin, jossa ei ole hirviötä ja palauttaa koordinaatit siihen.
     * @return koordinaatit tyhjään ruutuun areenan laidalla
     */
    public Koordinaatit arvoTyhjaSijaintiLaidalta() {
        int r;
        int x = 0;
        int y = 0;
        Koordinaatit k;

        while (true) {
            r = this.arpoja.nextInt(4);
            switch (r) {
                case 0:
                    x = this.arpoja.nextInt(this.leveys);
                    y = 0;
                    break;
                case 1:
                    x = this.arpoja.nextInt(this.leveys);
                    y = this.korkeus - 1;
                    break;
                case 2:
                    x = 0;
                    y = this.arpoja.nextInt(this.korkeus);
                    break;
                case 3:
                    x = this.leveys - 1;
                    y = this.arpoja.nextInt(this.korkeus);
                    break;
            }
            k = new Koordinaatit(x, y);

            if (!this.onkoRuudussaHirviota(k)) {
                break;
            }
        }
        return k;
    }

    /**
     * Metodi komentaa jokaista areenan hirviötä yksi kerrallaan. Mikäli hirviö
     * on gladiaattorin viereisissä koordinaateissa, se lyö gladiaattoria. Jos
     * seuraavat koordinaatit kohti gladiaattoria ovat vapaana, hirviö liikkuu
     * niihin. Muussa tapauksessa hirviö pysyy paikoillaan. Jokaisen hirviön
     * toiminta kirjataan vuororaporttiin.
     *
     * @param v viite kyseisen vuoron vuororaporttiin
     */
    public void liikutaHirvioita() {
        Koordinaatit hahmonKoordinaatit = this.hahmo.getSijainti();

        for (Hirvio h : this.hirviot) {
            Koordinaatit hirvionKoordinaatit = h.getSijainti();

            if (hirvionKoordinaatit.onVieressa(hahmonKoordinaatit)) {
                this.lisaaEfekti(h.getHyokkaysefekti(hahmonKoordinaatit));
                h.hyokkaa(this.hahmo, tilanne);
            } else {
                Koordinaatit kohderuutu = hirvionKoordinaatit.getViereinenRuutuKohtiKoordinaatteja(hahmonKoordinaatit);
                if (!this.onkoRuudussaHirviota(kohderuutu)) {
                    h.siirry(kohderuutu);
                }
            }
        }
    }

    /**
     * Metodi ohjaa gladiaattoria suorittamaan toiminnon syötteenä saatuun
     * suuntaan. Mikäli viereisissä koordinaateissa valittuun suuntaan on
     * hirviö, kutsutaan gladiaattorin hyökkää-metodia kyseistä hirviötä
     * kohtaan. Mikäli ruutu on vapaa, gladiaattori siirtyy siihen. Toteutettu
     * toiminto kirjataan vuororaporttiin.
     *
     * @param suunta pelaajalta näppäimistösyötteenä saatu toimintasuunta
     * @param v viite kyseisen vuoron vuororaporttiin
     */
    public void toimiHahmollaSuuntaan(Komennot suunta) {
        Koordinaatit kohderuutu = this.hahmo.getSijainti().getViereisetKoordinaatitSuunnassa(suunta);

        if (this.onkoRuudussaHirviota(kohderuutu)) {
            Hirvio h = this.haeHirvioRuudusta(kohderuutu);
            this.lisaaEfekti(this.hahmo.getHyokkaysEfekti(kohderuutu));
            hahmo.hyokkaa(h, tilanne);
        } else {
            hahmo.liikuSuuntaan(suunta);
            tilanne.lisaaTapahtuma("Hahmo liikkuu");
        }
    }

    /**
     * Metodi tarkistaa, sijaitseeko yksikään hirviöistä syötteenä saaduissa
     * koordinaateissa.
     *
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
     *
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
            if (h.getOsumapisteet() < 1) {
                poistettavat.add(h);
            }
        }

        this.hirviot.removeAll(poistettavat);
    }

    /**
     * Metodi tarkistaa, onko gladiaattori kuollut (0 tai vähemmän
     * osumapistettä).
     *
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
     * kuollut, metodi kirjaa pelitilanteeseen ilmoituksen pelin loppumisesta.
     */
    public void paivitaTilanne() {
        this.poistaKuolleet();
        if (this.onkoHahmoKuollut()) {
            tilanne.lisaaTapahtuma("Hirviöt lyövät gladiaattorin kuoliaaksi. Peli on ohi.");
            tilanne.lopetaPeli();
        }
    }
    
    public Efekti getEfektit() {
        return this.efektit;
    }
    
    public void tyhjennaEfektit() {
        this.efektit = null;
    }
    
    public void lisaaEfekti(Efekti e) {
        if (this.efektit == null) {
            this.efektit = e;
        } else {
            this.efektit.getViimeinen().lisaaSeuraavaFrame(e);
        }
    }
}
