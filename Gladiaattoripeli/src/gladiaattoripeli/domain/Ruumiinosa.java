package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Pelitilanne;

/**
 * Luokka edustaa hahmojen yksittäisiä vartalon osia ja pitää kirjaa niiden
 * vahingoittumisesta. Ruumiinosat esiintyvät aina Keho-olion osina.
 */
public class Ruumiinosa {

    private RuumiinosanNimi nimi; // Ruumiinosan nimi enumina
    private String omistajanNimi; // Ruumiinosan omistajan nimi
    private int osumapisteet; // Ruumiinosan omat osumapisteet
    private Boolean kuollut; // Boolean-arvo joka kertoo, onko osa kuollut

    /**
     * Luo uuden ruumiinosan.
     *
     * @param nimi ruumiinosan nimi
     * @param omistajanNimi ruumiinosan omistajan nimi
     * @param osumapisteet ruumiinosan osumapisteet
     */
    public Ruumiinosa(RuumiinosanNimi nimi, String omistajanNimi, int osumapisteet) {
        this.nimi = nimi;
        this.omistajanNimi = omistajanNimi;
        this.osumapisteet = osumapisteet;
        this.kuollut = false;
    }

    /**
     * Vähentää ruumiinosan osumapisteitä parametrina saadun vahingon verran ja
     * kirjaa vahingoittumisen pelitilanteeseen. Mikäli raaja kuoli, kirjaa myös
     * tiedon siitä ja muuttaa kuollut-muuttujan tilan vastaamaan todellisuutta.
     *
     * @param tilanne pelitilanne, johon tapahtumat kirjataan
     * @param vahinko vahingon määrä
     */
    public void otaVahinkoa(Pelitilanne tilanne, int vahinko) {
        this.osumapisteet -= vahinko;
        String vahinkoteksti =
                (this.omistajanNimi.charAt(this.omistajanNimi.length() - 1) == 'e')
                ? this.omistajanNimi + "e"
                : this.omistajanNimi;
        vahinkoteksti += "n " + this.nimi.getNimi();
        tilanne.lisaaTapahtuma(tilanne.viestit.onHaavoittunut(vahinkoteksti));
        if (this.osumapisteet <= 0) {
            this.kuollut = true;
            tilanne.lisaaTapahtuma(tilanne.viestit.onVammautunut(this.omistajanNimi));
        }
    }

    public Boolean isKuollut() {
        return this.kuollut;
    }

    public RuumiinosanNimi getNimi() {
        return this.nimi;
    }

    public String getOmistajanNimi() {
        return omistajanNimi;
    }

    public int getOsumapisteet() {
        return osumapisteet;
    }

    @Override
    public String toString() {
        return this.nimi.getNimi() + ", " + this.osumapisteet + " osumapistettä";
    }
}
