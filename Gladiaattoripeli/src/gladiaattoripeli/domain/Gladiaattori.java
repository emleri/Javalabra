package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;
import java.util.ArrayList;
import java.util.List;

/**
 * Pelaajahahmoa kuvaava luokka, joka kokoaa yhteen vahingoittumis-, sijainti-
 * ja hyökkäysmekanismit. Perii Liikutettava-luokalta sijaintiominaisuutensa ja
 * sisältää Keho-olion vahingon kirjanpitäjänä.
 */
public class Gladiaattori extends Hahmo {
    private List<Hahmo> tapot; // Lista surmatuista hirviöistä, ei vielä toiminnallisuutta.

    /**
     * Konstruktori.
     *
     * @param sijaintiX sijainti areenalla x-akselilla
     * @param sijaintiY sijainti areenalla y-akselilla
     * @param areenanLeveys areenan leveys
     * @param areenanKorkeus areenan korkeus
     * @param keho gladiaattorin keho
     */
    public Gladiaattori(Koordinaatit k, Keho keho, Ase ase) {
        super("Gladiaattori", k, keho, ase, 100, 10, 10);
        this.tapot = new ArrayList<Hahmo>();
    }

    /**
     * Gladiaattori suorittaa yläluokkansa hoykkaa-metodin parametrina saamaansa
     * hirviöön ja jos kohde kuolee, lisää sen tapettujen listaansa.
     *
     * @param h kohdehirviö
     * @param tilanne vuoron vuororaportti, johon hyökkäyksen tulos kirjataan
     * @return boolean kuoliko kohde k/e
     */
    @Override
    public boolean hyokkaa(Hahmo h, Pelitilanne tilanne) {
        boolean kuoli = super.hyokkaa(h, tilanne);
        if (kuoli) {
            this.tapot.add(h);
        }
        return kuoli;
    }

    public List<Hahmo> getTapot() {
        return this.tapot;
    }

    /**
     * Muuttaa gladiaattorin hyökkäys- ja puolustusarvoa parametrin mukaan. Arvojen
     * summa pysyy aina samana, eli mikäli hyökkäys nousee, puolustus laskee saman 
     * verran, vice versa. Kumpikaan arvo ei voi laskea alle viiden, mikäli näin 
     * kävisi niin muutetaan arvoja vain viiteen asti.
     * @param muutos arvojen muutos, lisätään hyökkäysarvoon ja vähennetään 
     * puolustusarvosta
     */
    public void muutaHyokkaysarvoa(int muutos) {
        if (this.getHyokkaysarvo() + muutos < 5) {
            this.muutaHyokkaysarvoa(muutos + 1);
        } else if (this.getPuolustusarvo() - muutos < 5) {
            this.muutaHyokkaysarvoa(muutos - 1);
        } else {
            this.setHyokkaysarvo(this.getHyokkaysarvo() + muutos);
            this.setPuolustusarvo(this.getPuolustusarvo() - muutos);
        }
    }
}
