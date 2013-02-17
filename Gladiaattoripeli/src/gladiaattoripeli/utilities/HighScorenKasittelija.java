package gladiaattoripeli.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emleri
 */
public class HighScorenKasittelija {

    private File highScore;
    private Scanner lukija;
    private FileWriter kirjoittaja;
    private List<String> highScoreLista;

    public HighScorenKasittelija() {
        this.highScore = new File("highScore.txt");
    }

    public void lisaaHighScore(String pisteet) {
        this.highScoreLista = new ArrayList<String>();
        try {
            this.lukija = new Scanner(highScore);
            while (lukija.hasNextLine()) {
                this.highScoreLista.add(this.lukija.nextLine());
            }
            this.lukija.close();
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui.");
        }

        this.highScoreLista.add(pisteet);
        Collections.sort(this.highScoreLista);
        
        try {
            this.kirjoittaja = new FileWriter(highScore);

            for (String s : this.highScoreLista) {
                this.kirjoittaja.append(s + ";");
            }
            this.kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui.");
        }
    }

    public List<String> getHighScore() {
        try {
            this.lukija = new Scanner(this.highScore);
        } catch (FileNotFoundException ex) {
        }
        
        this.highScoreLista = new ArrayList<String>();

        while (this.lukija.hasNextLine()) {
            this.highScoreLista.add(this.lukija.nextLine());
        }
        this.lukija.close();

        return this.highScoreLista;
    }
}
