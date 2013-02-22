
package gladiaattoripeli.utilities;

import gladiaattoripeli.domain.Ase;

/**
 * Kapseloi yleisiä aseiden konstruktoriarvoja helposti käytettäviin apumetodeihin.
 */
public class Asegeneraattori {

    public Asegeneraattori() {
    }

    /**
     * Palauttaa miekan.
     * @return Ase(4,0)
     */
    public Ase getMiekka() {
        return new Ase(4, 0);
    }

    /**
     * Palauttaa nuijan.
     * @return Ase(1, 1)
     */
    public Ase getNuija() {
        return new Ase(1, 1);
    }

    /**
     * Palauttaa kynnet.
     * @return Ase(2, 4)
     */
    public Ase getKynnet() {
        return new Ase(2, 4);
    }
}
