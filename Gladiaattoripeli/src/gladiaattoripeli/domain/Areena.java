package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Suunta;
import gladiaattoripeli.utilities.Vuororaportti;
import java.util.ArrayList;
import java.util.List;

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

    public void toimiHahmollaSuuntaan(Suunta suunta, Vuororaportti v) {
        Koordinaatit kohdeRuutu = this.hahmo.getSijainti().getViereisetKoordinaatitSuunnassa(suunta);

        if (this.onkoRuudussaHirviota(kohdeRuutu)) {
            Hirvio h = this.haeHirvioRuudusta(kohdeRuutu);
            hahmo.hyokkaa(h, v);
        } else {
            hahmo.liikuSuuntaan(suunta);
        }
    }

    public boolean onkoRuudussaHirviota(Koordinaatit k) {
        for (Hirvio h : this.hirviot) {
            if (h.getSijainti().equals(k)) {
                return true;
            }
        }
        return false;
    }

    public Hirvio haeHirvioRuudusta(Koordinaatit k) {
        for (Hirvio h : this.hirviot) {
            if (h.getSijainti().equals(k)) {
                return h;
            }
        }
        return null;
    }

    public void poistaKuolleet() {
        List<Hirvio> poistettavat = new ArrayList<Hirvio>();
        for (Hirvio h : this.hirviot) {
            if (h.getOsumaPisteet() < 1) {
                poistettavat.add(h);
            }
        }
        this.hirviot.removeAll(poistettavat);
    }

    public Boolean onkoHahmoKuollut() {
        if (this.hahmo.getOsumapisteet() < 1) {
            return true;
        }
        return false;
    }

    public void paivitaTilanne(Vuororaportti v) {
        this.poistaKuolleet();
        if (this.onkoHahmoKuollut()) {
            v.lisaaTapahtuma("Hirviöt lyövät gladiaattorin kuoliaaksi. Peli on ohi.");
            v.lopetaPeli();
        }
    }
}
