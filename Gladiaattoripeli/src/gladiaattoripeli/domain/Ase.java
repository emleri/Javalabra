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
    private int a; // Satunnaislukujen määrä, ei koskaan negatiivinen
    private int b; // Vakio, mikä vain kokonaisluku

    public Ase() {
        this.arpoja = new Random();
    }

    public Ase( int a, int b) {
        this();
        if(a < 0) {
            throw new IllegalArgumentException("Aseen kerroinmuuttuja ei saa olla negatiivinen.");
        }
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
     * Palauttaa aseen tekemän vahingon vahinkofunktionsa avulla.
     * @return
     */
    public int getVahinko() {
        int vahinko = 0;
        for(int i = 0; i < a; i++) {
            vahinko += (this.arpoja.nextInt(6) + 1);
        }
        return vahinko + b;
    }
    
    public Efekti getHyokkaysefekti(Koordinaatit k) {
        Efekti e = new Efekti('*');
        e.lisaaPiirtokohta(k);
        return e;
    }
}
