package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;

/**
 * Pelin vastustajia kuvaava luokka, joka laajentaa Hahmon toiminnallisuutta. 
 * Luokka lisää hahmolle tekoälyn, joka ohjaa sitä taistelemaan gladiaattoria
 * vastaan.
 */
public class Hirvio extends Hahmo {
    /**
     * Konstruktori välittää vain parametrit eteenpäin yläluokan konstruktorille.
     * @param osumapisteet hirviön osumapisteet
     * @param keho viite hirviölle annettavaan kehoon
     * @param ase viite hirviölle annettavaan aseeseen
     */
    public Hirvio(int osumapisteet, Keho keho, Ase ase) {
        super("Hirviö", new Koordinaatit(0, 0), keho, ase, osumapisteet, 7, 13);
    }

    /**
     * Hirviön (kehnon) tekoälyn toteuttava metodi.
     * 
     * Mikäli hirviö ei ole gladiaattorin viereisessä ruudussa, se siirtyy siihen 
     * viereiseen ruutuun, joka vie sen lähimmäksi gladiaattoria. Mikäli kyseinen
     * ruutu on varattu, se ottaa askeleen satunnaiseen vapaaseen suuntaan. Mikäli 
     * tilaa liikkua mihinkään suuntaan ei ole, hirviö pysyy paikallaan.
     * 
     * Mikäli hirviö on gladiaattorin viereisessä ruudussa, se kutsuu hyökkää-
     * metodiaan kohteena gladiaattori.
     * 
     * @param gladiaattori viite gladiaattoriin
     * @param tilanne tilanne tapahtumien raportointia varten
     * @param areena areena sijaintien tarkistamiseen
     */
    public void liiku(Gladiaattori gladiaattori, Pelitilanne tilanne, Areena areena) {
        if (this.getSijainti().onVieressa(gladiaattori.getSijainti())) {
            areena.lisaaEfekti(this.getHyokkaysefekti(gladiaattori.getSijainti()));
            this.hyokkaa(gladiaattori, tilanne);
        } else {
            Koordinaatit kohderuutu = this.getSijainti().getViereinenRuutuKohtiKoordinaatteja(gladiaattori.getSijainti());
            if (!areena.onkoRuudussaHirviota(kohderuutu) && !areena.onkoRuudussaEstetta(kohderuutu)) {
                this.siirry(kohderuutu);
            } else {
                int varmistus = 0;
                while (true) {
                    kohderuutu = this.getSijainti().getSatunnainenRuutuVieressa();
                    if (!areena.onkoRuudussaHirviota(kohderuutu) && !areena.onkoRuudussaEstetta(kohderuutu)) {
                        this.siirry(kohderuutu);
                        break;
                    }
                    varmistus++;
                    if (varmistus > 50) {
                        break;
                    }
                }
            }
        }
    }
}
