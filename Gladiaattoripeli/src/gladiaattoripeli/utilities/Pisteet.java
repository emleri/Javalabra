/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.utilities;

/**
 * High score -pisteet ja niiden omistajan kapseloiva apuluokka.
 * @author Emleri
 */
public class Pisteet implements Comparable<Pisteet> {
    private String nimi;
    private int pisteet;

    public Pisteet(int pisteet, String nimi) {
        this.setNimi(nimi);
        this.pisteet = pisteet;
    }

    public String getNimi() {
        return nimi;
    }
    
    public void setNimi(String nimi) {
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
