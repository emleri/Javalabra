package gladiaattoripeli.utilities;

/**
 * Kapseloitu (x, y)-koordinaatti ja siihen liittyviä apumetodeja.
 */
public class Koordinaatit {

    private int x;
    private int y;
    private int maxX;
    private int maxY;

    public Koordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
        this.maxX = 999;
        this.maxY = 999;
    }

    public Koordinaatit(int x, int y, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void setRajat(int leveys, int korkeus) {
        this.maxX = leveys;
        this.maxY = korkeus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (x < this.maxX) {
            this.x = x;
        } else {
            this.x = maxX-1;
        }
        if (x < 0) {
            this.x = 0;
        }
    }

    public void setY(int y) {
        if (y < this.maxY) {
            this.y = y;
        } else {
            this.y = maxY-1;
        }
        if (y < 0) {
            this.y = 0;
        }
    }

    public void lisaaKoordinaatit(Koordinaatit k) {
        this.setX(this.x + k.getX());
        this.setY(this.y + k.getY());
    }

    public Koordinaatit koordinaattienSumma(Koordinaatit k) {
        return new Koordinaatit(this.x + k.getX(), this.y + k.getY());
    }

    public Koordinaatit getViereisetKoordinaatitSuunnassa(Suunta suunta) {
        Koordinaatit viereiset;

        if (suunta == Suunta.ETELA) {
            viereiset = new Koordinaatit(0, 1, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.POHJOINEN) {
            viereiset = new Koordinaatit(0, -1, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.LANSI) {
            viereiset = new Koordinaatit(-1, 0, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.ITA) {
            viereiset = new Koordinaatit(1, 0, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.KOILLINEN) {
            viereiset = new Koordinaatit(1, -1, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.KAAKKO) {
            viereiset = new Koordinaatit(1, 1, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.LOUNAS) {
            viereiset = new Koordinaatit(-1, 1, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }
        if (suunta == Suunta.LUODE) {
            viereiset = new Koordinaatit(-1, -1, this.maxX, this.maxY);
            viereiset.lisaaKoordinaatit(this);
            return viereiset;
        }

        return null;
    }

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
