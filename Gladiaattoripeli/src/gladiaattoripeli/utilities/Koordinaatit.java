package gladiaattoripeli.utilities;

import java.util.Random;

/**
 * Kapseloitu (x, y)-koordinaatti ja siihen liittyviä apumetodeja.
 */
public class Koordinaatit {

    private int x;
    private int y;
    private Random arpoja;

    public Koordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
        this.arpoja = new Random();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
            this.x = x;
    }

    public void setY(int y) {
            this.y = y;
    }

    /**
     * Lisaa parametrina saatujen koordinaattien x- ja y-arvot nykyisiin arvoihin.
     * @param k lisattavat koordinaatit
     */
    public void lisaaKoordinaatit(Koordinaatit k) {
        this.setX(this.x + k.getX());
        this.setY(this.y + k.getY());
    }

    /**
     * Laskee yhteen kahdet koordinaatit.
     * @param k
     * @return
     */
    public Koordinaatit koordinaattienSumma(Koordinaatit k) {
        return new Koordinaatit(this.x + k.getX(), this.y + k.getY());
    }

    /**
     * Palauttaa parametrina saadusta suunnasta viereisen ruudun koordinaatit 
     * itsestään nähden.
     * @param suunta suunta, josta viereinen ruutu haetaan
     * @return viereisen ruudun koordinaatit
     */
    public Koordinaatit getViereisetKoordinaatitSuunnassa(Komennot suunta) {
        if (suunta == Komennot.ETELA) {
            return getViereisetApu(0, 1);
        }
        if (suunta == Komennot.POHJOINEN) {
            return getViereisetApu(0, -1);
        }
        if (suunta == Komennot.LANSI) {
            return getViereisetApu(-1, 0);
        }
        if (suunta == Komennot.ITA) {
            return getViereisetApu(1, 0);
        }
        if (suunta == Komennot.KOILLINEN) {
            return getViereisetApu(1, -1);
        }
        if (suunta == Komennot.KAAKKO) {
            return getViereisetApu(1, 1);
        }
        if (suunta == Komennot.LOUNAS) {
            return getViereisetApu(-1, 1);
        }
        if (suunta == Komennot.LUODE) {
            return getViereisetApu(-1, -1);
        }
        throw new IllegalArgumentException("Parametrina saatu komento ei ole suunta!");
    }
    
    private Koordinaatit getViereisetApu(int x, int y) {
        Koordinaatit viereiset = new Koordinaatit(x, y);
        viereiset.lisaaKoordinaatit(this);
        return viereiset;
    }

    /**
     * Palauttaa satunnaisen pääilmansuunnissa viereisen ruudun koordinaatit.
     * @return satunnainen viereinen ruutu
     */
    public Koordinaatit getSatunnainenRuutuVieressa() {
        int a = arpoja.nextInt(2);
        int b = arpoja.nextInt(2);
        Koordinaatit k;

        if (a == 0) {
            k = (b == 0) ? new Koordinaatit(this.x + 1, this.y) : new Koordinaatit(this.x, this.y + 1);
        } else {
            k = (b == 0) ? new Koordinaatit(this.x - 1, this.y) : new Koordinaatit(this.x, this.y - 1);
        }

        return k;
    }

    /**
     * Metodi etsii sen itsestään nähden viereisen ruudun, joka on lähimpänä 
     * parametrina saatua kohdetta. Mikäli kohderuutu on nykyinen ruutu, 
     * palautetaan omat koordinaatit.
     * @param k kohdekoordinaatit
     * @return sopivan viereisen ruudun koordinaatit
     */
    public Koordinaatit getViereinenRuutuKohtiKoordinaatteja(Koordinaatit k) {
        Koordinaatit viereinen = new Koordinaatit(0, 0);

        if (k.getX() > this.x) {
            viereinen.setX(this.x + 1);
        } else if (k.getX() < this.x) {
            viereinen.setX(this.x - 1);
        } else {
            viereinen.setX(this.x);
        }

        if (k.getY() > this.y) {
            viereinen.setY(this.y + 1);
        } else if (k.getY() < this.y) {
            viereinen.setY(this.y - 1);
        } else {
            viereinen.setY(this.y);
        }

        return viereinen;
    }

    public boolean onVieressa(Koordinaatit k) {
        if (k.equals(this)) {
            return false;
        }
        if (k.getX() > this.x + 1 || k.getX() < this.x - 1) {
            return false;
        } else if (k.getY() > this.y + 1 || k.getY() < this.y - 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Koordinaatit verrattava = (Koordinaatit) o;
        if (!(this.x == verrattava.getX()) || !(this.y == verrattava.getY())) {
            return false;
        }
        return true;
    }
}
