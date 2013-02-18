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
    private int aaltoNro;
    private Hahmogeneraattori hahmogeneraattori;
    private Gladiaattori hahmo;
    private List<Hirvio> hirviot;
    private List<Koordinaatit> esteet;
    private Efekti efektit;
    private Random arpoja;
    private Pelitilanne tilanne;

    public Areena(int leveys, int korkeus, Pelitilanne tilanne) {
        this.leveys = (leveys > 0) ? leveys : 1;
        this.korkeus = (korkeus > 0) ? korkeus : 1;

        this.hahmogeneraattori = new Hahmogeneraattori();
        this.hirviot = new ArrayList<Hirvio>();
        this.esteet = new ArrayList<Koordinaatit>();
        this.arpoja = new Random();
        this.tilanne = tilanne;
        this.efektit = null;
        this.aaltoNro = 0;
    }

    /**
     * Metodi suorittaa pelin aloittamiseen tarvittavat valmistelut: luo
     * gladiaattorin, hirviöt ja esteet.
     */
    public void alustaPeli() {
        this.luoHahmot();
        this.luoEsteet();
    }

    /**
     * Luo pelin alussa tarvittavat hahmot: gladiaattorin ja 5 hirviötä.
     */
    public void luoHahmot() {
        this.hahmo = hahmogeneraattori.luoGladiaattori(this);
        this.luoHirvioita(3);
    }

    public void luoEsteet() {
        int a = (int) Math.floor(0.25 * this.leveys);
        int b = (int) Math.round(0.75 * this.leveys);
        int c = (int) Math.round(0.25 * this.korkeus);
        int d = (int) Math.floor(0.75 * this.korkeus);
        int e = this.leveys / 2;
        int f = this.korkeus / 2;
        this.esteet.add(new Koordinaatit(a, c));
        this.esteet.add(new Koordinaatit(a, d));
        this.esteet.add(new Koordinaatit(b, c));
        this.esteet.add(new Koordinaatit(b, d));
        this.esteet.add(new Koordinaatit(a, f));
        this.esteet.add(new Koordinaatit(b, f));
        this.esteet.add(new Koordinaatit(e, c));
        this.esteet.add(new Koordinaatit(e, d));
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
     *
     * @param maara luotavien hirviöiden määrä
     */
    private void luoHirvioita(int maara) {
        if (maara > this.leveys * 2 + this.korkeus * 2 - 4) {
            throw new IllegalArgumentException("Liian monta hirviötä.");
        }

        Koordinaatit k;
        Hirvio h;

        for (int i = 0; i < maara; i++) {
            h = this.hahmogeneraattori.luoHirvio();
            k = this.arvoTyhjaSijaintiLaidalta();
            h.siirry(k);
            this.lisaaHirvio(h);
        }
    }

    /**
     * Luo uuden 'aallon' hirviöitä, mikäli edellinen aalto on kuollut. Jokainen
     * aalto on aina edellistä suurempi. Kolmannen aallon jälkeen taisteluun 
     * liittyy myös lohikäärme.
     */
    public void seuraavaAalto() {
        if (this.eiHirvioita()) {
            this.tilanne.lisaaTapahtuma("Lisää hirviöitä ryntää areenalle!");
            this.luoHirvioita(3 + 2 * this.aaltoNro);
            this.aaltoNro++;

            if (this.aaltoNro > 3) {
                Hirvio d = this.hahmogeneraattori.luoLohikaarme();
                d.siirry(this.arvoTyhjaSijaintiLaidalta());
                this.hirviot.add(d);
            }
        }

    }

    /**
     * Tarkistaa, ovatko areenan kaikki hirviöt kuolleet.
     *
     * @return boolean on/ei ole hirviöitä - false/true
     */
    private boolean eiHirvioita() {
        if (this.hirviot.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * Lisää hirviön hirviölistaan ja siten areenalle.
     *
     * @param h lisättävä hirviö
     */
    public void lisaaHirvio(Hirvio h) {
        this.hirviot.add(h);
    }

    /**
     * Metodi käy läpi satunnaisia ruutuja areenan reunoilta kunnes löytää
     * sijainnin, jossa ei ole hirviötä ja palauttaa koordinaatit siihen.
     *
     * @return koordinaatit tyhjään ruutuun areenan laidalla
     */
    private Koordinaatit arvoTyhjaSijaintiLaidalta() {
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
        for (Hirvio h : this.hirviot) {
            h.liiku(hahmo, tilanne, this);
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
        this.tarkistaKoordinaatit(kohderuutu);

        if (this.onkoRuudussaHirviota(kohderuutu)) {
            Hirvio h = this.getHirvioRuudusta(kohderuutu);
            this.lisaaEfekti(this.hahmo.getHyokkaysEfekti(kohderuutu));
            hahmo.hyokkaa(h, tilanne);
        } else if (!this.onkoRuudussaEstetta(kohderuutu)) {
            hahmo.siirry(kohderuutu);
            tilanne.lisaaTapahtuma("Gladiaattori liikkuu.");
        } else {
            tilanne.lisaaTapahtuma("Gladiaattori törmää seinään.");
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

    public boolean onkoRuudussaEstetta(Koordinaatit k) {
        for (Koordinaatit t : this.esteet) {
            if (t.equals(k)) {
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
    public Hirvio getHirvioRuudusta(Koordinaatit k) {
        for (Hirvio h : this.hirviot) {
            if (h.getSijainti().equals(k)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Metodi käyttää kolmea apumetodia poistaakseen kuolleet hirviöt areenalta
     * ja tarkistaakseen gladiaattorin tilan. Mikäli gladiaattori on kuollut,
     * metodi kirjaa pelitilanteeseen pelin loppuneen.
     */
    public void paivitaTilanne() {
        this.poistaKuolleet();
        if (this.onkoHahmoKuollut()) {
            tilanne.lisaaTapahtuma("Hirviöt lyövät gladiaattorin kuoliaaksi. Peli on ohi.");
            tilanne.lopetaPeli();
        }
    }

    /**
     * Metodi käy läpi kaikki hirviö-olennot ja poistaa kuolleet (0 tai vähemmän
     * osumapistettä).
     */
    private void poistaKuolleet() {
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
    private Boolean onkoHahmoKuollut() {
        if (this.hahmo.getOsumapisteet() < 1) {
            return true;
        }
        return false;
    }

    /**
     * Lisää efektilistaan parametrina saadun efektin. Mikäli lista on tyhjä,
     * alustaa sen tällä efektillä.
     *
     * @param e lisättävä efekti
     */
    public void lisaaEfekti(Efekti e) {
        if (this.efektit == null) {
            this.efektit = e;
        } else {
            this.efektit.getViimeinen().lisaaSeuraavaFrame(e);
        }
    }

    public Efekti getEfektit() {
        return this.efektit;
    }

    /**
     * Tyhjentää efektilistan asettamalla ensimmäiseksi nodeksi null-arvon.
     */
    public void tyhjennaEfektit() {
        this.efektit = null;
    }

    /**
     * Tarkistaa, että parametrina saadut koordinaatit mahtuvat areenan rajoihin
     * ja muokkaa ne sopiviksi, mikäli ei.
     *
     * @param k tarkistettavat koordinaatit
     */
    public void tarkistaKoordinaatit(Koordinaatit k) {
        if (k.getX() >= this.leveys) {
            k.setX(this.leveys - 1);
        } else if (k.getX() < 0) {
            k.setX(0);
        }

        if (k.getY() >= this.korkeus) {
            k.setY(leveys - 1);
        } else if (k.getY() < 0) {
            k.setY(0);
        }
    }
}
