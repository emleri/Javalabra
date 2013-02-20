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
        if (onGladiaattori(kuka)) {
            return "<font color=f88017>" + kuka + " on haavoittunut.</font>";
        }
        return "<font color=008000>" + kuka + " on haavoittunut.</font>";
    }

    public String onVammautunut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if (onGladiaattori(kuka)) {
            return "<font color=ff0000>" + kuka + " on vammautunut vakavasti.</font>";
        }
        return "<font color=00ff00>" + kuka + " on vammautunut vakavasti.</font>";
    }

    public String onKuollut(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if(this.onGladiaattori(kuka)) {
            return "<font color=c11b17>" + kuka + " kaatuu kuolleena maahan.</font>";
        }
        return "<font color=57e964>" + kuka + " kaatuu kuolleena maahan.</font>";
    }

    public String vaistaa(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        if(this.onGladiaattori(kuka)) {
            return "<font color=00ffff>" + kuka + " väistää hyökkäyksen.</font>";
        }
        return "<font color=fbb117>" + kuka + " väistää hyökkäyksen.</font>";
    }

    public String lyo(String kuka, String keta) {
        if (kuka == null || keta == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " lyö " + keta + ".";
    }

    public String nouseeIlmaan(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " levittää siipensä ja nousee lentoon.";
    }

    public String eiKykeneNousemaanIlmaan(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " on haavoittanut siipensä eikä kykene nousemaan ilmaan.";
    }

    public String syokseeTulta(String kuka) {
        if (kuka == null) {
            throw new IllegalArgumentException();
        }
        return kuka + " syöksee leimuavia liekkejä.";
    }

    public String alkutervehdys() {
        return "Taistelu alkaa!";
    }

    public String loppuviesti() {
        return "<br>Peli on ohi.<br><br>Paina mitä vain näppäintä "
                + "päästäksesi eteenpäin.";
    }
    
    public boolean onGladiaattori(String s) {
        return s.toLowerCase().contains("gladiaattori");
    }
}
