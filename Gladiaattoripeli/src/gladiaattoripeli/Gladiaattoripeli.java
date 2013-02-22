package gladiaattoripeli;

import gladiaattoripeli.UI.KayttoliittymanOhjaaja;

/**
 * Ohjelman käynnistävä main-metodin luokka. Ei muuta toiminnallisuutta.
 *
 * @author Emleri
 */
public class Gladiaattoripeli {

    /**
     * Käynnistää pelin.
     *
     * @param args
     */
    public static void main(String[] args) {
        KayttoliittymanOhjaaja ohjaaja = new KayttoliittymanOhjaaja();
        ohjaaja.aloitaPeli();
    }
}
