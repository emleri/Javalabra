/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import gladiaattoripeli.utilities.Pelitilanne;
import gladiaattoripeli.utilities.RuumiinosanNimi;
import java.util.Random;

/**
 *
 * @author Emleri
 */
public class Lohikaarme extends Hirvio {

    private int toimintolaskuri;
    private boolean ilmassa;
    private Random arpoja;

    /**
     * Lohikäärmeen konstruktori. Käyttää hirviön konstruktoria, laajentaen sen 
     * pohjalta omat toiminnallisuutensa.
     * @param osumapisteet lohikäärmeen osumapiste-arvo
     * @param keho viite lohikäärmeelle annettavaan keho-olioon
     */
    public Lohikaarme(int osumapisteet, Keho keho) {
        super(osumapisteet, keho);
        this.toimintolaskuri = 0;
        this.arpoja = new Random();
    }

    /**
     * Hirviön liiku-metodin korvaava versio, joka ohjaa lohikäärmettä toteuttamaan
     * ennaltamäärätyssä järjestyksessä joukkoa erilaisia toimintoja. 
     * Toimintolaskurin arvo määrää, mitä metodi tekee, ja metodi kasvattaa sitä 
     * yhden askeleen joka suorituskerrallaan.
     * 
     * @param gladiaattori viite gladiaattoriin
     * @param tilanne viite pelitilanteeseen
     * @param areena viite areenaan
     */
    @Override
    public void liiku(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        switch (toimintolaskuri) {
            case 0:
                this.syokseTulta(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 1:
                super.liiku(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 2:
                super.liiku(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 3:
                super.liiku(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 4:
                this.syokseTulta(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 5:
                super.liiku(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 6:
                super.liiku(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 7:
                this.nouseIlmaan(gladiaattori, tilanne, areena);
                this.toimintolaskuri++;
                break;
            case 8:
                this.toimintolaskuri++;
                break;
            case 9:
                this.laskeudu(gladiaattori, tilanne, areena);
                this.toimintolaskuri = 0;
                break;
        }
    }

    private void syokseTulta(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        Koordinaatit k = gladiaattori.getSijainti();
        Koordinaatit liekkeja;
        Hirvio h;
        Efekti e;

        tilanne.lisaaTapahtuma(tilanne.viestit.syokseeTulta(super.getNimi()));
        gladiaattori.puolusta(tilanne, 20, 15);
        e = this.getTuliefekti(k);

        for (int x = k.getX() - 1; x < k.getX() + 2; x++) {
            for (int y = k.getY() - 1; y < k.getY() + 2; y++) {
                liekkeja = new Koordinaatit(x, y);
                e.lisaaPiirtokohta(liekkeja);

                if (areena.onkoRuudussaHirviota(liekkeja)) {
                    h = areena.getHirvioRuudusta(liekkeja);
                    if (!h.equals(this)) {
                        h.puolusta(tilanne, 50, 15);
                    }
                }
            }
        }

        areena.lisaaEfekti(e);
    }

    private Efekti getTuliefekti(Koordinaatit k) {
        if(k == null) {
            throw new IllegalArgumentException();
        }
        Efekti e = new Efekti('§');
        e.lisaaSeuraavaFrame(new Efekti('§'));
        e.getSeuraava().lisaaPiirtokohta(k);
        return e;
    }

    private void nouseIlmaan(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        Keho k = super.getKeho();
        if (!k.getRaaja(RuumiinosanNimi.OIKEASIIPI).isKuollut()
                && !k.getRaaja(RuumiinosanNimi.VASENSIIPI).isKuollut()) {
            this.ilmassa = true;
            this.siirry(new Koordinaatit(areena.getLeveys() + 1, areena.getKorkeus() + 1));
            tilanne.lisaaTapahtuma(tilanne.viestit.nouseeIlmaan(super.getNimi()));
        } else {
            super.liiku(gladiaattori, tilanne, areena);
        }
    }

    private void laskeudu(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        this.ilmassa = false;
        Koordinaatit k;
        while (true) {
            k = new Koordinaatit(this.arpoja.nextInt(areena.getLeveys()),
                    this.arpoja.nextInt(areena.getKorkeus()));
            if (!areena.onkoRuudussaEstetta(k) && !areena.onkoRuudussaHirviota(k)
                    && !gladiaattori.getSijainti().equals(k)) {
                break;
            }
        }
        this.siirry(k);
    }

    /**
     * Kertoo, onko lohikäärme ilmassa vai laskeutunut.
     * @return boolean true/false ilmassa/maassa
     */
    public boolean isIlmassa() {
        return ilmassa;
    }
}
