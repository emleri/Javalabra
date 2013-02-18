/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Ase;

/**
 *
 * @author Emleri
 */
public class Asegeneraattori {

    public Asegeneraattori() {
    }

    public Ase getMiekka() {
        return new Ase(4, 0);
    }

    public Ase getNuija() {
        return new Ase(1, 1);
    }

    Ase getKynnet() {
        return new Ase(2, 4);
    }
}
