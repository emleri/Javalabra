/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Esittää animoituja efektejä linkitettynä listana kaksiulotteisia taulukoita. 
 * Jokainen taulukko esittää yhtä efektin animaatioframea, linkitetyllä listalla
 * eteenpäin liikkuessa siirrytään framesta toiseen.
 * 
 * @author Emleri
 */
public class Efekti {
    private char ulkoasu;
    private int framet;
    private Efekti seuraava;
    private List<Koordinaatit> piirtoalue;

    public Efekti(char ulkoasu) {
        this.ulkoasu = ulkoasu;
        this.piirtoalue = new ArrayList<Koordinaatit>();
    }
    
    public void lisaaPiirtokohta(Koordinaatit k) {
        this.piirtoalue.add(k);
    }
    
    public Boolean piirretaanko(Koordinaatit k) {
        for (Koordinaatit l : this.piirtoalue) {
            if (k.equals(l)) {
                return true;
            }
        }
        return false;
    }
    
    public void lisaaSeuraavaFrame(Efekti e) {
        this.seuraava = e;
    }
    
    @Override
    public String toString() {
        return "<font color=ff3300>" + this.ulkoasu + "</font>";
    }

    public Efekti getSeuraava() {
        return this.seuraava;
    }
    
    public Efekti getViimeinen() {
        Efekti e = this;
        while(e.getSeuraava() != null) {
            e = e.getSeuraava();
        }
        return e;
    }
}
