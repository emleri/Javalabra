/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.utilities;

/**
 *
 * @author Emleri
 */
public class Viestigeneraattori {

    public Viestigeneraattori() {
    }
    
    public String onHaavoittunut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " on haavoittunut.";
    }
    
    public String onVammautunut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " on vammautunut vakavasti.";
    }
    
    public String onKuollut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " kaatuu kuolleena maahan.";
    }

    public String vaistaa(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " väistää hyökkäyksen.";
    }

    public String lyo(String kuka, String keta) {
        if (kuka == null || keta == null) {
            throw new IllegalArgumentException();
        }
       return kuka + " lyö " + keta + ".";
    }
}
