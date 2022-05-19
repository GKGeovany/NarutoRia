package applicationV1.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import applicationV1.Box;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Environnement {

	private int width, height;
	private int [][] map;
	private Perso personnage;


	public Environnement(int width, int height) {
		personnage = new Perso(this,0,0);
		this.width = width;
		this.height = height;
		this.map = new int[height][width];
		try {
			readMap () ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	// méthode qui permet de lire ma map. On crée un objet de type File et on le lit grace au BufferReader qui
	// lit ligne par ligne les caractères du tableau de buffer
	public void readMap () throws IOException {
        File file = new File("src/map_simpliste.csv");
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // erreur d'ouverture
        }
        String ligne;
        String[] all_Line;
        try {
            int i = 0;
            // tant que la ligne n'est pas vide
            while ((ligne = bfr.readLine()) != null) {
//           	System.out.println(ligne);
                all_Line = ligne.split(","); // divise une chaîne en sous-chaînes à l'aide de séparateur (",").
                							// La méthode recueille ces sous-chaînes dans un tableau qui devient la valeur de retour.
                for(int j =0 ; j< all_Line.length; j++) {
                    this.map[i][j] = Integer.parseInt(all_Line[j].trim()); // on donne au tableau les id et supprime tous les caractères de code numérique inférieur ou égal à 32,
                    													// placés en tête ou en fin de la chaîne
                }
                i++;
            }
         
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
			// TODO Auto-generated catch block
		}
        try {
			bfr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getCase(int x, int y) {
		return this.map[y][x];
	}
	
	public Perso getPerso() {
		return this.personnage;
	}
	
	
}
