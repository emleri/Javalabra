
package gladiaattoripeli.domain;

/**
 * Yksinkertainen enum-luokka helpottamaan ruumiinosien käsittelyä.
 */
public enum RuumiinosanNimi {
    KESKIVARTALO ("rintakehä"),
    PAA ("pää"),
    OIKEAKASI ("oikea käsi"),
    OIKEAJALKA ("oikea jalka"),
    VASENKASI ("vasen käsi"),
    VASENJALKA ("vasen jalka"),
    OIKEASIIPI ("oikea siipi"),
    VASENSIIPI ("vasen siipi"),
    HANTA ("häntä");
    
    private static int size = 9;

    /**
     * Palauttaa nimien määrän.
     * @return nimien määrä
     */
    public static int getSize() {
        return size;
    }
    
    private String nimi; // Ruumiinosan nimi String-muuttujana.
    
    RuumiinosanNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }
}
