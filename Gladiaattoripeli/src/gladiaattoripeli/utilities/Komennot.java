
package gladiaattoripeli.utilities;

/**
 * Yksinkertainen enum-luokka komentojen välittämistä selkeyttämään.
 */
public enum Komennot {
    POHJOINEN, ETELA, ITA, LANSI, KOILLINEN, KAAKKO, LOUNAS, LUODE, ODOTA, MIKAVAIN;
    
    public Boolean onSuunta(Komennot k) {
        if (k.equals(Komennot.ETELA) || k.equals(Komennot.ITA) || k.equals(Komennot.LANSI) || k.equals(Komennot.POHJOINEN)) {
            return true;
        }
        return false;
    }
}
