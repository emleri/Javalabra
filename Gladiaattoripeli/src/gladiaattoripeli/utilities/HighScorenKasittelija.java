package gladiaattoripeli.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Emleri
 */
public class HighScorenKasittelija {

    private File highScore;
    private Scanner lukija;
    private FileWriter kirjoittaja;

    public HighScorenKasittelija() {
        this.highScore = new File("highScore.txt");
    }

    public void lisaaHighScore(Pisteet pisteet) {
        List<Pisteet> highScoreLista = this.getHighScore();

        highScoreLista.add(pisteet);
        Collections.sort(highScoreLista);

        try {
            this.kirjoittaja = new FileWriter(highScore);

            for (int i = 0; i < 10; i++) {
                if (i < highScoreLista.size()) {
                    Pisteet p = highScoreLista.get(i);
                    this.kirjoittaja.append(p.getPisteet() + ":" + p.getNimi() + "\n");
                }
            }
            this.kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui.");
        }
    }

    public List<Pisteet> getHighScore() {
        try {
            this.lukija = new Scanner(this.highScore);
        } catch (FileNotFoundException ex) {
        }

        List<Pisteet> highScoreLista = new ArrayList<Pisteet>();

        while (this.lukija.hasNextLine()) {
            String s = lukija.nextLine();
            if (s.contains(":")) {
                String[] s2 = s.split(":");
                highScoreLista.add(new Pisteet(Integer.parseInt(s2[0]), s2[1]));
            }
        }
        this.lukija.close();

        return highScoreLista;
    }
}
