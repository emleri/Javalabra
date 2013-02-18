/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.domain;

import gladiaattoripeli.utilities.Koordinaatit;
import java.util.Random;

/**
 * Erilaisia aseita esittävä luokka. Käytännössä toistaiseksi vain hieman 
 * monimutkaisempi satunnaislukugeneraattori, mutta mahdollistaa esim. 
 * asekohtaisten vahinkoviestien yms. toteuttamisen myöhemmin.
 * @author Emleri
 */
public class Ase {

    private String nimi; // Ei käytössä toistaiseksi.
    private Random arpoja;
    
    // Vahinkofunktion muuttujat
    private int a;
    private int b; 

    public Ase() {
        this.arpoja = new Random();
    }

    public Ase( int a, int b) {
        this();
        this.a = a;
        this.b = b;
    }
    
    public void setA(int a) {
        this.a = a;
    }
    
    public void setB(int b) {
        this.b = b;
    }

    /**
     * Palauttaa satunnaisluvun, aseen tekemän vahingon, vahinkofunktionsa avulla.
     * @return
     */
    public int getVahinko() {
        return a * (this.arpoja.nextInt(5) + 1) + b;
    }
    
    public Efekti getHyokkaysefekti(Koordinaatit k) {
        Efekti e = new Efekti('*');
        e.lisaaPiirtokohta(k);
        return e;
    }
}
