package gladiaattoripeli.utilities;

/**
 * Yksinkertainen enum-luokka komentojen välittämistä selkeyttämään.
 */
public enum Komennot {

    POHJOINEN, ETELA, ITA, LANSI, KOILLINEN, KAAKKO, LOUNAS, LUODE, ODOTA, MIKAVAIN, HYOKKAAVAMMIN, PUOLUSTAVAMMIN, HYVAKSY;

    /**
     * Tarkistaa, onko parametrina saatu komento ilmansuunta.
     * @param k tarkistettava komento
     * @return boolean true/false on/ei ole
     */
    public Boolean onSuunta(Komennot k) {
        if (k.equals(Komennot.ETELA) || k.equals(Komennot.ITA)
                || k.equals(Komennot.LANSI) || k.equals(Komennot.POHJOINEN)
                || k.equals(Komennot.KOILLINEN) || k.equals(Komennot.KAAKKO) 
                || k.equals(Komennot.LOUNAS) || k.equals(Komennot.LUODE)) {
            return true;
        }
        return false;
    }
}
