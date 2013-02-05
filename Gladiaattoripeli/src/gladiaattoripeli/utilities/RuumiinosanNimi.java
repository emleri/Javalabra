
package gladiaattoripeli.utilities;

/**
 * Yksinkertainen enum-luokka helpottamaan ruumiinosien käsittelyä.
 */
public enum RuumiinosanNimi {
    KESKIVARTALO ("rintakehä"),
    PAA ("pää"),
    OIKEAKASI ("oikea käsi"),
    OIKEAJALKA ("oikea jalka"),
    VASENKASI ("vasen käsi"),
    VASENJALKA ("vasen jalka");
    
    private String nimi;
    
    RuumiinosanNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }
    
    
}
