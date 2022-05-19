package applicationV1.controleur;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import applicationV1.modele.Environnement;
import applicationV1.modele.Perso;
import applicationV1.vue.VueMap;
import applicationV1.vue.VuePersonnage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{
	
	private Environnement env;
	private VueMap vueMap;
	private VuePersonnage vuePerso;
	
	@FXML
    private TilePane tilePane;
	@FXML
	private Pane pane;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.env = new Environnement(30, 20);
		
		lancement();
	}
	
	// cette méthode permet de controler les mouvements du personnage selon les clés UP,DOWN,RIGHT, et LEFT affiche "Tu ne peux plus avancer" en cas d'erreur.
	@FXML
	public void mouvements(KeyEvent k) {
//		System.out.println("test");
		Perso personnage = this.env.getPerso();
		try {
			switch (k.getCode()) {
				case UP	:
					personnage.setY(personnage.getY()-16);
					break;
					
				case DOWN	:
					personnage.setY(personnage.getY()+16);
					break;
				
				case RIGHT	:
					personnage.setX(personnage.getX()+16);
					break;
					
				case LEFT	:
					personnage.setX(personnage.getX()-16);
					break;
					
				default:
					break;
			}
		}catch(Exception e) {
			System.out.println("Tu ne peux plus avancer");
		}
	}
	
	public void lancement() {
		try {
			this.vueMap = new VueMap(env, tilePane);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.vuePerso = new VuePersonnage(pane, env.getPerso());
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	


}
