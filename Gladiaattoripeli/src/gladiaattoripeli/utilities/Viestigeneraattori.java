package gladiaattoripeli.utilities;

/**
 * Muotoilee peliviestejä tulostukseen sopivaan muotoon. Kokoaa nimiä ja
 * toiminto- viestejä yhteen, lisää HTML-väritageja.
 */
public class Viestigeneraattori {

    /**
     * Konstruktori.
     */
    public Viestigeneraattori() {
    }

    /**
     * Viesti: "'kuka' on haavoittunut." Väri: Oranssi, mikäli pelaaja on
     * haavoittunut, vihreä mikäli hirviö.
     *
     * @param kuka
     * @return viesti
     */
    public String onHaavoittunut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if (onGladiaattori(kuka)) {
            return "<font color=f88017>" + kuka + " on haavoittunut.</font>";
        }
        return "<font color=008000>" + kuka + " on haavoittunut.</font>";
    }

    /**
     * Viesti: "'kuka' on vammautunut vakavasti." Väri: Punainen, mikäli pelaaja
     * on haavoittunut, vihreä mikäli hirviö.
     *
     * @param kuka
     * @return viesti
     */
    public String onVammautunut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if (onGladiaattori(kuka)) {
            return "<font color=ff0000>" + kuka + " on vammautunut vakavasti.</font>";
        }
        return "<font color=00ff00>" + kuka + " on vammautunut vakavasti.</font>";
    }

    /**
     * Viesti: "'kuka' kaatuu kuolleena maahan." Väri: Punainen, mikäli pelaaja
     * on kuollut, vaaleanvihreä mikäli hirviö.
     *
     * @param kuka
     * @return viesti
     */
    public String onKuollut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if (this.onGladiaattori(kuka)) {
            return "<font color=c11b17>" + kuka + " kaatuu kuolleena maahan.</font>";
        }
        return "<font color=57e964>" + kuka + " kaatuu kuolleena maahan.</font>";
    }

    /**
     * Viesti: "'kuka' väistää hyökkäyksen." Väri: Vaaleansininen, mikäli
     * pelaaja väistää, oranssi mikäli hirviö.
     *
     * @param kuka
     * @return
     */
    public String vaistaa(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if (this.onGladiaattori(kuka)) {
            return "<font color=00ffff>" + kuka + " väistää hyökkäyksen.</font>";
        }
        return "<font color=fbb117>" + kuka + " väistää hyökkäyksen.</font>";
    }

    /**
     * Viesti: "'kuka' lyö 'keta'."
     *
     * @param kuka
     * @param keta
     * @return viesti
     */
    public String lyo(String kuka, String keta) {
        if (kuka == null || keta == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " lyö " + keta + ".";
    }

    /**
     * Viesti: "'kuka' levittää siipensä ja nousee lentoon."
     *
     * @param kuka
     * @return viesti
     */
    public String nouseeIlmaan(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " levittää siipensä ja nousee lentoon.";
    }

    /**
     * Viesti: "'kuka' on haavoittanut siipensä eikä kykene nousemaan ilmaan."
     *
     * @param kuka
     * @return viesti
     */
    public String eiKykeneNousemaanIlmaan(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " on haavoittanut siipensä eikä kykene nousemaan ilmaan.";
    }

    /**
     * Viesti: "'kuka' syöksee leimuavia liekkejä."
     *
     * @param kuka
     * @return viesti
     */
    public String syokseeTulta(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " syöksee leimuavia liekkejä.";
    }

    /**
     * Viesti: "Taistelu alkaa!"
     *
     * @return viesti
     */
    public String alkutervehdys() {
        return "Taistelu alkaa!";
    }

    /**
     * Viesti: rivinvaihto + "Peli on ohi [Enter]"
     *
     * @return viesti
     */
    public String loppuviesti() {
        return "<br>Peli on ohi. [Enter]";
    }

    /**
     * Viesti: "Lisää hirviöitä ryntää areenalle!"
     *
     * @return viesti
     */
    public String lisaaHirvioita() {
        return "Lisää hirviöitä ryntää areenalle!";
    }

    /**
     * Viesti: "'kuka' liikkuu."
     *
     * @param kuka
     * @return viesti
     */
    public String liikkuu(String kuka) {
        return kuka + " liikkuu.";
    }

    /**
     * Viesti: "'kuka' törmää seinään!"
     *
     * @param kuka
     * @return viesti
     */
    public String tormaa(String kuka) {
        return kuka + " törmää seinään!";
    }

    /**
     * Tarkistaa, sisältääkö parametrina saatu toimijan nimi
     * 'gladiaattori'-sanan.
     *
     * @param s tarkistettava merkkijono
     * @return boolean kyllä/ei
     */
    public boolean onGladiaattori(String s) {
        return s.toLowerCase().contains("gladiaattori");
    }
}
