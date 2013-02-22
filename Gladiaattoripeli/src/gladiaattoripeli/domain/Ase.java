
package gladiaattoripeli.domain;

import java.util.Random;

/**
 * Erilaisia aseita esittävä luokka. Käytännössä toistaiseksi vain hieman
 * monimutkaisempi satunnaislukugeneraattori, mutta mahdollistaa esim.
 * asekohtaisten vahinkoviestien yms. toteuttamisen myöhemmin.
 */
public class Ase {

    private String nimi; // Ei käytössä toistaiseksi.
    private Random arpoja; // Satunnaislukugeneraattori
    
    // Vahinkofunktion muuttujat
    private int a; // Satunnaislukujen määrä, ei koskaan negatiivinen
    private int b; // Vakio, mikä vain kokonaisluku

    /**
     * Aseen konstruktori. Luo uuden aseen parametreina saaduilla vahinkofunktio-
     * muuttujilla.
     * @param a muuttuja a
     * @param b muuttuja b
     */
    public Ase(int a, int b) {
        this.arpoja = new Random();
        if (a < 0) {
            throw new IllegalArgumentException("Aseen kerroinmuuttuja ei saa olla negatiivinen.");
        }
        this.a = a;
        this.b = b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }

    /**
     * Palauttaa aseen tekemän vahingon vahinkofunktionsa avulla. Vahinkofunktio
     * on: (a kpl satunnaislukuja väliltä 1-6) + b
     *
     * @return int vahingon määrä
     */
    public int getVahinko() {
        int vahinko = 0;
        for (int i = 0; i < a; i++) {
            vahinko += (this.arpoja.nextInt(6) + 1);
        }
        return vahinko + b;
    }

    /**
     * Palauttaa aseen hyökkäysefektin kohdistettuna parametrina saatuihin 
     * koordinaatteihin. Tällä hetkellä kaikkien aseiden efekti on vielä sama.
     * @param k kohdekoordinaatit
     * @return aseen efekti
     */
    public Efekti getHyokkaysefekti(Koordinaatit k) {
        Efekti e = new Efekti('*');
        e.lisaaPiirtokohta(k);
        return e;
    }
}
