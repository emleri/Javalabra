package gladiaattoripeli.utilities;

/**
 * High score -pisteet ja niiden omistajan kapseloiva apuluokka.
 */
public class Pisteet implements Comparable<Pisteet> {

    private String nimi; // Pelaajan nimi
    private int pisteet; // Pelaajan pisteet

    /**
     * Konstruktori. Käyttää nimen asettamiseen omaa setNimi()-metodiaan
     * varmistaakseen yhteensopivuuden pisteentallentajan kanssa.
     *
     * @param pisteet
     * @param nimi
     */
    public Pisteet(int pisteet, String nimi) {
        this.setNimi(nimi);
        this.pisteet = pisteet;
    }

    public String getNimi() {
        return nimi;
    }

    /**
     * Korvaa parametrina saadusta nimestä HighScorenKasittelija-luokan käyttöön
     * varatut ':'-merkin '.'-merkillä ja asettaa sen sitten nimi-kentän
     * arvoksi.
     *
     * @param nimi
     */
    public final void setNimi(String nimi) {
        String kasiteltyNimi = nimi.replace(':', '.');
        this.nimi = kasiteltyNimi;
    }

    public int getPisteet() {
        return pisteet;
    }

    @Override
    public String toString() {
        return this.pisteet + " pistettä, " + this.nimi;
    }

    /**
     * compareTo-metodin toteutus luokalle.
     *
     * @param p verrattavat pisteet
     * @return 1/0/-1 jos pisteet suuremmat/yhtä suuret/pienemmät kuin
     * verrattavat
     */
    @Override
    public int compareTo(Pisteet p) {
        if (this.pisteet > p.pisteet) {
            return -1;
        } else if (this.pisteet == p.pisteet) {
            return 0;
        } else {
            return 1;
        }
    }
}
