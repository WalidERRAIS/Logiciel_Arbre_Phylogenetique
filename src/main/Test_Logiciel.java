package main;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import interfaceGraphique.Menu;

public class Test_Logiciel {
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Menu menu= new Menu();
		menu.setVisible(true); //rend visible la fenêtre
	}

}
