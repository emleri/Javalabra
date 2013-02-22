/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;

/**
 * Lohikäärme-hahmo. Lentävä, tulta syöksevä hirviö.
 *
 * @author Emleri
 */
public class Lohikaarme extends Hirvio {

    private int toimintolaskuri;
    private boolean ilmassa;

    /**
     * Lohikäärmeen konstruktori. Käyttää hirviön konstruktoria, laajentaen sen
     * pohjalta omat toiminnallisuutensa.
     *
     * @param osumapisteet lohikäärmeen osumapiste-arvo
     * @param keho viite lohikäärmeelle annettavaan keho-olioon
     * @param ase viite lohikäärmeelle annettavaan aseeseen
     */
    public Lohikaarme(int osumapisteet, Keho keho, Ase ase) {
        super(osumapisteet, keho, ase);
        this.toimintolaskuri = 0;
    }

    /**
     * Hirviön liiku-metodin korvaava versio, joka ohjaa lohikäärmettä
     * toteuttamaan ennaltamäärätyssä järjestyksessä joukkoa erilaisia
     * toimintoja. Toimintolaskurin arvo määrää, mitä metodi tekee, ja metodi
     * kasvattaa sitä yhden askeleen joka suorituskerrallaan.
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
                if (!this.isIlmassa()) {
                    super.liiku(gladiaattori, tilanne, areena);
                }
                break;
            case 9:
                if (!this.isIlmassa()) {
                    super.liiku(gladiaattori, tilanne, areena);
                } else {
                    this.laskeudu(gladiaattori, tilanne, areena);
                }
                this.toimintolaskuri = 0;
                break;
        }
    }

    /**
     * Metodi toimii lohikäärmeen erikoishyökkäyksenä. Se suorittaa hyökkäyksen
     * gladiaattoriin ja jokaiseen hirviöön tämän vieressä. Hyökkäys toimii
     * lohikäärmeen ollessa missä tahansa koordinaateissa areenalla.
     *
     * Metodi huolehtii myös getTuliEfekti-metodilta saadun efektin
     * laajentamisesta jokaiseen ruutuun, johon hyökkäys vaikuttaa.
     *
     * @param gladiaattori viite gladiaattoriin hyökkäyksen suorittamiseksi
     * @param tilanne viite pelitilanteeseen hyökkäyksen raportointia varten
     * @param areena viite areenaan, jolta saadaan hirviöt joihin hyökkäys osuu
     */
    private void syokseTulta(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        if (!gladiaattori.isElossa()) {
            return;
        }
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

    /**
     * Luo uuden, kahden framen mittaisen efektin lohikäärmeen tulta
     * merkitsemään, keskipisteenään parametrina saadut koordinaatit.
     * Palautettava efekti ei ole valmis, vaan syokseTulta-metodi lisää efektiin
     * piirtokohtia vaikutus- alueen mukaan.
     *
     * @param k kohdekoordinaatit
     * @return tuliefekti
     */
    private Efekti getTuliefekti(Koordinaatit k) {
        if (k == null) {
            throw new IllegalArgumentException();
        }
        Efekti e = new Efekti('§');
        e.lisaaSeuraavaRuutu(new Efekti('§'));
        e.getSeuraava().lisaaPiirtokohta(k);
        return e;
    }

    /**
     * Käskee lohikäärmeen nousta lentoon. Metodi tarkistaa ensin lohikäärmeen
     * siipien tilan. Mikäli ne ovat haavoittuneet, lentäminen ei onnistu ja
     * metodi kutsuu hirviön liiku-metodia liikuttaakseen lohikäärmettä maassa.
     * Muussa tapauksessa lohikäärme nousee ilmaan ja poistuu taistelusta,
     * kunnes laskeudu-metodia kutsutaan.
     *
     * @param gladiaattori gladiaattori mahdollista yläluokan liiku-kutsua
     * varten
     * @param tilanne pelitilanne toiminnon raportoimiseen
     * @param areena areena, johon kirjataan lohikäärmeen liike
     */
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

    /**
     * Metodi käskee lohikäärmettä laskeutumaan, mikäli se lentää. Jos
     * lohikäärme on ilmassa, se laskeutuu satunnaiseen ruutuun areenalla.
     * Muussa tapauksessa mitään ei tapahdu.
     *
     * @param gladiaattori gladiaattori sijainnin tarkistamista varten
     * @param tilanne pelitilanne tapahtuman raportointia varten
     * @param areena areena hirviöiden sijainnin tarkistamista ja lohikäärmeen
     * uuden sijainnin kirjaamista varten
     */
    private void laskeudu(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        if (this.isIlmassa()) {
            this.ilmassa = false;
            Koordinaatit k;
            while (true) {
                k = new Koordinaatit(this.getArpoja().nextInt(areena.getLeveys()),
                        this.getArpoja().nextInt(areena.getKorkeus()));
                if (!areena.onkoRuudussaEstetta(k) && !areena.onkoRuudussaHirviota(k)
                        && !gladiaattori.getSijainti().equals(k)) {
                    break;
                }
            }
            this.siirry(k);
        }
    }

    /**
     * Kertoo, onko lohikäärme ilmassa vai maassa.
     *
     * @return boolean true/false ilmassa/maassa
     */
    public boolean isIlmassa() {
        return ilmassa;
    }
}
